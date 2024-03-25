package com.mashibing.service;

import com.mashibing.bean.ZhCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mashibing.bean.ZhCustomerEstate;
import com.mashibing.vo.CustomerMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 业主信息表 服务类
 * </p>
 *
 * @author lian
 * @since 2022-04-11
 */
public interface ZhCustomerService extends IService<ZhCustomer> {
    /**
     * 新增业主信息方法
     * @param customer 业主信息
     * @return 返回新增的业主信息
     */
    Integer insertCustomer(ZhCustomer customer);

    /**
     * 全部查询方法（优化查询）
     * 通过用户传递的参数来进行对应的查询
     * @param message 用户传递的参数
     * @return 查询结果：业主信息
     */
    List<ZhCustomer> SelectCustomer(@RequestBody CustomerMessage message);

    /**
     * 查询全部业主信息
     * @return 全部业主信息
     */
    List<ZhCustomer> SelectAllCustomer();

    /**
     * 根据用户选择的列和对应的值来查询业主信息
     * @param column 对应查询的列
     * @param value 对应列的值
     * @return 业主信息
     */
    List<ZhCustomer> selectCustomerByColumnAndValue(String column,String value);

    /**
     * 根据业主类型来进行查询业主信息
     * @param customerType 业主类型
     * @return 查询的业主信息
     */
    List<ZhCustomer> selectByCustomerByCustomerType(String customerType);

    /**
     * 根据前端传递的业主编码信息来修改业主状态为启用
     * 状态为：
     *  1开启
     *  0禁用
     * @param customerCodes 此参数为业主编码，如果出现多个业主编码，会通过"/"进行分割
     * @return 修改业主状态信息的结果
     */
    Integer UpdateCustomerStatusByCustomerCode(String customerCodes,String status);

    /**
     * 接收解析Excel表中的所有数据，以实体类ZhCustomer集合类型接收，并且做新增业主数据操作
     * @param customers 集合类型的实体类数据
     * @return 新增结果
     */
    Integer insertAll(List<ZhCustomer> customers,String company);

    /**
     * 新增业主入职信息
     * @param zhCustomerEstate 业主与房产关系数据
     * @return 是否添加成功
     */
    Integer insertCustomerOrEstate(ZhCustomerEstate zhCustomerEstate);

}
