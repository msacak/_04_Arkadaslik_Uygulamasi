package com.sacak._04_arkadaslik_uygulamasi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String avatar;
    Gender gender;
    String username;
    String password;
    String phone;
    String email;
    String address;
    Integer age;
    Integer weight;
    Integer height;
    Integer followerCount;
    Integer followingCount;
    Boolean isActive;

}