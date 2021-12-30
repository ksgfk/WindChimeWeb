package com.github.ksgfk.windchimeweb.mapper;

import com.github.ksgfk.windchimeweb.entity.User;
import com.github.ksgfk.windchimeweb.entity.UserHasItem;
import com.github.ksgfk.windchimeweb.entity.UserIntertwinedFate;
import com.github.ksgfk.windchimeweb.entity.UserShopItemAllInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    User selectByAccount(String account);

    User selectById(int id);

    int insertNewUser(User user);

    int insertUser(User user);

    int updateUserMoney(@Param("id") int id, @Param("newMoney") int newMoney);

    List<UserShopItemAllInfo> selectShopItems(int id);

    int insertShopItems(@Param("items") List<UserShopItemAllInfo> items);

    int updateUserLastRefreshShop(@Param("id") int id, @Param("time") Date time);

    int deleteShopItems(int id);

    User selectByQQ(String qq);

    int updateAccountPassword(@Param("id") int id, @Param("acc") String acc, @Param("pwd") byte[] pwd);

    UserIntertwinedFate selectUserIntertwinedFate(@Param("id") int id);

    List<UserHasItem> selectUserHasItems(int id);

    String selectItemLevelUp(int id);

    int updateUserItem(@Param("id") int id, @Param("prim") String prim, @Param("minor") String minor, @Param("level") int level);

    UserHasItem selectUserHasItem(@Param("userId") int userId, @Param("id") int id);
}