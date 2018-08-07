package com.tt.services;

import com.tt.entity.AddDept;
import com.tt.entity.DeptInfo;
import com.tt.repository.CallableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by liuhongbing on 2018/3/28.
 */
@Service("services")
public class CallableServices {
    @Autowired
    CallableMapper mapper;

    public void callProc(Map<String, String> map) {
        mapper.callProc(map);
    }

    public List<DeptInfo> getDeptInfos(Map<String, String> map) {
        return mapper.callProc1(map);
    }

    public List<DeptInfo> getDeptInfos2(Map<String, Object> map) {
        return mapper.callProc2(map);
    }

    public Integer selectCount() {
        return mapper.selectCount();
    }

    public List<DeptInfo> getDeptInfos3(Map<String, Object> map) {
        return mapper.callProc3(map);
    }

    public Integer del(String ids) {
        Integer integer = mapper.delDept(ids);
        System.out.println("Service-Delete:" + integer);
        return integer;
    }

    @Transactional
    public Integer add(AddDept a) {
        return mapper.addDept(a);
    }

    /**
     * 从输入流中获取字符串
     * @param in 输入流
     * @return  返回的字节信息
     * @throws Exception 异常
     */
    public byte[] getBytesByStream(InputStream in) throws Exception {
        if (in == null) throw new Exception("inputStream is null");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        byte[] result = out.toByteArray();
        out.close();
        return result;
    }
}
