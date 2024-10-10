package com.sacak._04_arkadaslik_uygulamasi.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
    @Size(min = 3, max = 64, message = "Kullanici adı 3-64 arasında karakter kısıtlamasına sahiptir.")
    @NotNull
    String username;
    @NotNull
    @NotEmpty
    String password;
    @NotNull
    @NotEmpty
    @Size(min = 8,max = 64)
    @Pattern(
            message = "Şifreniz en az 8 en fazla 64 karakter olmalıdır. Şifrenizde en az bir büyük bir küçük harf bulunmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"

    )
    String rePassword;
    @Email(message = "Lütfen geçerli bir e-posta adresi giriniz.")
    String email;

}
