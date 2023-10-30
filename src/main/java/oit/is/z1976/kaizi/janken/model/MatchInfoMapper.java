package oit.is.z1976.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {

  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1},#{user2},#{user1Hand},#{isActive});")
  void insertMatchInfoById(MatchInfo MatchInfo);

  @Select("SELECT * FROM matchinfo WHERE isActive=true;")
  ArrayList<MatchInfo> selectMatchInfoisActive();

  @Select("SELECT * FROM matchinfo WHERE isActive=true AND user2 = #{id}")
  MatchInfo selectByIdIsActive(int id);

  @Select("SELECT user1Hand FROM matchinfo WHERE isActive=true AND user2 = #{id}")
  String selectByidHand(int id);

}
