package com.sacak._04_arkadaslik_uygulamasi.repository;

import com.sacak._04_arkadaslik_uygulamasi.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}