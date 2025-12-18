package com.example.restaurantbooking.domain.model;

import java.util.List;

public record RestaurantData( List<Table> tables, List<Booking> bookings,Integer totalbookings) {
    
}
