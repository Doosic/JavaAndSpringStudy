package com.springbatch.hellospringbatch.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
// @DynamicUpdate: Entity 의 일부 컬럼값 변경시 일부값만 변경되는 쿼리 실행
@DynamicUpdate
@Table(name = "plain_text")
public class PlainText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;


}
