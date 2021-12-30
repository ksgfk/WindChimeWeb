package com.github.ksgfk.windchimeweb.mapper;

import com.github.ksgfk.windchimeweb.entity.GoodsItemIdWithPrice;
import com.github.ksgfk.windchimeweb.entity.GoodsWithItemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    List<GoodsWithItemInfo> selectAllItems(@Param("offset") int offset, @Param("count") int count);

    List<GoodsWithItemInfo> selectAllIntertwinedFate();

    List<GoodsWithItemInfo> selectItemFuzzy(@Param("key") String key, @Param("offset") int offset, @Param("count") int count);

    GoodsWithItemInfo selectById(int id);

    GoodsItemIdWithPrice selectItemIdWithPrice(int id);

    List<GoodsWithItemInfo> selectAllIdsNoIntertwinedFate();
}