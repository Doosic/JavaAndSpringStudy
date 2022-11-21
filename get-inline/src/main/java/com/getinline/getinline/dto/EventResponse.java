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

    private Long id;
    private PlaceDTO placeDTO;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String memo;

    public static EventResponse of(EventDTO event) {
        return EventResponse.builder()
                .id(event.getId())
                .placeDTO(event.getPlaceDTO())
                .eventName(event.getEventName())
                .eventStatus(event.getEventStatus())
                .eventStartDatetime(event.getEventStartDatetime())
                .eventEndDatetime(event.getEventEndDatetime())
                .currentNumberOfPeople(event.getCurrentNumberOfPeople())
                .capacity(event.getCapacity())
                .memo(event.getMemo())
                .build();
    }

    public static EventResponse of(
            Long id,
            PlaceDTO placeDto,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new EventResponse(
                id,
                placeDto,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                currentNumberOfPeople,
                capacity,
                memo
        );
    }

    public static EventResponse from(EventDTO eventDTO) {
        if (eventDTO == null) { return null; }
        return EventResponse.of(
                eventDTO.getId(),
                eventDTO.getPlaceDTO(),
                eventDTO.getEventName(),
                eventDTO.getEventStatus(),
                eventDTO.getEventStartDatetime(),
                eventDTO.getEventEndDatetime(),
                eventDTO.getCurrentNumberOfPeople(),
                eventDTO.getCapacity(),
                eventDTO.getMemo()
        );
    }

    public String getPlaceName() {
        return this.placeDTO.getPlaceName();
    }

}
