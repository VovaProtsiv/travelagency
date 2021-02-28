package dev.pprotsiv.travel.dto;

import dev.pprotsiv.travel.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoMapper {
    public static UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
    }
    public static List<UserDto> convertToDto(List<User> users){
        return users.stream().map(UserDtoMapper::convertToDto).collect(Collectors.toList());
    }
}
