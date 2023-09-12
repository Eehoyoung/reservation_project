package com.web.Bang.service;

import com.web.Bang.dto.RequestPostDto;
import com.web.Bang.dto.queryDslDto.HouseDto;
import com.web.Bang.model.House;
import com.web.Bang.model.Image;
import com.web.Bang.model.User;
import com.web.Bang.model.WishList;
import com.web.Bang.repository.HouseRepository;
import com.web.Bang.repository.ImageRepository;
import com.web.Bang.repository.LikeHouseRepository;
import com.web.Bang.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final ImageRepository imageRepository;
    private final LikeHouseRepository likeHouseRepository;
    private final ReviewRepository reviewRepository;
    @Value("${file.path}")
    private String uploadFolder;

    @Override
    @Transactional
    public House getHouseDetail(int houseId) {

        return houseRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("해당하는 숙소를 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    @Modifying
    public void deleteHouse(int houseId) {
        houseRepository.deleteById(houseId);
    }

    @Override
    @Transactional
    public void postHouse(RequestPostDto requestPostDto, User user) {
        House houseEntity = new House();
        houseEntity.setName(requestPostDto.getName());
        houseEntity.setAddress(requestPostDto.getAddress());
        houseEntity.setDetailAddress(requestPostDto.getDetailAddress());
        houseEntity.setInfoText(requestPostDto.getInfoText());
        houseEntity.setType(requestPostDto.getType());
        houseEntity.setOneDayPrice(requestPostDto.getOneDayPrice());
        houseEntity.setCapacity(requestPostDto.getCapacity());
        houseEntity.setHostId(user);

        String imageFileName = UUID.randomUUID() + "_" + "image"; // 한글이름 파일 저장시 오류 방지
        String newFileName = (imageFileName.trim()).replaceAll("\\s", "");

        Path imageFilePath = Paths.get(uploadFolder + newFileName);

        try {
            Files.write(imageFilePath, requestPostDto.getFile().getBytes());

            Image imageEntity = requestPostDto.toEntity(newFileName);
            imageRepository.save(imageEntity);
            houseEntity.setImage(imageEntity);
            houseRepository.save(houseEntity);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    @Transactional
    public List<HouseDto> getHouseList() {
        return houseRepository.findAllHouse();

    }

    @Override
    @Transactional
    public void addWishList(int houseId, User user) {
        WishList wishListEntity = new WishList();
        House selectedHouse = houseRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("해당하는 숙소를 찾을 수 없습니다."));

        wishListEntity.setGuest(user);
        wishListEntity.setGuestId(user.getId());
        wishListEntity.setHouse(selectedHouse);
        wishListEntity.setHouseId(houseId);
        likeHouseRepository.save(wishListEntity);
    }

    @Override
    @Transactional
    public List<HouseDto> getHouseListByAddress(String address, int houseId) {
        return houseRepository.findAllByAddress(address, houseId).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public void deleteItemOfWishList(int houseId, int guestId) {
        likeHouseRepository.deleteByHouseIdAndGuestId(houseId, guestId);
    }

    @Override
    @Transactional
    public List<HouseDto> findAllByHostId(int hostId) {
        return houseRepository.findAllByHostId(hostId);
    }

    @Override
    @Transactional
    public void updateHouse(int houseId, RequestPostDto requestPostDto) {

        House houseEntity = houseRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("해당 숙소는 존재하지 않습니다."));

        houseEntity.setName(requestPostDto.getName());
        houseEntity.setAddress(requestPostDto.getAddress());
        houseEntity.setDetailAddress(requestPostDto.getDetailAddress());
        houseEntity.setInfoText(requestPostDto.getInfoText());
        houseEntity.setType(requestPostDto.getType());
        houseEntity.setOneDayPrice(requestPostDto.getOneDayPrice());

        String imageFileName = UUID.randomUUID() + "_" + "image";
        String newFileName = (imageFileName.trim()).replaceAll("\\s", "");

        Path imageFilePath = Paths.get(uploadFolder + newFileName);
        System.out.println("originFileName : " + requestPostDto.getFile().getOriginalFilename());

        try {
            Image imageEntity;
            if (!Objects.equals(requestPostDto.getFile().getOriginalFilename(), "")) {
                Files.write(imageFilePath, requestPostDto.getFile().getBytes());
                imageEntity = requestPostDto.toEntity(newFileName);
                imageRepository.save(imageEntity);
                houseEntity.setImage(imageEntity);
            }

            houseRepository.save(houseEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @Transactional(readOnly = true)
    public int getReviewCount(int houseId) {
        return reviewRepository.getReviewCount(houseId).orElse(0);
    }

    @Override
    @Transactional
    public List<HouseDto> searchHouseByAddressAndType(String address, String type) {
        return houseRepository.findAllByAddressAndTypeOrderByIdDesc(address, type);
    }

    @Override
    @Transactional
    public List<HouseDto> searchHouseByAddressOrType(String address, String type) {
        return houseRepository.findAllByAddressOrTypeOrderByIdDesc(address, type);
    }

    @Override
    @Transactional
    public List<HouseDto> getHouseOrderByStarScore() {
        return houseRepository.findAllByStarScore();

    }

    @Override
    @Transactional
    public House findById(int houseId) {
        return houseRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("해당 숙소는 존재하지 않습니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public int getReviewCountByGuestId(int guestId) {
        return reviewRepository.getReviewCountByGuestId(guestId).orElse(0);
    }

}
