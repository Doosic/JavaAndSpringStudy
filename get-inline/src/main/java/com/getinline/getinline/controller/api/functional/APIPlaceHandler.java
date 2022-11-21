package com.getinline.getinline.controller.api.functional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.ok;
import static org.springframework.web.servlet.function.ServerResponse.created;

@Component
public class APIPlaceHandler {

    public ServerResponse getPlaces(ServerRequest request) throws Exception {
        return ok().body(List.of("place1", "place2"));
    }

    // TODO: 1은 구현할 때 제대로 넣어주자
    public ServerResponse createPlaces(ServerRequest request) throws Exception {
        return created(URI.create("/api/places/1")).body(true);
    }

    public ServerResponse getPlace(ServerRequest request) throws Exception {
        return ok().body("place" + request.pathVariable("placeId"));
    }

    public ServerResponse modifyPlace(ServerRequest request) throws Exception {
        return ok().body(true);
    }

    public ServerResponse removePlace(ServerRequest request) throws Exception {
        return ok().body(true);
    }
}
