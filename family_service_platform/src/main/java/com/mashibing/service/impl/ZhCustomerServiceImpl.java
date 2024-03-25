package com.mashibing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mashibing.bean.ZhCustomer;
import com.mashibing.bean.ZhCustomerEstate;
import com.mashibing.mapper.ZhCustomerEstateMapper;
import com.mashibing.mapper.ZhCustomerMapper;
import com.mashibing.service.ZhCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashibing.vo.CustomerMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 业主信息表 服务实现类
 * </p>
 *
 * @author lian
 * @since 2022-04-11
 */
@Service
public class ZhCustomerServiceImpl extends ServiceImpl<ZhCustomerMapper, ZhCustomer> implements ZhCustomerService {

    @Resource
    private ZhCustomerMapper zhCustomerMapper;

    @Resource
    private ZhCustomerEstateMapper zhCustomerEstateMapper;

    @Override
    public List<ZhCustomer> SelectAllCustomer() {
        List<ZhCustomer> zhCustomers = zhCustomerMapper.selectAll();
        return zhCustomers;
    }

    @Override
    public Integer insertCustomer(ZhCustomer customer) {
        Integer result = 0;
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_code",customer.getCustomerCode());
        ZhCustomer zhCustomer = zhCustomerMapper.selectOne(queryWrapper);
        if(zhCustomer == null){
            result = zhCustomerMapper.insert(customer);
        }
        return result;
    }

    /**
     * 查询优化方法，统一查询入口
     * @param message 用户传递的参数
     * @return
     */
    @Override
    public List<ZhCustomer> SelectCustomer(CustomerMessage message) {
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(message.getColumn()) ||!StringUtils.isEmpty(message.getValue())){
            queryWrapper.eq(message.getColumn(),message.getValue());
        }
        if(!StringUtils.isEmpty(message.getCustomerType())) {
            System.out.println(message.getCustomerType());
            queryWrapper.eq("customer_type", message.getCustomerType());
        }
        List<ZhCustomer> customers = zhCustomerMapper.selectList(queryWrapper);
        return customers;
    }

    @Override
    public List<ZhCustomer> selectCustomerByColumnAndValue(String column, String value) {
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,value);
        List<ZhCustomer> zhCustomers = zhCustomerMapper.selectList(queryWrapper);
        return zhCustomers;
    }

    @Override
    public List<ZhCustomer> selectByCustomerByCustomerType(String customerType) {
        List<ZhCustomer> customers;
        // 如果参数为空，直接调用全部数据查询方法
        if(customerType.equals("")){
            return SelectAllCustomer();
        }else{
            QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("customer_type",customerType);
            customers = zhCustomerMapper.selectList(queryWrapper);
        }
        return customers;
    }

    @Override
    public Integer UpdateCustomerStatusByCustomerCode(String customerCodes,String status) {
        Integer result = 0;
        UpdateWrapper<ZhCustomer> updateWrapper = new UpdateWrapper<>();
        System.out.println(customerCodes);
        System.out.println(customerCodes.contains("|"));
        if(customerCodes.contains("|")){
            System.out.println("多个数据");
            String[] codes = customerCodes.split("[|]");
            for (int i = 0;i<codes.length;i++) {
                //这里要注意必须重新创建UpdateWrapper对象，否则，查询条件会累加
                UpdateWrapper<ZhCustomer> updateWrapper1 = new UpdateWrapper<>();
                updateWrapper1.set("customer_status",status).eq("customer_code",codes[i]);
                result = zhCustomerMapper.update(null,updateWrapper1);
            }
            return result;
        }else{
            updateWrapper.set("customer_status",status).eq("customer_code",customerCodes);
            result = zhCustomerMapper.update(null,updateWrapper);
            return result;
        }
    }

    @Override
    public Integer insertAll(List<ZhCustomer> customers,String company) {
        Integer result = 0;
        if(customers.size()>0){
            for (ZhCustomer customer : customers) {
                customer.setCompany(company);
                result = zhCustomerMapper.insert(customer);
            }
        }
        return result;
    }

    @Override
    public Integer insertCustomerOrEstate(ZhCustomerEstate zhCustomerEstate) {
        Integer result = 0;
        if(zhCustomerEstate != null){
            result = zhCustomerEstateMapper.insert(zhCustomerEstate);
            return result;
        }
        return result;
    }


}
