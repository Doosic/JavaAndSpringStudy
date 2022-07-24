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
@EnableCaching // 어노테이션을 이용하여 해당 클래스에서 캐시기능을 사용하겠다는 선언
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
}
