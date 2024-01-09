package ru.complexhex.scrapernews.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.complexhex.scrapernews.dto.UserDTO;
import ru.complexhex.scrapernews.entity.User;
import ru.complexhex.scrapernews.mapper.UserMapper;
import ru.complexhex.scrapernews.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }
}


