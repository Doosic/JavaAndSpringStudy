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
public class EventDTO {

    private Place place;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String memo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static EventDTO of(Event event){
        return EventDTO.builder()
                .place(event.getPlace())
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

    public Event toEntity() {
        return Event.builder()
                .place(place)
                .eventName(eventName)
                .eventStatus(eventStatus)
                .eventStartDatetime(eventStartDatetime)
                .eventEndDatetime(eventEndDatetime)
                .currentNumberOfPeople(currentNumberOfPeople)
                .capacity(capacity)
                .memo(memo)
                .build();
    }

    public Event updateEntity(Event event) {
        if (place != null) { event.setPlace(place); }
        if (eventName != null) { event.setEventName(eventName); }
        if (eventStatus != null) { event.setEventStatus(eventStatus); }
        if (eventStartDatetime != null) { event.setEventStartDatetime(eventStartDatetime); }
        if (eventEndDatetime != null) { event.setEventEndDatetime(eventEndDatetime); }
        if (currentNumberOfPeople != null) { event.setCurrentNumberOfPeople(currentNumberOfPeople); }
        if (capacity != null) { event.setCapacity(capacity); }
        if (memo != null) { event.setMemo(memo); }

        return event;
    }
}
