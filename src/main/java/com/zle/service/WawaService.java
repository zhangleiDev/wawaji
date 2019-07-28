package com.zle.service;

import com.zle.dao.ZhuawawaDao;
import com.zle.entity.db.ZhuawawaEntity;
import com.zle.entity.db.ZhuawawaEntityExample;
import com.zle.vo.WawaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WawaService {

    @Autowired
    ZhuawawaDao zhuawawaDao;

    public ZhuawawaEntity add(ZhuawawaEntity entity){
        Date date = new Date();
        entity.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        entity.setYmd(sdf.format(date));
        zhuawawaDao.insert(entity);
        return entity;
    }

    /**
     * 校验每天智能抓取一个
     * @param userName
     * @return
     */
    public boolean checkDate(String userName){

        ZhuawawaEntityExample example = new ZhuawawaEntityExample();
        example.createCriteria()
                .andUserNameEqualTo(userName)
                .andStatusEqualTo(1)
                .andYmdEqualTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        List<ZhuawawaEntity> zhuawawaEntities = zhuawawaDao.selectByExample(example);
        if(zhuawawaEntities.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 个人中奖历史
     * @param name
     * @return
     */
    public List<WawaVo> winnerList(String name){

        ZhuawawaEntityExample example = new ZhuawawaEntityExample();
        example.createCriteria()
                .andUserNameEqualTo(name)
                .andStatusEqualTo(1);
        List<ZhuawawaEntity> zhuawawaEntities = zhuawawaDao.selectByExample(example);
        return convert(zhuawawaEntities);
    }

    /**
     * 全部中奖历史
     * @return
     */
    public List<WawaVo> allWinnerList(){

        ZhuawawaEntityExample example = new ZhuawawaEntityExample();
        example.createCriteria()
                .andStatusEqualTo(1);
        List<ZhuawawaEntity> zhuawawaEntities = zhuawawaDao.selectByExample(example);

        return convert(zhuawawaEntities);
    }

    public List<WawaVo> convert(List<ZhuawawaEntity> list){
        List<WawaVo> wawaVos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ZhuawawaEntity entity : list) {
            WawaVo vo = new WawaVo();
            vo.setId(entity.getId());
            vo.setGiftName(entity.getGiftName());
            vo.setTime(sdf.format(entity.getTime()));
            wawaVos.add(vo);
        }
        return wawaVos;

    }

}
