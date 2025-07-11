package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryUser extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    @Query("select u from User u where " +
            "u.role.name = 'USER'")
    Page<User> findAll(Pageable pageable);

    @Query("select u from User u where u.role.name ='USER'")
    List<User> getAllUsers();

    @Query("select u from User u where" +
            "(:name is null or u.username like %:name%) " +
            " and (:phone is null or u.phone like %:phone% ) " +
            "and (:email is null or u.email like %:email%)" +
            " and u.role.name = 'USER'"
    )
    Page<User> searchUsersByNameAndPhoneAndEmail(Pageable pageable,
                                                 @Param("name")String name,
                                                 @Param("phone")String phone,
                                                 @Param("email")String email);
}
