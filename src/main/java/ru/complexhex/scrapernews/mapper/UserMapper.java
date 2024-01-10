package ru.complexhex.scrapernews.mapper;

import org.mapstruct.Mapper;
import ru.complexhex.scrapernews.dto.UserDTO;
import ru.complexhex.scrapernews.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

}
