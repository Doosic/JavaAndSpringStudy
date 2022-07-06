package com.springBootStudy;

import com.springBootStudy.properties.MyProperties;
import com.springBootStudy.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringBootStudyApplication {

    private final MyProperties myProperties;
    private final StudentService studentService;
    private final String username;
    private final String password;

    public SpringBootStudyApplication(
            MyProperties myProperties,
            StudentService studentService,
            @Value("${spring.datasource.username:vault가}") String username,
            @Value("${spring.datasource.password:꺼져있어요}") String password
    ) {
        this.myProperties = myProperties;
        this.studentService = studentService;
        this.username = username;
        this.password = password;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }

/*    @PostConstruct
      스프링 캐시는 모든 Bean 들이 로딩이 된뒤 활성화가 되는데 @PostConstruct 는 해당클래스의 모든 의존성이 완성된 뒤 이기 때문에
      캐시가 준비되지 않은 상태이기에 제대로 실행되지 않은것.
*/
//    @EventListener(ApplicationReadyEvent.class)
//    public void init() {
////        System.out.println("[configurationProps] " + myProperties.getAge());
//        studentService.printStudent("jack");
//        studentService.printStudent("jack");
//        studentService.printStudent("jack");
//        studentService.printStudent("cache");
//        studentService.printStudent("cache");
//        studentService.printStudent("fred");
//        System.out.println("id: " + username);
//        System.out.println("pw: " + password);
//    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("내 나이는: "+ myProperties.getAge());
            studentService.printStudent("jack");
            studentService.printStudent("jack");
            studentService.printStudent("jack");
            studentService.printStudent("cache");
            studentService.printStudent("cache");
            studentService.printStudent("fred");
            System.out.println("user: " + username);
            System.out.println("pw: " + password);
        };
    }




}
