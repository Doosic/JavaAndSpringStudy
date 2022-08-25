package com.getinline.getinline.dto;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.domain.Event;
import com.getinline.getinline.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EventResponse {

    private Long placeId;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String memo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static EventResponse of(EventDTO event){
        return EventResponse.builder()
                .placeId(event.getPlaceId())
                .eventName(event.getEventName())
                .eventStatus(event.getEventStatus())
                .eventStartDatetime(event.getEventStartDatetime())
                .eventEndDatetime(event.getEventEndDatetime())
                .currentNumberOfPeople(event.getCurrentNumberOfPeople())
                .capacity(event.getCapacity())
                .memo(event.getMemo())
                .createdAt(event.getCreatedAt())
                .modifiedAt(event.getModifiedAt())
                .build();
    }

    public static EventResponse from (EventDTO eventDTO){
        if(eventDTO == null){return null;}
        return EventResponse.of(
                eventDTO
        );
    }
}
