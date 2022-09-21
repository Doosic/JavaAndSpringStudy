package com.springbatch.hellospringbatch.job.validator;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@AllArgsConstructor
public class LocalDateParameterValidator implements JobParametersValidator {

    private String parameterName;

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String localDate = parameters.getString("targetDate");

        // has: 가지다, text: 텍스트
        // hasText: 텍스트를 가지고 있다. 즉, 값이 있는지 없는지 확인가능.
        if (!StringUtils.hasText(localDate)) {
            throw new JobParametersInvalidException(parameterName + "가 빈 문자열이거나 존재하지 않습니다.");
        }

        // LocalDate 타입으로 변환되는지 확인하기 위해 parse() 를 먼저 해준다.
        // 다만 날짜로 변환 불가능시에 Exception 발생. try,catch 로 처리해주자.
        try {
            LocalDate.parse(localDate);
        }catch (Exception e){
            throw new JobParametersInvalidException(parameterName + "가 날짜 형식에 문자열이 아닙니다.");
        }

    }
}
