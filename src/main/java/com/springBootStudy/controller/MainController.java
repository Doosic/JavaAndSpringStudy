package com.springBootStudy.controller;

import com.springBootStudy.properties.MyProperties;
import com.springBootStudy.service.MyService;
import com.springBootStudy.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final SortService sortService;
    private final MyProperties myProperties;
    private final MyService myService;

    // localhost:8080/?list=3,1,2,4,5
    @GetMapping("/")
    public String hello(@RequestParam List<String> list){
        return sortService.doSort(list).toString();
    }

    @GetMapping("/my-height")
    public Map<String, Integer> myHeight (){
        Map<String, Integer> map = new HashMap<>() ;
        map.put("my.height (@ConfigurationProperties)", myProperties.getHeight());
        map.put("my.height (@Value)", myService.getHeight());
        return map;
    }
}
