package com.getinline.getinline.service;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.dto.EventDTO;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    public List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ){
        return List.of();
    }

    public Optional<EventDTO> findEvent(Long eventId){
        return Optional.empty();
    }

    public boolean createEvent(EventDTO eventDTO){
        return true;
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO){
        return true;
    }

    public boolean removeEvent(Long eventId){
        return true;
    }

}
