package com.github.ksgfk.windchimeweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ksgfk.windchimeweb.controller.request.*;
import com.github.ksgfk.windchimeweb.controller.response.*;
import com.github.ksgfk.windchimeweb.entity.User;
import com.github.ksgfk.windchimeweb.mapper.UserMapper;
import com.github.ksgfk.windchimeweb.service.UserService;
import com.github.ksgfk.windchimeweb.utility.EncryptUtility;
import com.github.ksgfk.windchimeweb.utility.StringUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/user")
@Api(tags = "用户管理")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserMapper users;
    @Autowired
    private UserService service;

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "登录")
    public LoginResponse login(@RequestBody LoginRequest request) {
        String acc = request.getAccount();
        String pwd = request.getPassword();
        if (StringUtility.isNullOrWhiteSpace(acc) || StringUtility.isNullOrWhiteSpace(pwd)) {
            return new LoginResponse(false, null);
        }
        User usr = users.selectByAccount(acc);
        if (usr == null) {
            return new LoginResponse(false, null);
        }
        byte[] encrypt = EncryptUtility.getSha256(pwd);
        byte[] dbPwd = usr.getPassword();
        if (Arrays.equals(encrypt, dbPwd)) {
            var token = service.login(usr);
            if (token.isEmpty()) {
                return new LoginResponse(false, null);
            }
            return new LoginResponse(true, token.get());
        } else {
            return new LoginResponse(false, null);
        }
    }

    @RequestMapping(value = "getUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "获取用户详细信息")
    public UserInfoResponse getInfo(@RequestBody UserToken info) {
        var usr = service.getUser(info.getToken());
        if (usr.isEmpty()) {
            return new UserInfoResponse(false, null);
        } else {
            return new UserInfoResponse(true, usr.get());
        }
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "登出")
    public EmptyResponse logout(@RequestBody UserToken info) {
        service.logout(info.getToken());
        return new EmptyResponse(true);
    }

    @RequestMapping(value = "register", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "注册账号")
    @Transactional(rollbackFor = {Exception.class})
    public EmptyResponse register(@RequestBody RegisterRequest info) {
        var old = users.selectByQQ(info.getQq());
        if (old == null) {
            User newUser = new User();
            newUser.setAccount(info.getAccount());
            newUser.setPassword(EncryptUtility.getSha256(info.getPassword()));
            newUser.setQq(info.getQq());
            int result = users.insertNewUser(newUser);
            if (result == 1) {
                return new EmptyResponse(true);
            } else {
                return new EmptyResponse(false);
            }
        } else {
            var enc = EncryptUtility.getSha256(info.getPassword());
            int r = users.updateAccountPassword(old.getId(), info.getAccount(), enc);
            if (r != 1) {
                throw new IllegalStateException();
            }
            return new EmptyResponse(true);
        }
    }

    @RequestMapping(value = "getFate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "注册账号")
    public UserIntertwinedFateCountRequest getFateCount(@RequestBody UserToken token) {
        var usr = service.getUser(token.getToken());
        if (usr.isEmpty()) {
            return new UserIntertwinedFateCountRequest(false, null);
        }
        var online = usr.get();
        var fate = users.selectUserIntertwinedFate(online.getId());
        return new UserIntertwinedFateCountRequest(true, fate.getCount());
    }

    public static class __PRIM {
        private __ITEM_PROP primary;

        public __ITEM_PROP getPrimary() {
            return primary;
        }

        public void setPrimary(__ITEM_PROP primary) {
            this.primary = primary;
        }
    }

    public static class __MINOR {
        private List<__ITEM_PROP> minor;

        public List<__ITEM_PROP> getMinor() {
            return minor;
        }

        public void setMinor(List<__ITEM_PROP> minor) {
            this.minor = minor;
        }
    }

    public static class __ITEM_PROP {
        private int type;
        private int value;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class __USER_ITEM {
        private int id;
        private int level;
        private __ITEM_PROP primary;
        private List<__ITEM_PROP> minor;
        private String rare;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public __ITEM_PROP getPrimary() {
            return primary;
        }

        public void setPrimary(__ITEM_PROP primary) {
            this.primary = primary;
        }

        public List<__ITEM_PROP> getMinor() {
            return minor;
        }

        public void setMinor(List<__ITEM_PROP> minor) {
            this.minor = minor;
        }

        public String getRare() {
            return rare;
        }

        public void setRare(String rare) {
            this.rare = rare;
        }
    }

    @RequestMapping(value = "getMyItem", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "获取用户储藏库物品")
    public UserHasItemResponse getItems(@RequestBody UserToken token) throws JsonProcessingException {
        var usr = service.getUser(token.getToken());
        if (usr.isEmpty()) {
            return new UserHasItemResponse(false, null);
        }
        var online = usr.get();
        var items = users.selectUserHasItems(online.getId());

        var res = new ArrayList<__USER_ITEM>(items.size());
        for (var i : items) {
            var it = new __USER_ITEM();
            it.setId(i.getItemId());
            it.setLevel(i.getLevel());
            it.setRare(i.getRare());
            var p = JSON_MAPPER.readValue(i.getPrimary(), __PRIM.class);
            var m = JSON_MAPPER.readValue(i.getMinor(), __MINOR.class);
            it.setPrimary(p.getPrimary());
            it.setMinor(m.getMinor());
            res.add(it);
        }
        return new UserHasItemResponse(true, res);
    }

    public static class __NEW_PROP {
        private List<__ITEM_PROP> change;
        private __USER_ITEM relics;

        public List<__ITEM_PROP> getChange() {
            return change;
        }

        public void setChange(List<__ITEM_PROP> change) {
            this.change = change;
        }

        public __USER_ITEM getRelics() {
            return relics;
        }

        public void setRelics(__USER_ITEM item) {
            this.relics = item;
        }
    }

    @RequestMapping(value = "strongRelics", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "强化物品")
    public IntensifyItemResponse intensifyItem(@RequestBody IntensifyItemRequest request) throws JsonProcessingException {
        var usr = service.getUser(request.getToken());
        if (usr.isEmpty()) {
            return new IntensifyItemResponse(false);
        }
        var online = usr.get();
        var items = users.selectUserHasItems(online.getId());
        if (request.getId() >= items.size()) {
            return new IntensifyItemResponse(false);
        }
        var intensify = items.get(request.getId());
        __PRIM prim = JSON_MAPPER.readValue(intensify.getPrimary(), __PRIM.class);
        __MINOR minor = JSON_MAPPER.readValue(intensify.getMinor(), __MINOR.class);
        var oldPrim = prim.getPrimary().getValue();
        var primIncJson = users.selectItemLevelUp(prim.getPrimary().getType());
        var primIncArr = JSON_MAPPER.readValue(primIncJson, Integer[].class);
        var primInc = primIncArr[ThreadLocalRandom.current().nextInt(primIncArr.length)];
        prim.getPrimary().setValue(prim.getPrimary().getValue() + primInc / 4);

        var selectMinor = minor.getMinor().get(ThreadLocalRandom.current().nextInt(minor.getMinor().size()));
        var oldMinor = selectMinor.getValue();
        if ((intensify.getLevel() + 1) % 4 == 0) {
            var minorIncJson = users.selectItemLevelUp(selectMinor.getType());
            var minorIncArr = JSON_MAPPER.readValue(minorIncJson, Integer[].class);
            var minorInc = minorIncArr[ThreadLocalRandom.current().nextInt(minorIncArr.length)];
            selectMinor.setValue(selectMinor.getValue() + minorInc);
        }

        String nowPrim = JSON_MAPPER.writeValueAsString(prim);
        String nowMinor = JSON_MAPPER.writeValueAsString(minor);

        int r = users.updateUserItem(intensify.getId(), nowPrim, nowMinor, intensify.getLevel() + 1);
        if (r != 1) {
            throw new IllegalStateException();
        }
        var propArr = new ArrayList<__ITEM_PROP>();
        var prop1 = new __ITEM_PROP();
        prop1.setType(prim.getPrimary().getType());
        prop1.setValue(prim.getPrimary().getValue() - oldPrim);
        propArr.add(prop1);
        if ((intensify.getLevel() + 1) % 4 == 0) {
            var prop2 = new __ITEM_PROP();
            prop2.setType(selectMinor.getType());
            prop2.setValue(selectMinor.getValue() - oldMinor);
            propArr.add(prop2);
        }
        var it = new __USER_ITEM();
        it.setId(intensify.getItemId());
        it.setLevel(intensify.getLevel() + 1);
        it.setRare(intensify.getRare());
        it.setPrimary(prim.getPrimary());
        it.setMinor(minor.getMinor());
        var result = new __NEW_PROP();
        result.setChange(propArr);
        result.setRelics(it);
        return new IntensifyItemResponse(true, result);
    }


    private static class __REAL_LOGIN_RESPONSE {
        private boolean success;
        private String data;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    @RequestMapping(value = "getWorkObject", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "工作信息")
    public void getWorkObject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var token = JSON_MAPPER.readValue(request.getInputStream(), UserToken.class);
        var usr = service.getUser(token.getToken());
        if (usr.isEmpty()) {
            return;
        }
        var online = usr.get();

        var msgLog = new LoginRequest();
        msgLog.setAccount(online.getAccount());
        msgLog.setPassword(new String(online.getPassword(), StandardCharsets.US_ASCII));
        var logRes = sendMsg("http://center.yumiswindchime.cn:1919/user/login", msgLog, __REAL_LOGIN_RESPONSE.class);

        var obj = JSON_MAPPER.createObjectNode();
        obj.put("token", logRes.getData());
        var r = sendMsg("http://center.yumiswindchime.cn:1919/user/getWorkObject", obj);
        try (var input = r.getInputStream()) {
            try (var resOut = response.getOutputStream()) {
                input.transferTo(resOut);
            }
        }
    }

    @RequestMapping(value = "startWork", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "工作")
    public void startWork(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var token = JSON_MAPPER.readValue(request.getInputStream(), StartWorkRequest.class);
        var usr = service.getUser(token.getToken());
        if (usr.isEmpty()) {
            return;
        }
        var online = usr.get();

        var msgLog = new LoginRequest();
        msgLog.setAccount(online.getAccount());
        msgLog.setPassword(new String(online.getPassword(), StandardCharsets.US_ASCII));
        var logRes = sendMsg("http://center.yumiswindchime.cn:1919/user/login", msgLog, __REAL_LOGIN_RESPONSE.class);

        var obj = JSON_MAPPER.createObjectNode();
        obj.put("token", logRes.getData());
        obj.put("workObject", token.getWorkObject());
        obj.put("workType", token.getWorkType());
        var r = sendMsg("http://center.yumiswindchime.cn:1919/user/startWork", obj);
        try (var input = r.getInputStream()) {
            try (var resOut = response.getOutputStream()) {
                input.transferTo(resOut);
            }
        }
    }

    private static <T> T sendMsg(String url, Object out, Class<T> type) throws IOException {
        URL loginUrl = new URL(url);
        var connection = (HttpURLConnection) loginUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        try (var output = connection.getOutputStream()) {
            JSON_MAPPER.writeValue(output, out);
        }
        T response;
        try (var input = connection.getInputStream()) {
            response = JSON_MAPPER.readValue(input, type);
        }
        return response;
    }

    private static HttpURLConnection sendMsg(String url, Object out) throws IOException {
        URL loginUrl = new URL(url);
        var connection = (HttpURLConnection) loginUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        try (var output = connection.getOutputStream()) {
            JSON_MAPPER.writeValue(output, out);
        }
        return connection;
    }
}
