package com.onlineshopping.mapper;

import com.onlineshopping.model.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {
    Shop selectShopByShopId(Integer shopId);

    Shop selectShopByUserId(Integer userId);

    List<Shop> selectShops(Shop shop, Integer startRow, Integer num);

    Integer countShops(Shop shop);

    void insertShop(Shop shop);

    void updateShopInfo(Shop shop);

//    void deleteShopByShopId(Integer shopId);
}
