package com.getinline.getinline.domain;

import com.getinline.getinline.constant.PlaceType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Place {
    private Long id;

    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private String capacity;
    private String memo;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
