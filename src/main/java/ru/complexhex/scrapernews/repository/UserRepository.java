package ru.complexhex.scrapernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.complexhex.scrapernews.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTelegramId(Long telegramId);
}
