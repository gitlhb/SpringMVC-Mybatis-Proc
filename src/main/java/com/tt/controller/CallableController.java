package com.tt.controller;

import com.tt.entity.*;
import com.tt.services.CallableServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuhongbing on 2018/3/28.
 */
@Controller
public class CallableController {
    @Resource
    CallableServices services;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> login(@RequestBody RequestObj req) {
        System.out.println(req);
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", req.getId());
        map.put("parent_dept_id", req.getParent_dept_id());
        services.callProc(map);
        System.out.println(map);
        Properties p=new Properties();
        return map;
    }

    @RequestMapping(value = "/par", method = RequestMethod.POST)
    public @ResponseBody
    List<DeptInfo> par(@RequestBody ParIdRequestObj obj) {
        System.out.println(obj);
        Map<String, String> map = new HashMap<String, String>();
        map.put("parId", obj.getParId());
        List<DeptInfo> deptInfos = services.getDeptInfos(map);
        System.out.println(deptInfos);
        return deptInfos;
    }

    @RequestMapping(value = "/par1", method = RequestMethod.POST)
    public @ResponseBody
    DataTablesResult par1(@RequestParam("parId") String obj, int parId, int draw, int start,
                          int length, @RequestParam("search[value]") String search,
                          @RequestParam("order[0][column]") int orderCol,
                          @RequestParam("order[0][dir]") String orderDir,
                          String searchItem, String minDate, String maxDate) {
        System.out.println("parId:" + parId);
        System.out.println("draw:" + draw);
        System.out.println("start:" + start);
        System.out.println("length:" + length);
        System.out.println("search:" + search);
        System.out.println("orderCol:" + orderCol);
        System.out.println("orderDir:" + orderDir);
        System.out.println("searchItem:" + searchItem);
        System.out.println("minDate:" + minDate);
        System.out.println("maxDate:" + maxDate);
        String[] columns = {"checkbox", "dept_id", "dept_name", "parent_dept_id", "`desc`", "location", "dt_create", "dt_update"};
        String orderC = "";
        if ("desc".equals(orderDir)) {
            orderC = " " + columns[orderCol] + " desc";
        } else {
            orderC = " " + columns[orderCol] + " asc";
        }

        /*
        DataTablesResult result = new DataTablesResult();
        System.out.println(obj);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("currpage", (start/10)+1);
        map.put("columns", "*");
        map.put("tablename", "t_dept");
        map.put("sCondition", "parent_dept_id="+parId);
        map.put("order_field", "dept_id");
        map.put("asc_field", 1);
        map.put("primary_field", "dept_id");
        map.put("pagesize", length);
        List<DeptInfo> deptInfos = services.getDeptInfos2(map);
        System.out.println(deptInfos);
        result.setSuccess(true);
        result.setData(deptInfos);
        int count=services.selectCount();
        result.setRecordsTotal(count);
        result.setRecordsFiltered(count);
        return result;
        */
        DataTablesResult result = new DataTablesResult();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fields", "*");
        map.put("tables", "t_dept");
        map.put("wher", "1=1");
        map.put("orderby", " " + orderC);
        map.put("pageindex", (start / 10) + 1);
        map.put("pageSize", length);
        map.put("totalcount", null);
        map.put("pagecount", null);
        List<DeptInfo> deptInfos = services.getDeptInfos3(map);
        if (deptInfos.size()>0){
            System.out.println(map);
            result.setSuccess(true);
            result.setData(deptInfos);
            result.setRecordsTotal(services.selectCount());
            result.setRecordsFiltered(Integer.valueOf(map.get("totalcount").toString()));
        }
        else {
            result.setData(null);
            result.setSuccess(false);
            result.setRecordsFiltered(0);
            result.setRecordsTotal(0);
        }
        return result;
    }

    @RequestMapping("/count")
    public @ResponseBody
    Map<String, Integer> getCount() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("count", services.selectCount());
        return map;
    }

    @RequestMapping("/search")
    public @ResponseBody
    DataTablesResult search(@RequestParam("searchKey") String searchKey, @RequestParam("minDate") String minDate, @RequestParam("maxDate") String maxDate, int start, int length,
                            @RequestParam("search[value]") String search,
                            @RequestParam("order[0][column]") int orderCol1,
                            @RequestParam("order[0][dir]") String orderDir) {

        System.out.println("searchKey:" + searchKey);
        System.out.println("minDate:" + minDate);
        System.out.println("maxDate:" + maxDate);
        DataTablesResult result = new DataTablesResult();
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder();
        sb.append("1=1");
        if (!"".equals(searchKey)) {
            sb.append(" and dept_name like '%" + searchKey + "%'");
        }
        if (!"".equals(minDate) && !"".equals(maxDate)) {
            sb.append(" and dt_create BETWEEN '" + minDate + " 00:00:00' AND '" + maxDate + " 23:59:59'");
        }
        System.out.println("where:" + sb.toString());
        String[] columns = {"checkbox", "dept_id", "dept_name", "parent_dept_id", "`desc`", "location", "dt_create", "dt_update"};
        String orderC = "";
        if ("desc".equals(orderDir)) {
            orderC = " " + columns[orderCol1] + " desc";
        } else {
            orderC = " " + columns[orderCol1] + " asc";
        }

        map.put("fields", "*");
        map.put("tables", "t_dept");
        map.put("wher", sb.toString());
        map.put("orderby", " " + orderC);
        map.put("pageindex", (start / 10) + 1);
        map.put("pageSize", length);
        map.put("totalcount", 0);
        map.put("pagecount", 0);
        List<DeptInfo> deptInfos = services.getDeptInfos3(map);
        result.setSuccess(true);
        result.setData(deptInfos);
        result.setRecordsTotal(services.selectCount());
        result.setRecordsFiltered(Integer.valueOf(map.get("totalcount").toString()));
        return result;
    }

    @RequestMapping(value = "del/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id") String ids) {
        System.out.println(ids);
        String[] str = ids.substring(0, ids.length() - 1).split(",");
        StringBuilder sb = new StringBuilder();
        for (String id : str) {
            sb.append("'" + id + "',");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Integer ins = services.del(sb.substring(0, sb.length() - 1));
            System.out.print(ins);
            map.put("isok", true);
            map.put("message", "success");
        } catch (Exception ex) {
            map.put("isok", false);
            map.put("message", "Exep");
            ex.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/deptadd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addDept(AddDept dept) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("deptName:" + dept.getDeptName());
        System.out.println("parentDeptId:" + dept.getParentDeptId());
        System.out.println("desc:" + dept.getDesc());
        dept.setDtCreate(sf.format(new Date()));
        dept.setDtUpdate(sf.format(new Date()));
        Integer zj = services.add(dept);
        System.out.println("生成的主键为:"+zj);
        System.out.println("dept:" + dept.toString());
        Map<String, String> map = new HashMap<String, String>();
        if (dept.getDeptId() != null) {
            map.put("returnCode", "SUCCESS");
            map.put("主键", dept.getDeptId().toString());
        } else {
            map.put("returnCode", "ERROR");
        }
        return map;
    }



}
