package com.getinline.getinline.service;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 이벤트 서비스
 *
 * @author doosic
 */
public interface EventServiceTEST {
    // 여기는 구현은 하지않고 이렇게 쓸수 있다는 것만.
    /**
     * 검색어들을 받아서 이벤트 리스트를 반환
     *
     * @param placeId 장소 ID
     * @param eventName 이벤트 이름
     * @param eventStatus 이벤트 상태
     * @param eventStartDatetime 시작시간
     * @param eventEndDatetime 종료시간
     * @return
     */
    List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    );

}
