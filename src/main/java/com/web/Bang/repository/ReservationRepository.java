package com.web.Bang.repository;

import com.web.Bang.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationRepositoryCustom {


}
