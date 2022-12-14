package com.getinline.getinline.repository;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.domain.Event;
import com.getinline.getinline.dto.EventViewResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("DB - 이벤트")
@DataJpaTest
class EventRepositoryTest {

    private final EventRepository eventRepository;

    public EventRepositoryTest(@Autowired EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    /*
        @Setter
        @ManyToOne(optional = false)
        private Place place;
        디폴트 동작이기에 (EAGER FETCH) 를 사용하고 있다.

        SpringDataJPA 의 쿼리메서드 findAll() 를 사용하여 조회되는 쿼리 확인시
        Event 랑 Place 를 조인한 쿼리가 아닌 독립적으로 조회한다.
        Event 따로 Place 따로 조회

        이럴때에는 findAll() 이 아닌 JPQL 을 직접 작성해서 join 을 영속성 컨텍스트에 알려줘야 함 (ex: querydsl)
        N + 1 의 문제가 이런것이다.
    */
    @Test
    void testN1() {
        List<Event> list = eventRepository.findAll();
    }

    // TODO: 추후 data.sql 없이 테스트 코드가 돌아가게 만들어보자.
    @Test
    void givenSearchParams_whenFindingEventViewResponse_thenReturnsEventViewResponsePage(){
       // Given

       // When
       Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
               "배드민턴",
               "운동1",
               EventStatus.OPENED,
               LocalDateTime.of(2021,1,1,0,0,0),
               LocalDateTime.of(2021,1,2,0,0,0),
               PageRequest.of(0,5)
       );

       // Then
        assertThat(eventPage.getTotalPages()).isEqualTo(1);
        assertThat(eventPage.getNumberOfElements()).isEqualTo(1);
        assertThat(eventPage.getContent().get(0))
                .hasFieldOrPropertyWithValue("placeName", "서울 배드민턴장")
                .hasFieldOrPropertyWithValue("eventName", "운동1")
                .hasFieldOrPropertyWithValue("eventStatus", EventStatus.OPENED)
                .hasFieldOrPropertyWithValue("eventStartDatetime", LocalDateTime.of(2021,1,1,9,0,0))
                .hasFieldOrPropertyWithValue("eventEndDatetime", LocalDateTime.of(2021,1,1,12,0,0));
    }

    @DisplayName("이벤트 뷰 데이터 검색어에 따른 조회 결과가 없으면, 빈 데이터를 페이징 정보와 함께 리턴한다.")
    @Test
    void givenSearchParams_whenFindingNonexistentEventViewPage_thenReturnsEmptyEventViewResponsePage() {
        // Given

        // When
        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                "없은 장소",
                "없는 이벤트",
                null,
                LocalDateTime.of(1000, 1, 1, 1, 1, 1),
                LocalDateTime.of(1000, 1, 1, 1, 1, 0),
                PageRequest.of(0, 5)
        );

        // Then
        Assertions.assertThat(eventPage).hasSize(0);
    }

    @DisplayName("이벤트 뷰 데이터를 검색 파라미터 없이 페이징 값만 주고 조회하면, 전체 데이터를 페이징 처리하여 리턴한다.")
    @Test
    void givenPagingInfoOnly_whenFindingEventViewPage_thenReturnsEventViewResponsePage() {
        // Given

        // When
        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                null,
                null,
                null,
                null,
                null,
                PageRequest.of(0, 5)
        );

        // Then
        Assertions.assertThat(eventPage).hasSize(5);
    }

    @DisplayName("이벤트 뷰 데이터를 페이징 정보 없이 조회하면, 에러를 리턴한다.")
    @Test
    void givenNothing_whenFindingEventViewPage_thenThrowsError() {
        // Given

        // When
        Throwable t = catchThrowable(() -> eventRepository.findEventViewPageBySearchParams(
                null,
                null,
                null,
                null,
                null,
                null
        ));

        // Then
        Assertions.assertThat(t).isInstanceOf(InvalidDataAccessApiUsageException.class);
    }

}