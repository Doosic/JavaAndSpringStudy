package com.example.cacheexample.Contorller;

import com.example.cacheexample.Entity.User;
import com.example.cacheexample.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@EnableCaching
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user/nocache/{id}")
    public User getNoCacheUser(
            @PathVariable Long id
    ){
        long start = System.currentTimeMillis();      // 시간측정
        User user = userService.printUserNoCache(id); // 데이터베이스 조회
        long end = System.currentTimeMillis();

        log.info(user.getName() + "NoCache 수행시간 : " + (end-start));
        return user;
    }

    @GetMapping("/user/cache/{id}")
    public User getCacheUser(
            @PathVariable Long id
    ){
        long start = System.currentTimeMillis();   // 시간측정
        User user = userService.printUser(id);     // 데이터베이스 조회
        long end = System.currentTimeMillis();

        log.info(user.getName() + " Cache 수행시간 : " + (end-start));
        return user;
    }

    // key 값을 이용하여 캐시삭제
    @GetMapping("/user/refresh/{id}")
    public void refreshCache(
            @PathVariable Long id
    ){
        userService.refresh(id);
    }
}
