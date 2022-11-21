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
@NoArgsConstructor
@Builder
public class EventRequest {

    @NotNull
    @Positive
    private Long placeId;

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


    public static EventRequest of(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ){
        return new EventRequest(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                currentNumberOfPeople,
                capacity,
                memo
        );
    }

    public EventDTO toDTO(){
        return EventDTO.of(
                null,
                null, // TODO: 여기를 반드시 적절히 고쳐야 사용할 수 있음
                this.eventName,
                this.eventStatus,
                this.eventStartDatetime,
                this.eventEndDatetime,
                this.currentNumberOfPeople,
                this.capacity,
                this.memo,
                null,
                null
        );
    }

}
