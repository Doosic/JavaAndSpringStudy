package com.springBootStudy;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
public class StudentEntity implements Serializable {

    private String name;
    private Integer age;
    private Grade grade;

    public enum Grade {
        A, B, C, D, E, F
    }
}
