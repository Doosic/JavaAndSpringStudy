package com.getinline.getinline.controller;

import com.getinline.getinline.constant.PlaceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {

    // @RequestParam 을 명시하지 않을시에는 Optional 해진다. @RequestParam(required = false) 와 같이
    // 필수값이 아니게된다. 이 부분은 검색을 하는 부분인데 값이 있을수도 있고 없을수도 있기 때문이다.
    @GetMapping("/places")
    public ModelAndView adminPlaces(
            PlaceType placeType,
            String placeName,
            String address
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeType", placeType);
        map.put("placeName", placeName);
        map.put("address", address);

        return new ModelAndView("admin/places", map);
    }

    @GetMapping("/places/{placeId}")
    public String adminPlaceDetail(@PathVariable Integer placeId){
        return "admin/place-detail";
    }

    @GetMapping("/events")
    public String adminEvents() {
        return "admin/events";
    }

    @GetMapping("/events/{eventId]")
    public String adminEventDetail(@PathVariable Integer eventId){
        return "admin/event-detail";
    }

}
