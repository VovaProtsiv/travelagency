package dev.pprotsiv.travel.projection;

import java.time.LocalDate;

public interface OrderProjection {
    String getId();

    LocalDate getCheckIn();

    LocalDate getCheckOut();

    String getUserId();

    String getState();


}
