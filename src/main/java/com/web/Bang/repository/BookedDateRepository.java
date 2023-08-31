package com.web.Bang.repository;

import com.web.Bang.model.BookedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookedDateRepository extends JpaRepository<BookedDate, Integer> {

    @Query(value = "select b.id as id, ResId, bookedDate\r\n"
            + "from bookeddate as b\r\n"
            + "inner join reservation as r\r\n"
            + "on b.ResId = r.id\r\n"
            + "where r.houseId = :houseId", nativeQuery = true)
    List<BookedDate> findAllByHouseId(@Param(value = "houseId") int houseId);

    @Modifying
    @Query(value = "delete from bookeddate where ResId = ?1", nativeQuery = true)
    void deleteAllByResId(@Param(value = "ResId") int ResId);
}
