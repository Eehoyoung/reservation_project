package com.web.Bang.service;

import com.web.Bang.model.WishList;
import com.web.Bang.repository.LikeHouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishListService {

    private final LikeHouseRepository likeHouseRepository;

    public WishListService(LikeHouseRepository likeHouseRepository) {
        this.likeHouseRepository = likeHouseRepository;
    }

    @Transactional(readOnly = true)
    public WishList checkWishList(int houseId, int guestId) {
        return likeHouseRepository.findByHouseIdAndGuestId(houseId, guestId).orElseGet(WishList::new);
    }

    @Transactional(readOnly = true)
    public List<WishList> getWishListById(int guestId) {
        return likeHouseRepository.getWishListByGuestId(guestId);
    }

    @Transactional(readOnly = true)
    public int getLikeCount(int houseId) {
        return likeHouseRepository.getLikeCount(houseId);
    }
}
