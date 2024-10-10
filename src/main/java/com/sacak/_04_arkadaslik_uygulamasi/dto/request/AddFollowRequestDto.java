package com.sacak._04_arkadaslik_uygulamasi.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddFollowRequestDto {

    @NotNull
            @NotEmpty
    Long userId;
    @NotNull
    Long followingId;


}
