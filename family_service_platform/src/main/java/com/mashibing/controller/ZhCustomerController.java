package com.mashibing.controller;


import com.mashibing.bean.ZhCustomer;
import com.mashibing.bean.ZhCustomerEstate;
import com.mashibing.result.R;
import com.mashibing.service.ZhCustomerService;
import com.mashibing.util.ExcelUtil;
import com.mashibing.vo.CustomerMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业主信息表 前端控制器
 * </p>
 *
 * @author lian
 * @since 2022-04-11
 */
@Slf4j
@RestController
@RequestMapping("/zhCustomer")
public class ZhCustomerController {
    @Resource
    private ZhCustomerService zhCustomerService;

    /**
     * 统一查询入口（优化查询）
     * 通过用户传递的参数来进行对应的查询
     * @param message 用户传递的参数
     * @return 查询结果：业主信息
     */
    @PostMapping("/selectCustomer")
    public R SelectCustomer(@RequestBody CustomerMessage message){
        log.info("SelectCustomer");
        List<ZhCustomer> zhCustomers = zhCustomerService.SelectCustomer(message);
        return new R(zhCustomers);
    }

    /**
     * 查询全部业主信息
     * @return
     */
    //@GetMapping("/selectCustomer")
    public R SelectAllCustomer(){
        System.out.println("SelectCustomer");
        List<ZhCustomer> zhCustomers = zhCustomerService.SelectAllCustomer();
        return new R(zhCustomers);
    }

    /**
     * 新增业主信息
     * @return 业主信息
     */
    @PostMapping("/insertCustomer")
    public R insertCustomer(ZhCustomer customer){
        System.out.println("insertCustomer");
        Integer result = zhCustomerService.insertCustomer(customer);
        if(result == 0){
            return new R("业主编码已存在！");
        }
        return new R("添加成功！");
    }

    /**
     * 根据用户选择的列和对应的值来查询业主信息
     * @param column 对应查询的列
     * @param value 对应列的值
     * @return 业主信息
     */
    @PostMapping("/selectCustomerByColumnAndValue")
    public R selectCustomerByColumnAndValue(@RequestParam("column") String column,
                                   @RequestParam("value") String value){
        System.out.println("selectCustomerByParam");
        List<ZhCustomer> zhCustomers = zhCustomerService.selectCustomerByColumnAndValue(column,value);
        return new R(zhCustomers);
    }

    /**
     * 根据业主类型来进行查询业主信息
     * @param customerType 业主类型
     * @return 查询的业主信息
     */
    @PostMapping("/selectByCustomerByCustomerType")
    public R selectByCustomerByCustomerType(String customerType){
        System.out.println("selectByCustomerByCustomerType");
        List<ZhCustomer> zhCustomers = zhCustomerService.selectByCustomerByCustomerType(customerType);
        return new R(zhCustomers);
    }

    /**
     * 根据前端传递的业主编码信息来修改业主状态
     * customerCodes 此参数为业主编码，如果出现多个业主编码，会通过"/"进行分割
     * status 状态：1开启 0禁用
     * @return 修改业主状态信息的结果
     */
    @PostMapping("/UpdateCustomerStatusByCustomerCode")
    public R UpdateCustomerStatusByCustomerCode(@RequestBody Map map){
        System.out.println("UpdateCustomerStatusByCustomerCode");
        String customerCodes = (String) map.get("customerCodes");
        String status = (String) map.get("status");
        Integer result = zhCustomerService.UpdateCustomerStatusByCustomerCode(customerCodes,status);
        if(result == 1){
            return new R("修改成功！");
        }else{
            return new R("修改失败！");
        }
    }

    /**
     * 根据前端传递的Excel表格文件和对应公司信息，解析出业主信息数据，存入到数据库中
     * @param file Excel文件（固定格式）
     * @param company 对应公司编号
     * @return 是否新增成功
     */
    @PostMapping("/uploadExcel")
    public R uploadExcel(MultipartFile file,String company){
        System.out.println("uploadExcel");
        if(file!=null&&file.getSize()>0){
            try {
                // 调用readExcel方法来进行解析Excel
                List<ZhCustomer> customers = ExcelUtil.readExcel((FileInputStream)file.getInputStream(),ZhCustomer.class);
                //调用业务层传递实体类customers集合数据
                zhCustomerService.insertAll(customers,company);
                return new R("批量添加成功!");
            } catch (Exception e) {
                e.printStackTrace();
                return new R("批量添加失败！");
            }
        }
        return new R("文件无法接受！");
    }

    /**
     * 新增业主入职信息
     * @param zhCustomerEstate 业主与房产关系数据
     * @return 是否添加成功
     */
    @PostMapping("/insertCustomerOrEstate")
    public R insertCustomerOrEstate(@RequestBody ZhCustomerEstate zhCustomerEstate){
        System.out.println("insertCustomerOrEstate");
        Integer result = zhCustomerService.insertCustomerOrEstate(zhCustomerEstate);
        if(result == 1){
            return new R("1");
        }else{
            return new R("2");
        }
    }

}

