package com.getinline.getinline.error;

import com.getinline.getinline.constant.ErrorCode;
import com.getinline.getinline.dto.APIErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class BaseErrorController implements ErrorController {

    // Header에 TEXT_HTML_VALUE 를 가지고 있는것만 HTML 페이지로 이동 외에는 json 응답
    @GetMapping(path = "/error", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletResponse response){
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        // 400번대 Error 라면 BAD_REQUEST 를 아니라면 INTERNAL_ERROR 를 던져준다.
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        return new ModelAndView("error", Map.of(
                "statusCode", status.value(),
                "errorCode", errorCode,
                "message", errorCode.getMessage(status.getReasonPhrase())
                ),
                status
        );
    }

    @GetMapping("/error")
    public ResponseEntity<APIErrorResponse> error(HttpServletResponse response){
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        // 400번대 Error 라면 BAD_REQUEST 를 아니라면 INTERNAL_ERROR 를 던져준다.
        ErrorCode errorCode = status.is4xxClientError() ? ErrorCode.BAD_REQUEST : ErrorCode.INTERNAL_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(false, errorCode));
    }

}
