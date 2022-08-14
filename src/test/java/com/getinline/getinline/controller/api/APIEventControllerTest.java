package com.getinline.getinline.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.constant.PlaceType;
import com.getinline.getinline.dto.EventDTO;
import com.getinline.getinline.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(APIEventController.class)
class APIEventControllerTest {

    /*
        TDD 간단하게
        입력/출력의 검사 어떠한 데이터를 내보내는지가 주 관심사
        @MockBean 어노테이션은 SpringBoot 에서 지원하는것
        Mockito 를 SpringBoot 에서 이미 포함시켜서 가지고있다가
        지원하는것.
    */

    private final MockMvc mvc;
    private final ObjectMapper mapper;
    
    // @MockBean 은 더티컨텍스트라는 문제가 있다. 회피하는 방법은 현제 없으며
    // 테스트가 변경될때마다 컨테이너가 다시 올라온다. 그래서 느려지는 문제가 있다.
    @MockBean
    private EventService eventService;

    public APIEventControllerTest(
            @Autowired MockMvc mvc,
            @Autowired ObjectMapper mapper
    ) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    @DisplayName("[API] [GET] 이벤트 리스트 조회 + 검색 파라미터")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlacesInStandardResponse() throws Exception{
        // Given
        given(eventService.getEvents(any(), any(), any(), any(), any()))
                .willReturn(List.of(createEventDTO()));

        // When & Then
        mvc.perform(
                get("/api/events")
                        .queryParam("placeId","1")
                        .queryParam("eventName","운동")
                        .queryParam("eventStatus", EventStatus.OPENED.name())
                        .queryParam("eventStartDateTime","2021-01-01T00:00:00")
                        .queryParam("eventEndDateTime","2021-01-02T00:00:00")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeId").value(1L))
                .andExpect(jsonPath("$.data[0].eventName").value("오후 운동"))
                .andExpect(jsonPath("$.data[0].eventStatus").value(EventStatus.OPENED.name()))
                .andExpect(jsonPath("$.data[0].eventStartDatetime").value(
                        LocalDateTime.of(2021,1,1,13,0,0)
                                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                ))
                .andExpect(jsonPath("$.data[0].eventEndDatetime").value(
                        LocalDateTime.of(2021,1,1,13,0,0)
                                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                ))
                .andExpect(jsonPath("$.data[0].currentNumberOfPeople").value(0))
                .andExpect(jsonPath("$.data[0].capacity").value(24))
                .andExpect(jsonPath("$.data[0].memo").value("마스크 꼭 착용하세요"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
        then(eventService).should().getEvents(any(), any(), any(), any(), any());
    }

    @DisplayName("[API] [GET] 이벤트 리스트 조회 + 잘못된 검색 파라미터")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsFailListStandardResponse() throws Exception{
        // Given

        // When & Then
        mvc.perform(
                        get("/api/events")
                                .queryParam("placeId","0")
                                .queryParam("eventName","오")
                                .queryParam("eventStatus", EventStatus.OPENED.name())
                                .queryParam("eventStartDateTime","2021-01-01T00:00:00")
                                .queryParam("eventEndDateTime","2021-01-02T00:00:00")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.VALIDATION_ERROR.getCode()))
                .andExpect(jsonPath("$.message").value(containsString(ErrorCode.VALIDATION_ERROR.getMessage())));
        then(eventService).shouldHaveNoInteractions();
    }

    private EventDTO createEventDTO(
    ){
        return EventDTO.builder()
                .placeId(1L)
                .eventName("오후 운동")
                .eventStatus(EventStatus.OPENED)
                .eventStartDatetime(LocalDateTime.of(2021,1,1,13,0,0))
                .eventEndDatetime(LocalDateTime.of(2021,1,1,13,0,0))
                .currentNumberOfPeople(0)
                .capacity(24)
                .memo("마스크 꼭 착용하세요")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }
}