package com.example.cacheexample.RepositoryImpl;

import com.example.cacheexample.Entity.User;

public interface UserRepository {

    User findByIdCache(Long id);
    User findByIdNoCache(Long id);
    void refresh(Long id);
}
