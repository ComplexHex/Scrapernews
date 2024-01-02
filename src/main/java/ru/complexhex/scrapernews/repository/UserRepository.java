package ru.complexhex.scrapernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.complexhex.scrapernews.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
