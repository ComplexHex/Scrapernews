package ru.complexhex.scrapernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.complexhex.scrapernews.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
