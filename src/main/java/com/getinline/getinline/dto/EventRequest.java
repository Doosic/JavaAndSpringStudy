package com.getinline.getinline.dto;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EventRequest {

    @NotNull
    @Positive
    private Place place;

    @NotBlank
    private String eventName;

    @NotNull
    private EventStatus eventStatus;

    @NotNull
    private LocalDateTime eventStartDatetime;

    @NotNull
    private LocalDateTime eventEndDatetime;

    @NotNull
    @PositiveOrZero
    private Integer currentNumberOfPeople;

    @NotNull
    @Positive
    private Integer capacity;

    private String memo;


    public static EventRequest of(EventDTO event){
        return EventRequest.builder()
                .place(event.getPlace())
                .eventName(event.getEventName())
                .eventStatus(event.getEventStatus())
                .eventStartDatetime(event.getEventStartDatetime())
                .eventEndDatetime(event.getEventEndDatetime())
                .currentNumberOfPeople(event.getCurrentNumberOfPeople())
                .capacity(event.getCapacity())
                .memo(event.getMemo())
                .build();
    }

    public EventDTO toDTO(){
        return EventDTO.builder()
                .place(this.place)
                .eventName(this.eventName)
                .eventStatus(this.eventStatus)
                .eventStartDatetime(this.eventStartDatetime)
                .eventEndDatetime(this.eventEndDatetime)
                .currentNumberOfPeople(this.currentNumberOfPeople)
                .capacity(this.capacity)
                .memo(this.memo)
                .build();
    }

}
