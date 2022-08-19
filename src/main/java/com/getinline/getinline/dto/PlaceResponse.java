package com.getinline.getinline.dto;

import com.getinline.getinline.constant.PlaceType;
import com.getinline.getinline.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PlaceResponse {
    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private String capacity;
    private String memo;

    public static PlaceResponse of(Place place){
        return PlaceResponse.builder()
                .placeType(place.getPlaceType())
                .placeName(place.getPlaceName())
                .address(place.getAddress())
                .phoneNumber(place.getPhoneNumber())
                .capacity(place.getCapacity().toString())
                .memo(place.getMemo())
                .build();
    }
}
