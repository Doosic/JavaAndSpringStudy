package com.getinline.getinline.repository;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.constant.PlaceType;
import com.getinline.getinline.domain.Event;
import com.getinline.getinline.domain.Place;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EventRepositoryTest {

    private final EventRepository sut;
    private final TestEntityManager testEntityManager;

    EventRepositoryTest(
            @Autowired EventRepository sut,
            @Autowired TestEntityManager testEntityManager
    ) {
        this.sut = sut;
        this.testEntityManager = testEntityManager;
    }

    @DisplayName("asdasd")
    @Test
    void test(){
        // Given

        // When
        Iterable<Event> events = sut.findAll(new BooleanBuilder());

        // Then
        assertThat(events).contains();
    }

//    private Event createEvent(Place place){
//        return createEvent(
//                place,
//                "test event",
//                EventStatus.ABORTED,
//
//        );
//    }

    private Event createEvent(
            long id,
            long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ) {
        Event event = Event.of(
                createPlace(placeId),
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                0,
                24,
                "마스크 꼭 착용하세요"
        );
        ReflectionTestUtils.setField(event, "id", id);

        return event;
    }

    private Place createPlace(){return createPlace(1L);}

    private Place createPlace(long id){
        Place place = Place.of(PlaceType.COMMON, "test place", "test address", "010-1234-1234", 24, "22");
        ReflectionTestUtils.setField(place, "id", id);

        return place;
    }


}