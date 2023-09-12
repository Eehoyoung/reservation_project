package com.web.Bang.repository;

import com.web.Bang.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer>, HouseRepositoryCustom {
}
