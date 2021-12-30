package com.github.ksgfk.windchimeweb.controller;

import com.github.ksgfk.windchimeweb.controller.request.UserToken;
import com.github.ksgfk.windchimeweb.controller.response.EmptyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/card")
@Api(tags = "卡牌管理")
@CrossOrigin(origins = "*")
public class CardController {
    @RequestMapping(value = "drawCard", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "抽卡")
    public EmptyResponse drawCard() {
        return new EmptyResponse(true);
    }
}
