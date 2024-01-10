package ru.complexhex.scrapernews.service;

import ru.complexhex.scrapernews.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getUserById(Long id);

    void save(UserDTO userDTO);

    void updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
