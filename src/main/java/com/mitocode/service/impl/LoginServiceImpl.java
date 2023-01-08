package com.mitocode.service.impl;

import com.mitocode.model.User;
import com.mitocode.repo.ILoginRepo;
import com.mitocode.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService{

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private ILoginRepo repo;

    @Override
    public User checkUsername(String username) {
        return repo.checkUsername(username);
    }

    @Override
    public void changePassword(String password, String username) {
        repo.changePassword(bcrypt.encode(password), username);
    }
}
