package com.getinline.getinline.integration;

import com.getinline.getinline.dto.EventDTO;
import com.getinline.getinline.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EventServiceSociableTest {

    @Autowired private EventService sut;

    @DisplayName("검색 조건 없이 이벤트 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnsEntireEventList(){
        // Given
        // 실제 서비스를 테스트하기 때문에 mocking 은 하지 않는다.!!!

        // When
        List<EventDTO> list = sut.getEvents(null,null,null,null,null);

        // Then
        assertThat(list).hasSize(0);
    }
}
