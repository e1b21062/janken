package oit.is.z1976.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users where id = #{id}")
  User selectById(int id);

  @Select("SELECT id from users where name = #{name}")
  int selectByNameToUser2(String name);

  @Select("SELECT * from users")
  ArrayList<User> selectAllUser();
}
