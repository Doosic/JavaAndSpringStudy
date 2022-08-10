package com.getinline.getinline.controller.api;


import com.getinline.getinline.constant.PlaceType;
import com.getinline.getinline.domain.Place;
import com.getinline.getinline.dto.APIDataResponse;
import com.getinline.getinline.dto.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceDTO>> getPlaces() {
        Place place = Place.builder()
                .placeType(PlaceType.COMMON)
                .placeName("야미배드민턴장")
                .address("서울시 강남구")
                .phoneNumber("010-1234-1234")
                .capacity(String.valueOf(30))
                .memo("신장개업")
                .build();
        return APIDataResponse.of(List.of(PlaceDTO.of(place)));
    }

    @PostMapping("/places")
    public Boolean createPlaces() {
        return true;
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceDTO> getPlaces(
            @PathVariable Integer placeId
    ) {
        if(placeId.equals(2)){
            return APIDataResponse.of(null);
        }

        Place place = Place.builder()
                .placeType(PlaceType.COMMON)
                .placeName("야미배드민턴장")
                .address("서울시 강남구")
                .phoneNumber("010-1234-1234")
                .capacity(String.valueOf(30))
                .memo("신장개업")
                .build();
        return APIDataResponse.of(PlaceDTO.of(place));
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlaces(@PathVariable Integer placeId) {
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlaces(@PathVariable Integer placeId) {
        return true;
    }
}
