package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.dto.UserDto;
import dev.pprotsiv.travel.dto.UserDtoMapper;
import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.repo.UserRepository;
import dev.pprotsiv.travel.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (user != null) {

            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found"));
    }


    @Override
    public User update(User user) {
        if (user != null) {
            User readById = readById(user.getId());
            if (user.getPassword()==null){
                user.setPassword(readById.getPassword());
            }
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        userRepository.delete(readById(id));
    }


    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.isEmpty() ? new ArrayList<>() : allUsers;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
