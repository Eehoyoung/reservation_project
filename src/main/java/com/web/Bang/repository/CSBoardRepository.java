package com.web.Bang.repository;

import com.web.Bang.model.CustomServiceBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSBoardRepository extends JpaRepository<CustomServiceBoard, Integer>, CSBoardRepositoryCustom {

}
