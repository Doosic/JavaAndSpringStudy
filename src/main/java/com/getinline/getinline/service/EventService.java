package com.getinline.getinline.service;

import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.dto.EventDTO;
import com.getinline.getinline.exception.GeneralException;
import com.getinline.getinline.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<EventDTO> getEvents(com.querydsl.core.types.Predicate predicate) {
        try {
            return StreamSupport.stream(eventRepository.findAll(predicate).spliterator(), false)
                    .map(EventDTO::of)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        try {
            return null;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public Optional<EventDTO> getEvent(Long eventId) {
        try {
            return eventRepository.findById(eventId).map(EventDTO::of);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public boolean createEvent(EventDTO eventDTO) {
        try {
            if (eventDTO == null) {
                return false;
            }

            eventRepository.save(eventDTO.toEntity(null));
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public boolean modifyEvent(Long eventId, EventDTO dto) {
        try {
            if (eventId == null || dto == null) {
                return false;
            }

            eventRepository.findById(eventId)
                    .ifPresent(event -> eventRepository.save(dto.updateEntity(event)));

            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public boolean removeEvent(Long eventId) {
        try {
            if (eventId == null) {
                return false;
            }

            eventRepository.deleteById(eventId);
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

}
