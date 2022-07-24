package com.example.cacheexample.Service;

import com.example.cacheexample.Entity.User;
import com.example.cacheexample.Repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepositoryImpl userRepository;

    public User printUser(Long id){
        User user = userRepository.findByIdCache(id);
        log.info("찾는 사람: "+user);
        return user;
    }

    public User printUserNoCache(Long id){
        User user = userRepository.findByIdNoCache(id);
        return user;
    }

    // userRepositoryImpl 의 enroll 메서드를 호출하여 유저정보를 초기화해준다.
    @PostConstruct
    public void init() {
        userRepository.enroll(0L,"jack", "jack@naver.com", 15);
        userRepository.enroll(1L, "fred", "fred@naver.com",18);
        userRepository.enroll(2L, "cache", "cache@naver.com",17);
    }

}
