package com.example.demo.mapper.ground;

import com.example.demo.entity.student;
import com.example.demo.entity.t001userinfo;
import com.example.demo.entity.t0100placeinfo;
import com.example.demo.entity.t0922;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface groundMapper {


  public List<t0922> query0992(Map<String,String> sqlparam);

}