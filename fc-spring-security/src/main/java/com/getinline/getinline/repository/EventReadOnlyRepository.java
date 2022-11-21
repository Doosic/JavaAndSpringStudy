package com.getinline.getinline.repository;

import com.getinline.getinline.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
// Repository CRUD 기능을 사용할 수 있으나 선택적으로 사용이 가능하다.
public interface EventReadOnlyRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAllById(Iterable<ID> ids);
    List<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);
}
