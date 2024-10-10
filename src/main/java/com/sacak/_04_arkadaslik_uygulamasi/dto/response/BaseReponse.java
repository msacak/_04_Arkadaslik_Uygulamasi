package com.sacak._04_arkadaslik_uygulamasi.dto.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseReponse<T> {
    Boolean success;
    String message;
    Integer code;
    T data;

}
