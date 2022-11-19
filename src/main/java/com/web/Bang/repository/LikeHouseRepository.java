package com.web.Bang.repository;

import com.web.Bang.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeHouseRepository extends JpaRepository<WishList, Integer> {

    void deleteByHouseIdAndGuestId(int houseId, int guestId);

    Optional<WishList> findByHouseIdAndGuestId(int houseId, int guestId);

    List<WishList> getWishListByGuestId(int guestId);

    @Query(value = "SELECT COUNT(*) FROM likehouse WHERE houseId = ? ", nativeQuery = true)
    int getLikeCount(int houseId);

}
