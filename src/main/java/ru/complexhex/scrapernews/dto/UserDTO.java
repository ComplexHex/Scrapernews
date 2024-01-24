package ru.complexhex.scrapernews.dto;

import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    public Long telegramId;
    public String userName;
}