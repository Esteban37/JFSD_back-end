package com.mitocode.service.impl;

import com.mitocode.model.ResetToken;
import com.mitocode.repo.IResetTokenRepo;
import com.mitocode.service.IResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetTokenServiceImpl implements IResetTokenService {

    @Autowired
    private IResetTokenRepo repo;

    @Override
    public ResetToken findByToken(String token) {
        return repo.findByToken(token);
    }

    @Override
    public void save(ResetToken token) {
        repo.save(token);
    }

    @Override
    public void delete(ResetToken token) {
        repo.delete(token);
    }

}
