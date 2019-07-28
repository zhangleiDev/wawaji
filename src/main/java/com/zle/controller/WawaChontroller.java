package com.zle.controller;

import com.google.common.base.Strings;
import com.zle.entity.db.ZhuawawaEntity;
import com.zle.service.WawaService;
import com.zle.vo.WawaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lrr")
public class WawaChontroller {

    @Value("${lrr.check}")
    private String check;
    @Autowired
    WawaService service;
    @PostMapping
    public ResObject add(String userName,String giftName,Integer status){

        if(Strings.isNullOrEmpty(userName)){
            return new ResObject(800, "姓名不能为空！");
        }
        ZhuawawaEntity entity =new ZhuawawaEntity();
        if(status == null){
            status =3;
        }
        entity.setUserName(userName);
        entity.setGiftName(giftName);
        entity.setStatus(status);
        service.add(entity);
        return new ResObject(HttpStatus.OK.value(), entity.getId());
    }

    /**
     * 个人中奖历史记录
     * @param userName
     * @return
     */
    @GetMapping()
    public ResObject winnerList(String userName){
        if(Strings.isNullOrEmpty(userName)){
            return new ResObject(800, "姓名不能为空！");
        }
        List<WawaVo> zhuawawaEntities = service.winnerList(userName);
        return new ResObject(HttpStatus.OK.value(), zhuawawaEntities);
    }
    @GetMapping("/user")
    public ResObject check(String userName){
        ResObject res = new ResObject(HttpStatus.OK.value(), "");
        if(Strings.isNullOrEmpty(userName)){
            res.setCode(800);
            res.setMsg("姓名不能为空!");
            return res;
        }
        if(userName.equals("李蕊蕊")){

        }else{
            if(check.equals("true")){

                return new ResObject(801, false);
            }
        }
        if(service.checkDate(userName)){
            res.setMsg("true");
        }else {
            res.setCode(800);
            res.setMsg("每天最多一个奖品哦，明天再来吧～");
        }
        return res;
    }

}
