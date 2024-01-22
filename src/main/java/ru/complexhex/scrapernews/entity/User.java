package ru.complexhex.scrapernews.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name="user_generator", sequenceName="user_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_generator")
    @Column(name = "id")
    public Integer id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

   @Column(name = "telegram_id")
    public Integer telegramId;

   @Column(name = "user_name")
    public  String userName;
}
