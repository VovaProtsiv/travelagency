package dev.pprotsiv.travel.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderProjection {
    String getId();

    LocalDate getCheckIn();

    LocalDate getCheckOut();

    String getUserId();

    String getHotelId();

    String getHotelName();

    String getState();

    BigDecimal getTotalAmount();
}
