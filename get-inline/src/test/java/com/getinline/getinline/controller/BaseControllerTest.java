//package com.getinline.getinline.controller;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestConstructor;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BaseControllerTest.class)
//class BaseControllerTest {
//
//    /*
//        @AutoConfigureMockMvc
//        사용시 Mock 을 @Autowired 로 주입 가능하다.
//
//        그러나 Junit 5 부터는 생성자 주입이 가능하다.
//        @SpringBootTest 에 @ExtendWith(SpringExtension.class) 이 붙어있기에 가능
//    */
//    private final MockMvc mvc;
//
//    public BaseControllerTest(@Autowired MockMvc mvc) {
//        this.mvc = mvc;
//    }
//
//    @DisplayName("[view] [GET] 기본 페이지 요청")
//    @Test
//    void givenNothing_whenRequestingRootPage_thenReturnsIndexPage() throws Exception {
//        // Given
//
//        // When & Then
//        mvc.perform(get("/"))
//                .andExpect(status().isOk()) // 200번
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // view로 떨어질경우 Header의 accept에 text html 이 들어있다
//                .andExpect(content().string(containsString("This is default page."))) // 해당 페이지 body에 일치하는 내용이 있는지 확인
//                .andExpect(view().name("index")) // 이동될 view 페이지의 이름이 일치하는지 확인
//                .andDo(print());
//
//    }
//
//    /*
//        만약 given, when, then 폼에 맞추고 싶다면
//        @Test
//        void root() throws Exception {
//            // Given
//
//            // When
//            ResultActions result = mvc.perform(get("/"));
//
//            // Then
//            result
//                    .andExpect(status().isOk()) // 200번
//                    .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // view로 떨어질경우 Header의 accept에 text html 이 들어있다
//                    .andExpect(content().string(containsString("This is default page."))) // 해당 페이지 body에 일치하는 내용이 있는지 확인
//                    .andExpect(view().name("index")) // 이동될 view 페이지의 이름이 일치하는지 확인
//                    .andDo(print());
//
//        }
//    */
//
//}