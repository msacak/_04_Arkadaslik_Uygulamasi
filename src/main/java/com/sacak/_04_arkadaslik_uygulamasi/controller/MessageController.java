package com.sacak._04_arkadaslik_uygulamasi.controller;

import com.sacak._04_arkadaslik_uygulamasi.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * DİKKAT
 * Constructor Injection işlemi, sınıf için gerekli constructoru ve talep edilen değişkeni yazarak
 * tanımlayabiliriz. Bakınız. UserController.
 * ancak bu işlemi kolayca Lombok kullanarakta yapabiliriz. Bunun için lombokun @RequiredArgConstructor
 * anotasyonu kullanılır.
 */
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


}
