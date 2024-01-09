package ru.complexhex.scrapernews.service;

import org.springframework.transaction.annotation.Transactional;
import ru.complexhex.scrapernews.dto.UserDTO;
import ru.complexhex.scrapernews.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    UserDTO getUserById(Long id);

    void save(User user);
}
