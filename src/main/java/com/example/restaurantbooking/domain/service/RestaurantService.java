package com.example.restaurantbooking.domain.service;

import com.example.restaurantbooking.domain.model.RestaurantData;
import com.example.restaurantbooking.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public RestaurantData getRestaurantData(int restaurantId) {
        return repository.getRestaurantData(restaurantId);
    }
}
