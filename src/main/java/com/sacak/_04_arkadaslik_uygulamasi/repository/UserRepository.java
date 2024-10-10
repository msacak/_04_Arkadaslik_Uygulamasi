package com.sacak._04_arkadaslik_uygulamasi.repository;

import com.sacak._04_arkadaslik_uygulamasi.entity.Follow;
import com.sacak._04_arkadaslik_uygulamasi.entity.Gender;
import com.sacak._04_arkadaslik_uygulamasi.entity.User;
import com.sacak._04_arkadaslik_uygulamasi.views.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> gelSenButunDatalariBanaVer(); böyle saçma metod yazarsan program patlar.
    /**
     * JpaRepository -> içerişindeki tüm metodların kodlamaları standarttır. Bu nedenle Spring ile
     * ilgili sınıfların mesela UserRepository için UserRepositoryImpl sınıfını oluşturabilir.
     * Ancak burada tanımı yapılan method nasıl oluşturulacağı hakkında bir bilgi içermediği için
     * nesne yaratma sürecini baltaladı.
     */

    /**
     * Kadın kullanıcıların listesini getir.
     */
    // List<User> kadinKullanicilarıGetir(); YANLIŞ
    /**
     *
     * JpaRepository belli keywordler ile sorgu methodları oluşturmanıza izin verir.
     * JPA kendine ait metodların gövdelerini yazabilir.
     * JPA kendi kelimeleri ile oluşturulan methodların da gövdelerini yazabilir, ancak kurallara
     * uygun yazılır ise.
     * Örn: Cinsiyete göre kullanıcı listesi.
     * SELECT * FROM tbl_user WHERE gender = ?
     * find(sorgu türünü belirtiyoruz) ALL(opsiyoneldir)
     * by - where komutuna denk gelir
     * Entity içerisinde tanımlanmış değişken adını yazıyorsunuz Ancak değişkenin
     * ilk harfi büyük harf ile başlamalı!
     * eğer sorgu bir değer talep ediyor ise talep edilen
     */


    List<User> findAllByGender(Gender gender);

    //birden fazla alan için sorgu yapmak istenirse
    List<User> findAllByGenderAndAge(Gender gender, int age);
    List<User> findAllByGenderOrName(Gender gender, String name);

    //sorgu sadece tek bir sonuç dönecek ise
    Optional<User> findOptionalByUsername(String username); //User findByUserName olmaz!! nullcheck falan

    //belli bir yaşa eşit veya üzerindeki kullanıcıları bulmak
    List<User> findAllByAgeGreaterThan(int age); //o yaştan büyük olanlar
    List<User> findAllByAgeGreaterThanEqual(int age); // o yaşa eşit veyahut büyük olanlar
    List<User> findAllByAgeLessThan(int age); // o yaştan küçük olanlar

    // like ve ilike
    List<User> findAllByUsernameLike(String username); // select * from user where like ?
    List<User> findAllByUsernameLikeIgnoreCase(String username); //ilike -> bunu dene hoca emin değildi.

    //bununla başlayanlar ve bununla bitenler kullanımı
    List<User> findAllByNameStartingWith(String name);
    List<User> findAllByNameEndingWith(String name);
    //adında şu olanlar -> bu da like gibi
    List<User> findALlByNameContaining(String name); //like ile (___%) gibi sorgu yapabilirsin, containingde yapamazsın, sadece %__% sorguları yapabilirsin.
    List<User> findAllByUsernameContaining(String username);
    List<User> findAllByUsernameContainingIgnoreCaseAndAgeGreaterThanAndEmailEndingWith(String userName, int age,String email);

    //Sorgu neticesinde dönen verilerde sıralama yapmak

    List<User> findAllByOrderByAge(); //default asc
    List<User> findAllByUsernameOrderByAge(String username);
    List<User> findAllByNameOrderByAgeDesc(String username);

    //yaşı en büyük olan User ?
    User findTopByOrderByAgeDesc();

    //yaşı en küçük olan ilk 5 kullanıcı -> select * from users order by age limit 5;
    List<User> findTop5ByOrderByAge();

    //belli aralıkları sorgulamak.
    List<User> findAllByAgeBetween(int low, int high);//between başlangıç ve bitişi dahil eder.

    List<User> findAllByUsernameContainingAndAgeBetween(String name, int low, int high);

    //Boolean değeri sorgularken kullanılacak keyword -> True/False
    //Hesabı aktif olan kullanıcılar
    List<User> findAllByIsActiveTrue();
    //Hesabı aktif olmayan kullanıcılar
    List<User> findAllByIsActiveFalse();

    //Mükerrer kayıtlı tekilleştirmek
    //Uygulamamızda kullanılan farklı isimlerin listesi
    List<User> findDistinctBy();

    //Belli bir listede olan kişileri arama, mesela elimizde id listesi var, bu idlere sahip kişileri getirelim
    List<User> findAllByIdIn(List<Long> ids);

    /**
     * HQL ile sorgu yazmak
     * Normal Spring sorgularının aksine metod ismini istediğin gibi yazabilirsin exception fırlatmaz
     * çünkü o sorguyu sen zaten hql olarka giriyorsun.
     */

    @Query("FROM User u where u.name=?1") //-> HQL
    List<User> banaAdiSuOlanKullanicilariGetir(String ad);


    @Query("select u from User u where u.age=?1 AND u.email like ?2") //JPQL
    List<User> bulYasiSuOlanVeMailAdresiSuOlan(int yas,String mailAdres);

    @Query(value = "SELECT * from User where name = :ad",nativeQuery = true)
    List<User> yukaridakininAynisi(@Param("ad") String ad); //Benim örneğim, native query ile ilgili

    //Login olacak bir kullanıcı username, password girecek ve bu kritere uygun kayıt varsa giriş yapacak
    /**
     * select COUNT(*)>0 from tbluser where username=? AND password=?
     */

    @Query(nativeQuery = true,value = "select COUNT(*)>0 from tbl_user where user_name=?1 AND password=?2 ")
    Boolean girisDogruMu(String username,String password);


    //View ile ilgili çok güzel bir örnek !!!!! VwUser pathini nasıl seçip tanıttığına dikkat et...
    @Query("select new com.sacak._04_arkadaslik_uygulamasi.views.VwUser(u.username,u.name,u.avatar) from User u")
    List<VwUser> tumKullanicilariGetir();








}