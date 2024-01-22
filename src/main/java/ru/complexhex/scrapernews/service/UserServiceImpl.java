package ru.complexhex.scrapernews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.complexhex.scrapernews.dto.UserDTO;
import ru.complexhex.scrapernews.entity.User;
import ru.complexhex.scrapernews.mapper.UserMapper;
import ru.complexhex.scrapernews.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDTO(user);
    }

    @Transactional
    @Override
    public void save(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow();
        User updateUser = userMapper.userDTOToUser(userDTO);

        existingUser.setFirstName(updateUser.getFirstName());
        existingUser.setLastName(updateUser.getLastName());
        existingUser.setTelegramId(updateUser.getTelegramId());
        existingUser.setUserName(updateUser.getUserName());

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByTelegramId(Long telegramId) {
        User user = userRepository.findByTelegramId(telegramId).orElse(null);
        return Optional.ofNullable(user).map(userMapper::userToUserDTO);
    }
}
