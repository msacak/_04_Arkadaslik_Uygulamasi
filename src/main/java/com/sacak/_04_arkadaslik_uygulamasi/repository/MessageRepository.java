package com.sacak._04_arkadaslik_uygulamasi.repository;

import com.sacak._04_arkadaslik_uygulamasi.entity.Follow;
import com.sacak._04_arkadaslik_uygulamasi.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
