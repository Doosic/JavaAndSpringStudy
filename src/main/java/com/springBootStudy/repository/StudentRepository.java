package com.springBootStudy.repository;

import com.springBootStudy.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
//import org.springframework.nativex.hint.AotProxyHint;
//import org.springframework.nativex.hint.ProxyBits;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

//@AotProxyHint(targetClass = com.springBootStudy.repository.StudentRepository.class, proxyFeatures = ProxyBits.IS_STATIC)
@RequiredArgsConstructor
@Repository
@Component
public class StudentRepository {

    private final Map<String, StudentEntity> storage;

    @Cacheable("student")
    public StudentEntity getStudent(String name){
        System.out.println("[repo] 나의 통행료는 무척 비싸다!");
        return storage.get(name);
    }

    public StudentEntity enroll(String name, Integer age, StudentEntity.Grade grade){
        return storage.put(name, StudentEntity.of(name, age, grade));
    }
}
