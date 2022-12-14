package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByRun(Run run);
    Optional<Ticket> findById(Long id);
    void deleteById(Long aLong);

    List<Ticket> findByUser(User user);

    @Transactional
    @Modifying
    @Query("update Ticket t set t.user=?2 where t.id=?1")
    void  setUserById(Long id, User user);

    @Transactional
    @Modifying
    @Query("update Ticket t set t.run=?2, t.price=?3, t.currency=?4, t.status=?5 where t.id=?1")
    void  updateById(Long id, Run run, double price, String currency, TicketStatus status);
}
