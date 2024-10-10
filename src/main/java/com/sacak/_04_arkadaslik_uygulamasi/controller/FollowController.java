package com.sacak._04_arkadaslik_uygulamasi.controller;

import com.sacak._04_arkadaslik_uygulamasi.dto.request.AddFollowRequestDto;
import com.sacak._04_arkadaslik_uygulamasi.entity.Follow;
import com.sacak._04_arkadaslik_uygulamasi.service.FollowService;
import com.sacak._04_arkadaslik_uygulamasi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.sacak._04_arkadaslik_uygulamasi.constants.RestApis.*;
@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    private final UserService userService;

    /**
     * HTTP REQUEST Types
     * 1-) Get = Bir sunucuya bilgi almak amaçlı gönderilen istek türüdür. Parametre gönderebilirsiniz, ancak güvenli değildir.
     * Genellikle herkese açık ve güvenliği sorun olmadığı işlemlerde kullanılır.
     * Get isteği genellikle browser istekleridir. Bilgiler header içerisinde iletilir.

     * 2-) Post = Yeni bir veri yaratmak amacıyla bilgi gönderiminde yapılan istek türüdür.
     * Bilgeler isteğin gövdesinde gönderilir ve veriler güvenli bir şekilde iletilir.
     * 3-) Update
     * 4-) Delete
     * 5-)
     */

    //http://localhost:9090/follow/add-follow?userId=5&followId=1
    @GetMapping(ADDFOLLOW)
    public void addFollow(AddFollowRequestDto dto){
        followService.addFollow(dto);
    }





}
