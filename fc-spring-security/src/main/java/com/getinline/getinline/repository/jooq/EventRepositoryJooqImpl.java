//package com.getinline.getinline.repository.jooq;
//
//import com.getinline.getinline.constant.EventStatus;
//import com.getinline.getinline.dto.EventViewResponse;
//import com.getinline.getinline.tables.Event;
//import com.getinline.getinline.tables.Place;
//import lombok.RequiredArgsConstructor;
//import org.jooq.Condition;
//import org.jooq.DSLContext;
//import org.jooq.SelectField;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.jooq.impl.DSL.trueCondition;
//
//@RequiredArgsConstructor
//@Repository
//public class EventRepositoryJooqImpl implements EventRepositoryJooq{
//
//    private final DSLContext dslContext;
//
//    @Override
//    public Page<EventViewResponse> findEventViewPageBySearchParams(
//            String placeName,
//            String eventName,
//            EventStatus eventStatus,
//            LocalDateTime eventStartDatetime,
//            LocalDateTime eventEndDatetime,
//            Pageable pageable
//    ) {
//        final Event event = Event.EVENT;
//        final Place place = Place.PLACE;
//
//        Condition condition = trueCondition();
//
//        SelectField<?>[] select = {
//            event.ID,
//            place.PLACE_NAME,
//            event.EVENT_NAME,
//            event.EVENT_STATUS,
//            event.EVENT_START_DATETIME,
//            event.EVENT_END_DATETIME,
//            event.CURRENT_NUMBER_OF_PEOPLE,
//            event.CAPACITY,
//            event.MEMO
//        };
//
//        if (placeName != null && !placeName.isBlank()){
//            condition = condition.and(place.PLACE_NAME.containsIgnoreCase(placeName));
//        }
//        if (eventName != null && !eventName.isBlank()){
//            condition = condition.and(event.EVENT_NAME.containsIgnoreCase(eventName));
//        }
//        if (eventStatus != null){
//            condition = condition.and(event.EVENT_STATUS.containsIgnoreCase(eventStatus));
//        }
//        if (eventStartDatetime != null){
//            condition = condition.and(event.EVENT_START_DATETIME.greaterOrEqual(eventStartDatetime));
//        }
//        if (eventEndDatetime != null){
//            condition = condition.and(event.EVENT_END_DATETIME.lessOrEqual(eventEndDatetime));
//        }
//
//        int count = dslContext
//                .selectCount()
//                .from(event)
//                .innerJoin(place)
//                .onKey()
//                .where(condition)
//                .fetchSingleInto(int.class);
//
//        List<EventViewResponse> pagedList = dslContext
//                .select(select)
//                .from(event)
//                .innerJoin(place)
//                .onKey()
//                .where(condition)
//                .limit(pageable.getOffset(), pageable.getPageSize())
//                .fetchInto(EventViewResponse.class);
//
//        return new PageImpl<>(pagedList, pageable, count);
//    }
//}
