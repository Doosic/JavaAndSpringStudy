package com.getinline.getinline.service;

import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.dto.EventDTO;
import com.getinline.getinline.exception.GeneralException;
import com.getinline.getinline.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> getEvents(Predicate predicate) {
        try {
            return StreamSupport.stream(eventRepository.findAll((Sort) predicate).spliterator(), false)
                    .map(EventDTO::of)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public Optional<EventDTO> getEvent(Long eventId){
        return null;
    }

    public boolean createEvent(EventDTO eventDTO){
        return false;
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO){
        return false;
    }

    public boolean removeEvent(Long eventId){
        return false;
    }

}
