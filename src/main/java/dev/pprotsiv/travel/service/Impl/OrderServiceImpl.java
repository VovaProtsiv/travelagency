package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.dto.OrderDto;
import dev.pprotsiv.travel.dto.OrderDtoMapper;
import dev.pprotsiv.travel.exception.BookedRoomsExceptions;
import dev.pprotsiv.travel.exception.IllegalDateException;
import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.model.State;
import dev.pprotsiv.travel.projection.OrderProjection;
import dev.pprotsiv.travel.repo.OrderRepository;
import dev.pprotsiv.travel.service.AccountService;
import dev.pprotsiv.travel.service.OrderService;
import dev.pprotsiv.travel.service.RoomService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Period;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService, AccountService {
    private final OrderRepository orderRepository;
    private final RoomService roomService;

    public OrderServiceImpl(OrderRepository orderRepository, RoomService roomService) {
        this.orderRepository = orderRepository;
        this.roomService = roomService;
    }

    @Override
    public Order create(OrderDto dto) {
        if (dto != null) {
            if (dto.getRooms().isEmpty()){
                throw new IllegalArgumentException("At least one room must be selected.");
            }
            Order order = OrderDtoMapper.fromDto(dto);
            isValidDate(dto);
            if (isFreeRooms(dto)) {
                return orderRepository.save(order);
            } else {
                throw new BookedRoomsExceptions("Selected rooms have been booked");
            }
        }
        throw new NullEntityReferenceException("Order cannot be 'null'");
    }

    @Override
    public BigDecimal getTotalAmount(OrderDto dto) {
        if (dto==null){
            throw new NullEntityReferenceException("Order cannot be 'null'");
        }
        isValidDate(dto);
        int days = Period.between(dto.getCheckIn(), dto.getCheckOut()).getDays();
        return totalRoomsPricePerDay(dto).multiply(new BigDecimal(days));
    }

    private BigDecimal totalRoomsPricePerDay(OrderDto dto) {
        return dto.getRooms().stream()
                .map(e -> roomService.readById(Long.parseLong(e)).getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void isValidDate(OrderDto dto) {
        if (dto.getCheckIn() == null || dto.getCheckOut() == null) {
            throw new IllegalDateException("Check-in and check-out can't be 'null'");
        }
        if (!dto.getCheckIn().isBefore(dto.getCheckOut())){
            throw new IllegalDateException("Check-out should be greater than check-in");
        }
    }

    private boolean isFreeRooms(OrderDto dto) {
        List<String> orderedRoomsId = roomService.findOrderedRoomByHotelIdAndDate(Long.parseLong(dto.getHotelId()), State.NEW.name(), dto.getCheckIn(), dto.getCheckOut());
        Set<String> roomsId = dto.getRooms();
        return Collections.disjoint(orderedRoomsId, roomsId);
    }

    @Override
    public Order readById(long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + id + " not found"));
    }

    @Override
    public OrderProjection readProjectionById(long id) {
        return Optional.ofNullable(orderRepository.getProjectionById(id)).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + id + " not found")
        );
    }

    @Override
    public Order update(Order order) {
        if (order != null) {
            readById(order.getId());
            return orderRepository.save(order);
        }
        throw new NullEntityReferenceException("Order cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(readById(id));
    }

    @Override
    public List<OrderProjection> getProjectionsByUserId(long id) {
        List<OrderProjection> orders = orderRepository.getAllOrderProjectionByUserId(id);
        return orders.isEmpty() ? new ArrayList<>() : orders;
    }


}
