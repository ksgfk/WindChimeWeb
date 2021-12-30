package com.github.ksgfk.windchimeweb.mapper;

import com.github.ksgfk.windchimeweb.entity.ItemMinorPropertyInfo;
import com.github.ksgfk.windchimeweb.entity.ItemPrimaryPropertyInfo;
import com.github.ksgfk.windchimeweb.entity.ItemProperty;

import java.util.List;

public interface ItemMapper {
    List<ItemPrimaryPropertyInfo> selectPrimaryInfoByItemId(int id);

    List<ItemMinorPropertyInfo> selectMinorInfoByItemId(int id);

    ItemProperty selectItemProperty(int id);
}