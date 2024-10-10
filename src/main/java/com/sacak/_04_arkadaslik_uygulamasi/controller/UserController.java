package com.sacak._04_arkadaslik_uygulamasi.controller;

import com.sacak._04_arkadaslik_uygulamasi.constants.RestApis;
import com.sacak._04_arkadaslik_uygulamasi.dto.request.RegisterRequestDto;
import com.sacak._04_arkadaslik_uygulamasi.dto.response.BaseReponse;
import com.sacak._04_arkadaslik_uygulamasi.entity.Gender;
import com.sacak._04_arkadaslik_uygulamasi.entity.User;
import com.sacak._04_arkadaslik_uygulamasi.service.UserService;
import com.sacak._04_arkadaslik_uygulamasi.views.VwUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sacak._04_arkadaslik_uygulamasi.constants.RestApis.*;

import java.util.List;

/**
 * Controller sınıfları son kullanıcı ile iletişime geçiş için gelen isteleri handle edecek
 * sınıflardır. Burada web için gerekli erişimler tanımlanır ve istekler işlenir.
 *
 * @Controller -> web MCV için kullanılır.
 * @RestController -> restAPI kullanımları için
 * Bir diğer önemli nokta ise, bu sınıf bir web iletişim sınıfı olduğu için internet ya da intranet
 * üzerinden gelen isteklerin yakalanabilmesi için PATH tanımı yapılması gereklidir. İlgili sınıf
 * için tanımlama
 * @RequestMapping("/[PATH_NAME]") şeklinde yapılır.
 * Bu işlem şunu ifade eder,
 * http://[Ip Adres ya da DomainName]:[PORT]/[PATH_NAME]
 * userController için erişim
 * http://localhost:9090/user
 */

@RestController
@RequestMapping(USER)
public class UserController {


    //ConstructorInjection aka DependencyInjection yapıyoz. AutoWired ile yapsak ne olur ?
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * http://localhost:9090/user/add-user
     */

    @GetMapping(ADDUSER)
    public String addUser() {
        userService.addUser("murat", "http://picsum.photos/100/100", Gender.MALE);
        userService.addUser("deniz", "http://picsum.photos/100/100", Gender.MALE);
        userService.addUser("bahar", "http://picsum.photos/100/100", Gender.FEMALE);
        userService.addUser("canan", "http://picsum.photos/100/100", Gender.FEMALE);
        return "kayıtlar başarı ile eklendi.";
    }

    //http://localhost:9090/user/get-all-users
    @GetMapping(GETALLUSERS)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(GETALLFEMALEUSERS)
    public List<User> getFemaleUsers() {
        return userService.kadinKullanicilarListesi();
    }


    @PostMapping(LOGIN)
    public void doLogin(String username, String password) {
        System.out.println("Kullanıcı adı = " + username);
        System.out.println("Password      = " + password);
    }

    @PostMapping(CREATEUSER)
    public void createUser(String username, String password, String email, String name) {
        User user = User.builder().username(username).password(password).email(email).name(name).build();
        userService.createUser(user);
    }

    @GetMapping(FINDALLBYUSERNAME)
    public List<User> getByUsername(String username) {
        return userService.findAllByUsername(username);
    }

    @GetMapping(FINDBYID)
    public User findById(Long id) {
        return userService.findById(id);
    }

    @PostMapping(REGISTER)
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequestDto dto) {
        if(!dto.getPassword().equals(dto.getRePassword())){
            return ResponseEntity.badRequest().body(null);
        }
        User user = userService.register(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-all-user-list")
    public ResponseEntity<List<VwUser>> getAllUserList(){
        return ResponseEntity.ok(userService.getAllUserList());
    }


    @GetMapping("/get-all-view-users")
    public ResponseEntity<BaseReponse<List<VwUser>>> getAllViewUser(){
        return ResponseEntity.ok(BaseReponse.<List<VwUser>>builder()
                .success(true)
                .code(200)
                .message("Kullanıcı listesi başarılı şekilde oluşturuldu.")
                .data(userService.getAllUserList()).build());
    }




}
