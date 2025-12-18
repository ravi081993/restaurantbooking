package com.example.restaurantbooking.domain.repository.Impl;
import com.example.restaurantbooking.domain.model.Booking;
import com.example.restaurantbooking.domain.model.RestaurantData;
import com.example.restaurantbooking.domain.model.Table;
import com.example.restaurantbooking.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RestaurantData getRestaurantData(int restaurantId) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("get_restaurant_data");

        query.registerStoredProcedureParameter("in_restaurant_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("out_total_bookings", Integer.class, ParameterMode.OUT);

        query.setParameter("in_restaurant_id", restaurantId);

        boolean hasResults = query.execute();

        List<Table> tables = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();

        while (hasResults) {
            List<Object[]> rows = query.getResultList();

            if (!rows.isEmpty()) {
                // Heuristic based on column count to separate result sets
                int colCount = rows.get(0).length;
                if (colCount == 3 && rows.get(0)[2] instanceof Boolean) {
                    for (Object[] r : rows) {
                        tables.add(new Table(
                            castInt(r[0]), castInt(r[1]), (Boolean) r[2]
                        ));
                    }
                } else {
                    for (Object[] r : rows) {
                        bookings.add(new Booking(
                            castInt(r[0]), (String) r[1], (Timestamp) r[2]
                        ));
                    }
                }
            }

            hasResults = query.hasMoreResults();
        }

        Integer totalBookings = (Integer) query.getOutputParameterValue("out_total_bookings");

        return new RestaurantData(tables, bookings, totalBookings);
    }

    private Integer castInt(Object o) {
        if (o instanceof Integer i) return i;
        if (o instanceof Number n) return n.intValue();
        return null;
    }
}
