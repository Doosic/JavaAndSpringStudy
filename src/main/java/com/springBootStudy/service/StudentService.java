package com.springBootStudy.service;

import com.springBootStudy.StudentEntity;
import com.springBootStudy.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Cacheable("StudentEntity")
    public void printStudent(String name){
        StudentEntity studentEntity = studentRepository.getStudent(name);
        System.out.println("찾는 학생 "+ studentEntity);
    }

    @PostConstruct
    public void init() {
        studentRepository.enroll("jack", 15, StudentEntity.Grade.E);
        studentRepository.enroll("jack", 15, StudentEntity.Grade.E);
        studentRepository.enroll("jack", 15, StudentEntity.Grade.E);
        studentRepository.enroll("fred", 18, StudentEntity.Grade.A);
        studentRepository.enroll("fred", 18, StudentEntity.Grade.A);
        studentRepository.enroll("fred", 18, StudentEntity.Grade.A);
        studentRepository.enroll("fred", 18, StudentEntity.Grade.A);
        studentRepository.enroll("cache", 17, StudentEntity.Grade.C);
        studentRepository.enroll("cache", 17, StudentEntity.Grade.C);
    }
}
