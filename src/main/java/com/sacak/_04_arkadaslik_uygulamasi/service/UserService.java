package com.sacak._04_arkadaslik_uygulamasi.service;

import com.sacak._04_arkadaslik_uygulamasi.dto.request.RegisterRequestDto;
import com.sacak._04_arkadaslik_uygulamasi.entity.Gender;
import com.sacak._04_arkadaslik_uygulamasi.entity.User;
import com.sacak._04_arkadaslik_uygulamasi.repository.UserRepository;
import com.sacak._04_arkadaslik_uygulamasi.views.VwUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DİKKAT!! spring boot bir sınıfın referansını oluşturmak için mutlaka o sınıfın işareetli olmasını
 * talep eder, yani bir sınıftan nesre üretilecek mi bilmek ister. Bunu yapabilmek için anotayon
 * kullanırız. Böylece spring componentScan sistemi ile işaretli sınıfları Bean olarak işaretler ve
 * çalışma zamanında bu sınıflardan birer bean yaratır. Servis sınıfları için @Servis anotasyonu
 * kullanılır.
 */

@Service
public class UserService {
    /**
     * Servis temel işlevleri yerine getirir. Gerekli gördüğü bilgileri repositorye aktarır. Bu nedenle
     * servis sınıfında repository nesneleirne ihtiyaç duyulur.
     * Burada repository değişkeni tanımlanmalı ve nesnesi oluşturulmalı.
     */

//    @Autowired
//    private UserRepository userRepository;



    /**
     * Dependency Injection DI
     * Spring bootta bağımlılıkları sağlamak injektee etmek için farklı yöntemler kullanılır.
     * En bilindikleri:
     * 1- Autowired anotasyonu ile kullanım.
     * Bağlılık atfedilen dğişkenin üzerine eklenir ise spring boot ApplicationContext içerisinde ilgili
     * sınıftan oluşturulmuş olan nesne referansını ilgili değişkene atar
     * 2-) Constructor Injectin ile kullanım
     * Spring boot bir sınıfın oluşturulabilmesi için gerekli olan Constructor methodlarını incelerken
     * eğer method nesne talep ediyor ise, Application Context içindeki nesnelerden bunu sağlamaya çalışır.
     * ve nesne referanslarını bu constructora parametre olarak geçer.
     */

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void addUser(String username, String avatar, Gender gender) {
        User user = User.builder().avatar(avatar).name(username).gender(gender).build();
        userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> kadinKullanicilarListesi(){
        return userRepository.findAllByGender(Gender.FEMALE);
    }
    public Boolean kullaniciVarmi(){
        return userRepository.girisDogruMu("a","b");
    }

    public List<VwUser> tumKullanicilariGetir(){
        return userRepository.tumKullanicilariGetir();
    }
    public void createUser(User user){
        userRepository.save(user);
    }

    public List<User> findAllByUsername(String username){
        return userRepository.findAllByUsernameContaining(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User register(RegisterRequestDto dto) {
       return userRepository.save(User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build());
    }

    public List<VwUser> getAllUserList() {
        return userRepository.tumKullanicilariGetir();
    }
    public boolean isExistById(Long id){
        return userRepository.existsById(id);
    }
}
