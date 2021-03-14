package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.dto.OrderDto;

import java.math.BigDecimal;

public interface AccountService {
    BigDecimal getTotalAmount(OrderDto dto);
}
