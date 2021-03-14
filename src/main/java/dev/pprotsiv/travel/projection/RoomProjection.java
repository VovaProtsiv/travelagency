package dev.pprotsiv.travel.projection;

import java.math.BigDecimal;

public interface RoomProjection {
    String getId();

    String getName();

    Integer getSleeps();

    Integer getHotelId();

    BigDecimal getPrice();
}
