package com.tt.repository;

import com.tt.entity.AddDept;
import com.tt.entity.DeptInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by liuhongbing on 2018/3/28.
 */

public interface CallableMapper {
    public  void  callProc(Map<String,String> map);

    public List<DeptInfo> callProc1(Map<String,String> map);

    public  List<DeptInfo> callProc2(Map<String,Object> map);

    public  Integer selectCount();

    public  List<DeptInfo> callProc3(Map<String,Object> map);

    public  Integer delDept(@Param("ids") String ids);


    public  Integer addDept(AddDept d);
}
