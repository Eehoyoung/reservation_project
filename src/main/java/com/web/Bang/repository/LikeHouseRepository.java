package com.web.Bang.repository;

import com.web.Bang.model.WishList;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeHouseRepository extends JpaRepository<WishList, Integer> {

    void deleteByHouseIdAndGuestId(int houseId, int guestId);

    Optional<WishList> findByHouseIdAndGuestId(int houseId, int guestId);

    List<WishList> getWishListByGuestId(int guestId);

    @Query("SELECT count(*) from WishList w where w.houseId = :houseId")
    int getLikeCount(@Param("houseId") int houseId);

}
