package com.mashibing.mapper;

import com.mashibing.bean.TblUserRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户档案 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-04-11
 */
public interface TblUserRecordMapper extends BaseMapper<TblUserRecord> {
    /**
     * 查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 当前用户的信息
     */
    TblUserRecord login(@Param("username") String username,@Param("password") String password);
}
