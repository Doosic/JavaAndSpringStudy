package com.getinline.getinline.repository.jooq;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.dto.EventViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class EventRepositoryJooqImpl implements EventRepositoryJooq{
    @Override
    public Page<EventViewResponse> findEventViewPageBySearchParams(String placeName, String eventName, EventStatus eventStatus, LocalDateTime eventStartDatetime, LocalDateTime eventEndDatetime, Pageable pageable) {
        return null;
    }
}
