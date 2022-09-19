package com.springbatch.hellospringbatch.core.repository;

import com.springbatch.hellospringbatch.core.domain.PlainText;
import org.springframework.data.domain.Page;
// Pageable import 조심할것... java.awt.print 에도 같은게 있다.
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlainTextRepository extends JpaRepository<PlainText, Integer> {
    Page<PlainText> findBy(Pageable pageable);
}
