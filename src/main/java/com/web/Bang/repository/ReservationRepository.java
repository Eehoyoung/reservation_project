package com.web.Bang.repository;

import com.web.Bang.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM reservation WHERE guestId = ?1 order by id desc", nativeQuery = true)
    List<Reservation> findByGuestId(@Param(value = "guestId") int guestId);

    @Query(value = "SELECT * FROM reservation WHERE hostId = ?1 order by id desc", nativeQuery = true)
    List<Reservation> findByHostId(@Param(value = "hostId") int id);

}
