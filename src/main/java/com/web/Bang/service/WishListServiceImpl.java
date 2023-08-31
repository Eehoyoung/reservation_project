package com.web.Bang.service;

import com.web.Bang.model.WishList;
import com.web.Bang.repository.LikeHouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final LikeHouseRepository likeHouseRepository;


    @Override
    @Transactional(readOnly = true)
    public WishList checkWishList(int houseId, int guestId) {
        return likeHouseRepository.findByHouseIdAndGuestId(houseId, guestId).orElseGet(WishList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WishList> getWishListById(int guestId) {
        return likeHouseRepository.getWishListByGuestId(guestId);
    }

    @Override
    @Transactional(readOnly = true)
    public int getLikeCount(int houseId) {
        return likeHouseRepository.getLikeCount(houseId);
    }
}
