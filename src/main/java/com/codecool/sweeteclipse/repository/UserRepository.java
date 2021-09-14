package com.codecool.sweeteclipse.repository;

import com.codecool.sweeteclipse.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    boolean existsByName(String name);

    Optional<User> findUserById(long id);

}
