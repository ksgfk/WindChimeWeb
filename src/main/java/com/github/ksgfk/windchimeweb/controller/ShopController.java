package com.github.ksgfk.windchimeweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ksgfk.windchimeweb.controller.request.BuyIntertwinedFateRequest;
import com.github.ksgfk.windchimeweb.controller.request.BuyItemRequest;
import com.github.ksgfk.windchimeweb.controller.request.UserToken;
import com.github.ksgfk.windchimeweb.controller.response.*;
import com.github.ksgfk.windchimeweb.entity.*;
import com.github.ksgfk.windchimeweb.mapper.ItemMapper;
import com.github.ksgfk.windchimeweb.mapper.ShopMapper;
import com.github.ksgfk.windchimeweb.service.UserService;
import com.github.ksgfk.windchimeweb.utility.MathUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shop")
@Api(tags = "商店管理")
@CrossOrigin(origins = "*")
public class ShopController {
    @Value("1800")
    private int refreshTime;
    @Value("8")
    private int randUserShopGoodsCount;

    @Autowired
    private ShopMapper shop;
    @Autowired
    private UserService user;
    @Autowired
    private ItemMapper items;

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @RequestMapping(value = "getFate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "获取所有纠缠之缘商品")
    public IntertwinedFateInfoResponse getAllFate(@RequestBody UserToken token) {
        var all = shop.selectAllInterFates();
        return new IntertwinedFateInfoResponse(true, all);
    }

    @RequestMapping(value = "/getItem", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "获取用户商店信息")
    public UserItemResponse getUserShopItems(@RequestBody UserToken info) {
        var usr = user.getUser(info.getToken());
        if (usr.isEmpty()) {
            return new UserItemResponse(false, null);
        }
        var online = usr.get();
        var goods = shop.selectUserShopGoodsList(online.getId());
        return new UserItemResponse(true, goods);
    }

    @RequestMapping(value = "/buyFate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "购买纠缠之缘")
    public EmptyResponse buyIntertwinedFate(@RequestBody BuyIntertwinedFateRequest request) {
        var usr = user.getUser(request.getToken());
        if (usr.isEmpty()) {
            return new EmptyResponse(false);
        }
        var online = usr.get();
        var fate = shop.selectInterFate(request.getId());
        var tryBuy = online.getMoney() - fate.getPrice().intValue();
        if (tryBuy < 0) {
            return new EmptyResponse(false);
        }
        var fateCnt = shop.selectUserFateCount(online.getId());
        int changeResult;
        if (fateCnt == null) {
            changeResult = shop.insertUserFate(online.getId(), 1);
        } else {
            changeResult = shop.updateUserFate(online.getId(), fateCnt.getCount() + fate.getCount() + fate.getExtraCount());
        }
        if (changeResult != 1) {
            throw new IllegalStateException();
        }
        changeResult = shop.updateUserMoney(online.getId(), tryBuy);
        if (changeResult != 1) {
            throw new IllegalStateException();
        }
        return new EmptyResponse(true);
    }

    @RequestMapping(value = "/buyItem", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "购买物品")
    public BuyItemResponse buyItem(@RequestBody BuyItemRequest request) throws JsonProcessingException {
        var usr = user.getUser(request.getToken());
        if (usr.isEmpty()) {
            return new BuyItemResponse(false, null);
        }
        var online = usr.get();
        var userGoods = shop.selectUserShopGoods(online.getId(), request.getId());
        if (userGoods == null) {
            return new BuyItemResponse(false, null);
        }
        if (userGoods.hasBuy()) {
            return new BuyItemResponse(false, null);
        }
        int tryBuy = online.getMoney() - userGoods.getPrice().intValue();
        if (tryBuy < 0) {
            return new BuyItemResponse(false, null);
        }
        int r = shop.updateUserMoney(online.getId(), tryBuy);
        if (r != 1) {
            throw new IllegalStateException();
        }

        //首先随机主属性
        //1.根据 物品id 从 物品主属性表 获取 可能出现的 属性id 和 权重
        //2.使用权重随机一个 属性id
        //3.使用 属性id 去 属性表 获取 属性增长
        //4.根据 属性增长 随机出 属性值
        //5.将 物品id，属性id，属性值 存入 玩家表
        var rand = ThreadLocalRandom.current();

        var primPropList = items.selectPrimaryInfoByItemId(userGoods.getItemId());
        var primWeightList = primPropList.stream().map(ItemPrimaryPropertyInfo::getWeight).mapToInt(i -> i).toArray();
        var primIdx = MathUtility.randomWeight(rand.nextDouble(), primWeightList);
        var primProperty = primPropList.get(primIdx);

        var minorProps = new ItemMinorPropertyInfo[3];
        var minorPropList = items.selectMinorInfoByItemId(userGoods.getItemId());
        for (int i = 0; i < 3; i++) {
            var non = new int[i + 2];
            for (int j = 0; j < i; j++) {
                non[j] = minorProps[j].getMinor();
            }
            non[i + 1] = primProperty.getPrimary();
            var minorWeightList = minorPropList.stream()
                    .map(k -> Arrays.stream(non).anyMatch(l -> l == k.getMinor()) ? 0 : k.getWeight())
                    .mapToInt(Integer::intValue)
                    .toArray();
            var minorIdx = MathUtility.randomWeight(rand.nextDouble(), minorWeightList);
            minorProps[i] = minorPropList.get(minorIdx);
        }

        var primP = items.selectItemProperty(primProperty.getPrimary());
        var primIncrease = JSON_MAPPER.readTree(primP.getIncrease());
        List<Integer> incList = new ArrayList<>(4);
        for (var inc : primIncrease) {
            incList.add(inc.asInt());
        }
        var primVal = incList.get(rand.nextInt(incList.size()));
        var primJsonNode = JSON_MAPPER.createObjectNode();
        var n = primJsonNode.putObject("primary");
        n.put("type", primP.getId());
        n.put("value", primVal);

        var miNode = JSON_MAPPER.createObjectNode();
        var miArr = miNode.putArray("minor");
        for (var minorProp : minorProps) {
            incList.clear();
            var miP = items.selectItemProperty(minorProp.getMinor());
            var miIncrease = JSON_MAPPER.readTree(miP.getIncrease());
            for (var inc : miIncrease) {
                incList.add(inc.asInt());
            }
            var miVal = incList.get(rand.nextInt(incList.size()));
            var arrObj = miArr.addObject();
            arrObj.put("type", miP.getId());
            arrObj.put("value", miVal);
        }

        String prim = primJsonNode.toString();
        String minor = miNode.toString();

        int cnt = shop.userHasItemCount(online.getId());
        r = shop.insertUserHasItem(cnt, online.getId(), userGoods.getItemId(), userGoods.getRare(), prim, minor);
        if (r != 1) {
            throw new IllegalStateException();
        }
        r = shop.updateUserShopItemHasBuy(online.getId(), userGoods.getGoodsId(), true);
        if (r != 1) {
            throw new IllegalStateException();
        }
        var i = new UserHasItem();
        i.setItemId(userGoods.getItemId());
        i.setUserId(online.getId());
        i.setRare(userGoods.getRare());
        i.setPrimary(prim);
        i.setMinor(minor);
        return new BuyItemResponse(true, i);
    }

    @RequestMapping(value = "/refreshShop", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class})
    @ApiOperation(value = "刷新独特的用户商店")
    public EmptyResponse refreshUserShop(@RequestBody UserToken token) {
        var usr = user.getUser(token.getToken());
        if (usr.isEmpty()) {
            return new EmptyResponse(false);
        }
        var online = usr.get();
        boolean willRefresh = online.getLastRefreshShop() == null;
        if (!willRefresh) {
            var lastRefresh = online.getLastRefreshShop().toInstant();
            Duration duration = Duration.between(lastRefresh, Instant.now());
            var second = duration.toSeconds();
            if (second > refreshTime) {
                willRefresh = true;
                shop.deleteUserShopItems(online.getId());
            }
        }
        if (willRefresh) {
            var rand = ThreadLocalRandom.current();
            var all = shop.selectActiveGoodsKey();
            GoodsKey[] next;
            if (all.size() <= randUserShopGoodsCount) {
                next = all.toArray(new GoodsKey[0]);
            } else {
                next = new GoodsKey[randUserShopGoodsCount];
                for (int i = 0; i < next.length; i++) {
                    next[i] = all.remove(rand.nextInt(all.size()));
                }
            }
            var items = Arrays.stream(next).map(i -> {
                var rng = ThreadLocalRandom.current();
                var it = new UserShopGoods();
                it.setGoodsId(i.getId());
                it.setUserId(online.getId());
                it.setHasBuy(false);
                it.setRare(rng.nextBoolean() ? "4" : "5");
                return it;
            }).collect(Collectors.toList());
            int r = shop.insertUserShopGoods(items);
            if (r != items.size()) {
                throw new IllegalStateException();
            }
            r = shop.updateUserLastRefreshShop(online.getId(), Date.from(Instant.now()));
            if (r != 1) {
                throw new IllegalStateException();
            }
            return new EmptyResponse(true);
        } else {
            return new EmptyResponse(false);
        }
    }

    @RequestMapping(value = "/getRefreshTime", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "下一次刷新时间")
    public ShopRefreshTimeResponse getRefreshTime(@RequestBody UserToken token) {
        var usr = user.getUser(token.getToken());
        if (usr.isEmpty()) {
            return new ShopRefreshTimeResponse(false, null);
        }
        var online = usr.get();
        var lastRefresh = online.getLastRefreshShop().toInstant();
        var next = lastRefresh.plusSeconds(refreshTime);
        return new ShopRefreshTimeResponse(true, Date.from(next));
    }
}
