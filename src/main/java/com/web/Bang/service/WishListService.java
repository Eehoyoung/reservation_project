package com.web.Bang.service;

import com.web.Bang.model.WishList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WishListService {
    @Transactional(readOnly = true)
    WishList checkWishList(int houseId, int guestId);

    @Transactional(readOnly = true)
    List<WishList> getWishListById(int guestId);

    @Transactional(readOnly = true)
    int getLikeCount(int houseId);
}
