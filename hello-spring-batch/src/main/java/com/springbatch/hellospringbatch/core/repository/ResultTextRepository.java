package com.springbatch.hellospringbatch.core.repository;

import com.springbatch.hellospringbatch.core.domain.ResultText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResultTextRepository extends JpaRepository<ResultText, Integer> {
    Page<ResultText> findBy(Pageable pageable);
}
