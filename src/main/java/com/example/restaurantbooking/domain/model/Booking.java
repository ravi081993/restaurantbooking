package com.example.restaurantbooking.domain.model;

import java.sql.Timestamp;

public record Booking(Integer bookingId,String customerName, Timestamp bookingTime) {
    
}
