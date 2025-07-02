package com.example.demo.configuration.security;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.Implements.ImplServiceRole;
import com.example.demo.service.Implements.ImplServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ImplServiceUser serviceUser;
    @Autowired
    private ImplServiceRole serviceRole;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(serviceRole.findByName("ADMIN") == null) {
            Role role = new Role();
            role.setName("ADMIN");
            serviceRole.save(role);
        }
        if(serviceRole.findByName("USER") == null) {
            Role role = new Role();
            role.setName("USER");
            serviceRole.save(role);
        }
        if(serviceUser.findByUsername("adminadmin") == null) {
            User user = new User();
            user.setUsername("adminadmin");
            user.setPassword(bCryptPasswordEncoder.encode("123456789"));
            user.setEmail("admin@gmail.com");
            user.setGender(true);
            user.setAddress("Nghe an");
            user.setPhone("0968555555");
            Role role = serviceRole.findByName("ADMIN");
            user.setRole(role);
            serviceUser.save(user);
        }
        if(serviceUser.findByUsername("username123") == null) {
            User user = new User();
            user.setUsername("username123");
            user.setPassword(bCryptPasswordEncoder.encode("987654321"));
            user.setEmail("user@gmail.com");
            user.setGender(true);
            user.setAddress("Ha noi");
            user.setPhone("0968777777");
            Role role = serviceRole.findByName("USER");
            user.setRole(role);
            serviceUser.save(user);
        }
    }
}
