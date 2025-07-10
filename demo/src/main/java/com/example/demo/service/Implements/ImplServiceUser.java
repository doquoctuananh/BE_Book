package com.example.demo.service.Implements;

import com.example.demo.model.User;
import com.example.demo.repository.IRepositoryUser;
import com.example.demo.service.IServiceUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplServiceUser  implements IServiceUser {

    @Autowired
    private IRepositoryUser repoUser;

    @Override
    public User findByUsername(String username) {
        return repoUser.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        return repoUser.save(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return repoUser.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional
    public User findByPhone(String phone) {
        return repoUser.findByPhone(phone).orElse(null);
    }
}
