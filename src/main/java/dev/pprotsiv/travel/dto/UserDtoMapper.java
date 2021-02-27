package dev.pprotsiv.travel.dto;

import dev.pprotsiv.travel.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoMapper {
    public static UserDto convertToDto(User user) {
        Set<String> collect = user.getRoles().stream().map(e -> e.getName().name().substring(5)).collect(Collectors.toSet());
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), collect);
    }
    public static List<UserDto> convertToDto(List<User> users){
        return users.stream().map(UserDtoMapper::convertToDto).collect(Collectors.toList());
    }
}
