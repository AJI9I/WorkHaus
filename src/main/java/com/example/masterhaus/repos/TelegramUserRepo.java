package com.example.masterhaus.repos;

import com.example.masterhaus.domain.Telegramuser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TelegramUserRepo extends CrudRepository<Telegramuser,Long> {

    Optional<Telegramuser> findByChatid(String chatid);
}
