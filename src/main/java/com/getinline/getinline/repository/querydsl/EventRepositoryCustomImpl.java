package com.getinline.getinline.repository.querydsl;

import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.domain.Event;
import com.getinline.getinline.domain.QEvent;
import com.getinline.getinline.dto.EventViewResponse;
import com.getinline.getinline.exception.GeneralException;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventRepositoryCustomImpl extends QuerydslRepositorySupport implements EventRepositoryCustom{

    /*
        첫 번째로 Querydsl 을 사용하기 위해 넣어야 할 것!
        extends QuerydslRepositorySupport
        생성자가 필요하다. 우리가 사용할 Entity class 를 super(Entity.class) 에 넣어준다.
     */
    public EventRepositoryCustomImpl() {
        super(Event.class);
    }

    @Override
    public Page<EventViewResponse> findEventViewPageBySearchParams(
            String placeName,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Pageable pageable
    ) {
        QEvent event = QEvent.event;

        JPQLQuery<EventViewResponse> query = from(event)
                .select(Projections.constructor(
                        EventViewResponse.class,
                        event.id,
                        event.place.placeName,
                        event.eventName,
                        event.eventStatus,
                        event.eventStartDatetime,
                        event.eventEndDatetime,
                        event.currentNumberOfPeople,
                        event.capacity,
                        event.memo
                ));
        /*
            where 을 넣고싶으나 전부 넣어준다면 데이터가 있으나 없으나 where 을 수행하게 된다.
            좀더 다이나믹하게 동작하기위해 if 문을 통하여 만들어주자.
            다음과 같은 null 검사들을 한번에 해주는 라이브러리도 있다.(StreamUtils 라고 들은것 같다.)
            당연한것이지만 else if 가 아닌 if 로 개별 동작해야된다. 나머지가 무시되기 떄문에.
            - goe(이상), loe(이하) 가 있다.
            - containsIgnoreCase 포함되는것
            - eq 같은것
        */
        if (placeName != null && !placeName.isBlank()){
            query.where(event.place.placeName.containsIgnoreCase(placeName));
        }
        if (eventName != null && !eventName.isBlank()){
            query.where(event.eventName.containsIgnoreCase(eventName));
        }
        if (eventStatus != null){
            query.where(event.eventStatus.eq(eventStatus));
        }
        if (eventStartDatetime != null){
            query.where(event.eventStartDatetime.goe(eventStartDatetime));
        }
        if (eventEndDatetime != null){
            query.where(event.eventEndDatetime.loe(eventEndDatetime));
        }

        // applyPagination 는 NullPointException 을 일으킬 수 있다.
        List<EventViewResponse> events = Optional.ofNullable(getQuerydsl())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR, "Spring Data JPA 로부터 Qeurydsl 인스턴스를 못 가져옴"))
                .applyPagination(pageable, query)
                .fetch();

        return new PageImpl<>(events, pageable, query.fetchCount());
    }
}
