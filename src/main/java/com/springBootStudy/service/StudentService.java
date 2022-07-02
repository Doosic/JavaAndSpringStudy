package com.springBootStudy.service;

import com.springBootStudy.StudentEntity;
import com.springBootStudy.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public void printStudent(String name){
        StudentEntity studentEntity = studentRepository.getStudent(name);
        log.info("찾는 학생~ "+ studentEntity);
    }

    @PostConstruct
    public void init() {
        studentRepository.enroll("jack", 15, StudentEntity.Grade.E);
        studentRepository.enroll("fred", 18, StudentEntity.Grade.A);
        studentRepository.enroll("cache", 17, StudentEntity.Grade.C);
    }
}
