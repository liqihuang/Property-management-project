package com.mashibing.mapper;

import com.mashibing.bean.ZhCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 业主信息表 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-04-11
 */
public interface ZhCustomerMapper extends BaseMapper<ZhCustomer> {

    /**
     * 查询全部业主数据
     * @return
     */
    List<ZhCustomer> selectAll();

}
