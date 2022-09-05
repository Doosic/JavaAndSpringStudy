package com.getinline.getinline.dto;

import com.getinline.getinline.constant.PlaceType;
import com.getinline.getinline.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class PlaceResponse {

    private Long id;
    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private Integer capacity;
    private String memo;

    public PlaceResponse(Long id, PlaceType placeType, String placeName, String address, String phoneNumber, Integer capacity, String memo) {
        this.id = id;
        this.placeType = placeType;
        this.placeName = placeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.memo = memo;
    }

    public static PlaceResponse of(Place place){
        return PlaceResponse.builder()
                .placeType(place.getPlaceType())
                .placeName(place.getPlaceName())
                .address(place.getAddress())
                .phoneNumber(place.getPhoneNumber())
                .capacity(place.getCapacity())
                .memo(place.getMemo())
                .build();
    }

    public static PlaceResponse of(
            Long id,
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ) {
        return new PlaceResponse(id, placeType, placeName, address, phoneNumber, capacity, memo);
    }
}
