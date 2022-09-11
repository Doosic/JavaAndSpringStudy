//package com.getinline.getinline.repository.jooq;
//
//import com.getinline.getinline.constant.EventStatus;
//import com.getinline.getinline.dto.EventViewResponse;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.time.LocalDateTime;
//
//public interface EventRepositoryJooq {
//    Page<EventViewResponse> findEventViewPageBySearchParams(
//            String placeName,
//            String eventName,
//            EventStatus eventStatus,
//            LocalDateTime eventStartDatetime,
//            LocalDateTime eventEndDatetime,
//            Pageable pageable
//    );
//}
