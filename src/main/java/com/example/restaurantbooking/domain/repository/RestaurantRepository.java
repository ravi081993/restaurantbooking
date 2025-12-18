package com.example.restaurantbooking.domain.repository;

import com.example.restaurantbooking.domain.model.RestaurantData;

public interface RestaurantRepository {

    RestaurantData getRestaurantData(int restaurantId);
}
