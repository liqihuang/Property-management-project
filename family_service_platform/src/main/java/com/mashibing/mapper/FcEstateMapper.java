package com.mashibing.mapper;

import com.mashibing.bean.FcEstate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 楼盘信息 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-04-11
 */
public interface FcEstateMapper extends BaseMapper<FcEstate> {
    /**
     * 查询全部的房产信息
     * @return
     */
    List<FcEstate> selectAllEstate();

}
