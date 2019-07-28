package com.zle.entity.db;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class ZhuawawaEntity {
    /**
     * id
     */
    private Integer id;

    /**
     * user_name
     *   用户名称
     */
    private String userName;

    /**
     * time
     *   抓取时间
     */
    private Date time;

    /**
     * ymd
     *   yyyy-mm-dd
     */
    private String ymd;

    /**
     * gift_name
     *   奖品名称
     */
    private String giftName;

    /**
     * status
     *   1:想要，2:重新选择,3:什么都没做
     */
    private Integer status;
}