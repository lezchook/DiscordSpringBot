package com.leshchenko.starcraftbot.repo;

import com.leshchenko.starcraftbot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllBy();
    void deleteAll();
}
