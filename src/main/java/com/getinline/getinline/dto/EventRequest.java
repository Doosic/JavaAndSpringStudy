package com.getinline.getinline.dto;

import com.getinline.getinline.constant.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EventRequest {

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

    public EventDTO toDTO(){
        return EventDTO.builder()
                .placeId(this.placeId)
                .eventName(this.eventName)
                .eventStatus(this.eventStatus)
                .eventStartDatetime(this.eventStartDatetime)
                .eventEndDatetime(this.eventEndDatetime)
                .currentNumberOfPeople(this.currentNumberOfPeople)
                .capacity(this.capacity)
                .memo(this.memo)
                .createdAt(this.createdAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }

}
