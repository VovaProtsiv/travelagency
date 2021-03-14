package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.dto.OrderDto;
import dev.pprotsiv.travel.service.AccountService;
import dev.pprotsiv.travel.service.RoomService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Period;

@Service
public class AccountServiceImpl implements AccountService {
    private final RoomService roomService;

    public AccountServiceImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public BigDecimal getTotalAmount(OrderDto dto) {
        int days = Period.between(dto.getCheckIn(), dto.getCheckOut()).getDays();
        return totalRoomsPricePerDay(dto).multiply(new BigDecimal(days));
    }

    private BigDecimal totalRoomsPricePerDay(OrderDto dto) {
        return dto.getRooms().stream()
                .map(e -> roomService.readById(Long.parseLong(e)).getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
