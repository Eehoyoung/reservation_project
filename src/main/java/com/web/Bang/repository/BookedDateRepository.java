package com.web.Bang.repository;

import com.web.Bang.model.BookedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookedDateRepository extends JpaRepository<BookedDate, Integer>, BookedDateRepositoryCustom {

    @Modifying
    @Query(value = "delete from bookeddate where ResId = ?1", nativeQuery = true)
    void deleteAllByResId(@Param(value = "ResId") int ResId);
}
