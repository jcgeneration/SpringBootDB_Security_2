package org.generation.demodb.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    @Query("SELECT u FROM user u WHERE u.username=?1")
    Optional<user> findUserByName (String username);

}//interface
