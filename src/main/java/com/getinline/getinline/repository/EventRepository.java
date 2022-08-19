package com.getinline.getinline.repository;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.domain.Event;
import com.getinline.getinline.dto.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// TODO: 인스턴스 생성 편의를 위해 임시로 default 사용
public interface EventRepository extends
        JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event>{

    default List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ){
        List<EventDTO> result = new ArrayList<>();
        EventDTO eventDTO = EventDTO.builder()
                .placeId(placeId)
                .eventName(eventName)
                .eventStatus(eventStatus)
                .eventStartDatetime(eventStartDateTime)
                .eventEndDatetime(eventEndDateTime)
                .currentNumberOfPeople(5)
                .capacity(24)
                .memo("마스크 꼭 착용하세요")
                .build();
        result.add(eventDTO);

        return result;
    }

    default Optional<EventDTO> findEvent(Long eventId){
        return Optional.empty();
    }

    default boolean insertEvent(EventDTO eventDTO){
        return false;
    }

    default boolean updateEvent(Long eventId, EventDTO eventDTO){
        return false;
    }

    default boolean deleteEvent(Long eventId){
        return false;
    }
}
