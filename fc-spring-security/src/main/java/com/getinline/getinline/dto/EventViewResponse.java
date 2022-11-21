package com.getinline.getinline.dto;

import com.getinline.getinline.constant.EventStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
    이미 응답전용 Response가 있으나
    View 전용 Response 를 생성
 * */
public class EventViewResponse {

    private Long id;
    private String placeName;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String memo;

    public EventViewResponse(Long id, String placeName, String eventName, EventStatus eventStatus, LocalDateTime eventStartDatetime, LocalDateTime eventEndDatetime, Integer currentNumberOfPeople, Integer capacity, String memo) {
        this.id = id;
        this.placeName = placeName;
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
        this.currentNumberOfPeople = currentNumberOfPeople;
        this.capacity = capacity;
        this.memo = memo;
    }

    public static EventViewResponse of(
            Long id,
            String placeName,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new EventViewResponse(
                id,
                placeName,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                currentNumberOfPeople,
                capacity,
                memo
        );
    }

    public static EventViewResponse from(EventDTO eventDTO) {
        if (eventDTO == null) { return null; }
        return EventViewResponse.of(
                eventDTO.getId(),
                eventDTO.getPlaceDTO().getPlaceName(),
                eventDTO.getEventName(),
                eventDTO.getEventStatus(),
                eventDTO.getEventStartDatetime(),
                eventDTO.getEventEndDatetime(),
                eventDTO.getCurrentNumberOfPeople(),
                eventDTO.getCapacity(),
                eventDTO.getMemo()
        );
    }


}
