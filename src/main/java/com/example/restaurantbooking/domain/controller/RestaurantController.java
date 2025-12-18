package com.example.restaurantbooking.domain.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurantbooking.domain.model.RestaurantData;
import com.example.restaurantbooking.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}/data")
    public RestaurantData getRestaurantData(@PathVariable int id) {
        return service.getRestaurantData(id);
    }
}
