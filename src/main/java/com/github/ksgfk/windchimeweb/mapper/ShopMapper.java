package com.github.ksgfk.windchimeweb.mapper;

import com.github.ksgfk.windchimeweb.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ShopMapper {
    List<IntertwinedFateGoods> selectAllInterFates();

    IntertwinedFateGoods selectInterFate(int id);

    void deleteUserShopItems(int id);

    List<UserShopItemAllInfo> selectUserShopGoodsList(int id);

    UserShopItemAllInfo selectUserShopGoods(@Param("userId") int userId, @Param("goodsId") int goodsId);

    UserIntertwinedFate selectUserFateCount(int userId);

    int insertUserFate(@Param("userId") int userId, @Param("count") int count);

    int updateUserFate(@Param("userId") int userId, @Param("count") int count);

    int updateUserMoney(@Param("userId") int userId, @Param("money") int money);

    int updateUserShopItemHasBuy(@Param("userId") int userId, @Param("goodsId") int goodsId, @Param("hasBuy") boolean hasBuy);

    int userHasItemCount(int userId);

    int insertUserHasItem(@Param("id") int id, @Param("userId") int uid,
                          @Param("itemId") int iid,
                          @Param("rare") String rare,
                          @Param("prim") String prim,
                          @Param("minor") String minor);

    List<GoodsKey> selectActiveGoodsKey();

    int insertUserShopGoods(@Param("items") List<UserShopGoods> goods);

    int updateUserLastRefreshShop(@Param("id") int id, @Param("time") Date time);
}
