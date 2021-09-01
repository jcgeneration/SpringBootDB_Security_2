
package org.generation.demodb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.generation.demodb.jwt.config.Token;
import org.generation.demodb.jwt.config.loginData;
import org.generation.demodb.users.UserRepository;
import org.generation.demodb.users.UserService;
import org.generation.demodb.users.user;
import org.generation.utils.SHAUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }//constructor

    @PostMapping("/login")
    public Token login (@RequestBody loginData data) throws ServletException {
        Optional<user> userByName = userRepository.findUserByName(data.getUsername());
        if (userByName.isPresent()) {
            if ( (data.getUsername().equals(userByName.get().getUsername())) &&
              (SHAUtil.verifyHash(data.getPassword(), userByName.get().getPassword())  ) ){
                return new Token( generateToken(data.getUsername()) );
            }// if getUserName && getPassword
        } //if isPresent

        throw new ServletException("Invalid login. Please check your credentials.");
    }// login

    private String generateToken( String email )  {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.HOUR,  10);
        String secret = "this-secret-is-not-very-secret-99";

        return Jwts.builder().setSubject( email ).claim( "role", "user" )
                .setIssuedAt( new Date() ).setExpiration(calendar.getTime() )
                .signWith( SignatureAlgorithm.HS256, secret ).compact();
    }//generateToken


} //class
