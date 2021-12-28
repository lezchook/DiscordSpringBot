package com.leshchenko.starcraftbot.service;

import com.leshchenko.starcraftbot.domain.User;
import com.leshchenko.starcraftbot.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class DataBaseService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(String username) {
        User user = new User();
        user.setCount(0);
        user.setUsername(username);
        userRepo.save(user);
    }

    public void addScore(String username) {
        User user = userRepo.findByUsername(username);
        user.setCount(user.getCount() + 1);
        userRepo.save(user);
    }

    public List<User> showTable() {
        return userRepo.findAllBy();
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }

    public boolean containCheck(String username) {
        List<User> users = this.showTable();
        Iterator<User> iterator = users.iterator();
        int i = 0;
        if (!iterator.hasNext()) {
            return false;
        }
        while (iterator.hasNext()) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                return true;
            }
            i++;
            iterator.next();
        }
        return false;
    }
}
