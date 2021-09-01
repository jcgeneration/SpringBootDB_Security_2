
package org.generation.demodb.users;

import org.apache.catalina.LifecycleState;
import org.generation.utils.SHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }//constructor

    public List<user> getUsers(){
        return userRepository.findAll();
    }// getUsers

    public user getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("User does not exists " + userId)
        );
    }// getUser

    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalStateException("User does not exist " + userId);
        }//else
    } // deleteUser

    public void addUser(user usr){
        Optional<user> userByName = userRepository.findUserByName(usr.getUsername());
        if (userByName.isPresent()) {
            throw new IllegalStateException("username exist !!!");
        } //if

        userRepository.save(usr);
    }//addUser

    public void updateUser(Long userId, String oldPassword, String newPassword) {
        if (! userRepository.existsById(userId)) {
           throw new IllegalStateException("User does not exist " + userId);
        }//if ! exists
        user usr = userRepository.getById(userId);
        if (! SHAUtil.verifyHash(oldPassword, usr.getPassword()) ){
        //if (! usr.getPassword().equals(oldPassword)) {
            throw new IllegalStateException("Password does not match " + userId);
        }//if ! exists
       if (newPassword!=null && newPassword.length()>=4 &&
               (! SHAUtil.verifyHash(newPassword, usr.getPassword())) ) {
                //(! usr.getPassword().equals(newPassword)) ) {
                usr.setPassword(newPassword);
           userRepository.save(usr);
        }//if
    }// updateUser

}//class UserService
