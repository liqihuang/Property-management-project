package com.mashibing.service.impl;

import com.mashibing.bean.TblUserRecord;
import com.mashibing.mapper.TblUserRecordMapper;
import com.mashibing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private TblUserRecordMapper tblUserRecordMapper;

    @Override
    public TblUserRecord login(String username, String password) {
        TblUserRecord tblUserRecord = tblUserRecordMapper.login(username,password);
        return tblUserRecord;
    }
}
