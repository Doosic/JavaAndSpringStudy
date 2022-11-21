//package com.getinline.getinline.controller.api;
//
//import com.getinline.getinline.constant.ErrorCode;
//import com.getinline.getinline.constant.EventStatus;
//import com.getinline.getinline.constant.PlaceType;
//import com.getinline.getinline.domain.Place;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
///**
// * Spring Data REST 로 API 를 만들어서 당장 필요가 없어진 컨트롤러.
// * 우선 deprecated 하고, 향후 사용 방안을 고민해 본다.
// * 필요에 따라서는 다시 살릴 수도 있음
// *
// * @deprecated 0.1.2
// */
//@Deprecated
//@WebMvcTest(APIPlaceController.class)
//class APIPlaceControllerTest {
//
//    private final MockMvc mvc;
//
//    public APIPlaceControllerTest(@Autowired MockMvc mvc) {
//        this.mvc = mvc;
//    }
//
//   private Place getDefaultPlace(String name){
//     return Place.builder()
//            .placeType(PlaceType.COMMON)
//            .placeName("야미배드민턴장")
//            .address(name)
//            .phoneNumber("010-1234-1234")
//            .capacity(String.valueOf(30))
//            .memo("신장개업")
//            .build();
//   }
//
//    @DisplayName("[API] [GET] 장소 리스트 조회")
//    @Test
//    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlacesInStandardResponse() throws Exception{
//        // Given
////        List<Place> placeList = new ArrayList<>();
////        placeList.add(getDefaultPlace("실내배드민턴장"));
////        placeList.add(getDefaultPlace("실외배드민턴장"));
////        placeList.add(getDefaultPlace("배드민턴장"));
//
//        // When & Then
//        mvc.perform(get("/api/places"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data").isArray())
//                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
//                .andExpect(jsonPath("$.data[0].placeName").value("야미배드민턴장"))
//                .andExpect(jsonPath("$.data[0].address").value("서울시 강남구"))
//                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1234-1234"))
//                .andExpect(jsonPath("$.data[0].capacity").value(30))
//                .andExpect(jsonPath("$.data[0].memo").value("신장개업"))
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
//                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
//
//    }
//
//    @DisplayName("[API] [GET] 단일 장소 조회")
//    @Test
//    void givenPlaceAndItsId_whenRequestingPlace_thenReturnsPlaceInStandardResponse() throws Exception{
//        // Given
//        int placeId = 1;
//
//        // When & Then
//        mvc.perform(get("/api/places/" + placeId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data").isMap())
//                .andExpect(jsonPath("$.data.placeType").value(PlaceType.COMMON.name()))
//                .andExpect(jsonPath("$.data.placeName").value("야미배드민턴장"))
//                .andExpect(jsonPath("$.data.address").value("서울시 강남구"))
//                .andExpect(jsonPath("$.data.phoneNumber").value("010-1234-1234"))
//                .andExpect(jsonPath("$.data.capacity").value(30))
//                .andExpect(jsonPath("$.data.memo").value("신장개업"))
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
//                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
//
//    }
//
//    @DisplayName("[API] [GET] 단일 장소 조회 - 장소 없는 경우")
//    @Test
//    void givenPlaceId_whenRequestingPlace_thenReturnsEmptyStandardResponse() throws Exception{
//        // Given
//        int placeId = 2;
//
//        // When & Then
//        mvc.perform(get("/api/places/" + placeId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data").isEmpty())
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
//                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
//
//    }
//
//    @DisplayName("[API] [GET] 장소 리스트 조회 - 장소 리스트 데이터를 담은 표준 API 출력")
//    @Test
//    void givenNothing_whenRequestingPlaces_thenReturnsPlacesInStandardResponse() throws Exception {
//        // Given
//
//
//        // When & Then
//        mvc.perform(get("/api/places"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.data").isArray())
//                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
//                .andExpect(jsonPath("$.data[0].placeName").value("야미배드민턴장"))
//                .andExpect(jsonPath("$.data[0].address").value("서울시 강남구"))
//                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1234-1234"))
//                .andExpect(jsonPath("$.data[0].capacity").value(30))
//                .andExpect(jsonPath("$.data[0].memo").value("신장개업"))
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
//                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
//
//    }
//}