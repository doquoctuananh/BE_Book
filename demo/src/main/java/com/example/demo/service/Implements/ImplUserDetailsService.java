package com.example.demo.service.Implements;

import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.User;
import com.example.demo.repository.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplUserDetailsService implements UserDetailsService {

    @Autowired
    private IRepositoryUser repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repositoryUser.findByUsername(username)
                .orElseThrow(() ->new UsernameNotFoundException("khong tim thay user"));
        return new CustomUserDetail(user);
    }
}
