package com.zle.dao;

import com.zle.entity.db.ZhuawawaEntity;
import com.zle.entity.db.ZhuawawaEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ZhuawawaDao {
    long countByExample(ZhuawawaEntityExample example);

    int deleteByExample(ZhuawawaEntityExample example);

    @Delete({
        "delete from zhuawawa",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into zhuawawa (user_name, time, ",
        "ymd, gift_name, status)",
        "values (#{userName,jdbcType=VARCHAR}, #{time,jdbcType=TIMESTAMP}, ",
        "#{ymd,jdbcType=VARCHAR}, #{giftName,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ZhuawawaEntity record);

    int insertSelective(ZhuawawaEntity record);

    List<ZhuawawaEntity> selectByExample(ZhuawawaEntityExample example);

    @Select({
        "select",
        "id, user_name, time, ymd, gift_name, status",
        "from zhuawawa",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.zle.dao.ZhuawawaDao.BaseResultMap")
    ZhuawawaEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZhuawawaEntity record, @Param("example") ZhuawawaEntityExample example);

    int updateByExample(@Param("record") ZhuawawaEntity record, @Param("example") ZhuawawaEntityExample example);

    int updateByPrimaryKeySelective(ZhuawawaEntity record);

    @Update({
        "update zhuawawa",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=TIMESTAMP},",
          "ymd = #{ymd,jdbcType=VARCHAR},",
          "gift_name = #{giftName,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ZhuawawaEntity record);
}