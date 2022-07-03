package com.paul.demo.auth;

import com.paul.demo.entity.app_user.AppUser;
import com.paul.demo.exception.NotFoundException;
import com.paul.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringUserService implements UserDetailsService {
    @Autowired
    private AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AppUser appUser  = appUserService.getUserByEmail(username);
            return new SpringUser(appUser);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Username is wrong.");
        }
    }
}
