package com.getinline.getinline.repository;

import com.getinline.getinline.constant.EventStatus;
import com.getinline.getinline.domain.Event;
import com.getinline.getinline.domain.QEvent;
import com.getinline.getinline.dto.EventDTO;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends
        JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event>,
        QuerydslBinderCustomizer<QEvent> {

    @Override
    default void customize(QuerydslBindings bindings, QEvent root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.place, root.eventName, root.eventStatus, root.eventStartDatetime, root.eventEndDatetime);
        bindings.bind(root.eventName).first(((path, value) -> path.like("%" + value + "%")));
        bindings.bind(root.eventStartDatetime).first(ComparableExpression::goe);
        bindings.bind(root.eventEndDatetime).first(ComparableExpression::loe);
    }

}
