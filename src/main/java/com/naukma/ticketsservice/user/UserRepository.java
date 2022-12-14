package com.naukma.ticketsservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.email=?2, u.password=?3, u.firstName=?4, u.lastName=?5 where u.id=?1")
    void updateById(Long id, String email, String password, String firstName, String lastName);

}
