package com.example.cacheexample.Repository;

import com.example.cacheexample.Entity.User;
import com.example.cacheexample.RepositoryImpl.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    // DB를 연결하지 않았기에 예제의 DB 역할을 해줄 Map
    private final Map<Long, User> storage;

    @Override
    @Cacheable("User")
    public User findByIdCache(Long id) {
        slowQuery(3000);
        return storage.get(id);
    }

    @Override
    public User findByIdNoCache(Long id) {
        slowQuery(3000);
        return storage.get(id);
    }

    @Override
    @CacheEvict(value = "User", key = "#id")
    public void refresh(Long id){
        log.info("Cache Clear: " + id);
    }


    // storage 에 유저정보를 넣어준다.
    public User enroll(Long id, String name, String email, Integer age){
        return storage.put(id, User.of(id, name, email, age));
    }

    // 빅 쿼리를 돌려 시간이 걸린다는 가정
    private void slowQuery(long seconds) {
        try{
            Thread.sleep(seconds);
        }catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
