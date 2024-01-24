package ru.complexhex.scrapernews.service;

import ru.complexhex.scrapernews.dto.UserDTO;
import ru.complexhex.scrapernews.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getUserById(Long id);

    void save(UserDTO userDTO);

    void updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    Optional<UserDTO> findByTelegramId(Long telegramId);
}
