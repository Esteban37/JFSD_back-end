package com.mitocode.service;

import com.mitocode.model.ResetToken;

public interface IResetTokenService {

    ResetToken findByToken(String token);

    void save(ResetToken token);

    void delete(ResetToken token);

}
