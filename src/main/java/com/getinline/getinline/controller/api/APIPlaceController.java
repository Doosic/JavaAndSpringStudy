package com.getinline.getinline.controller.api;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public List<String> getPlaces() {
        return List.of("place1", "place2");
    }

    @PostMapping("/places")
    public Boolean createPlaces() {
        return true;
    }

    @GetMapping("/places/{placeId}")
    public String getPlaces(@PathVariable Integer placeId) {
        return "place" + placeId;
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
