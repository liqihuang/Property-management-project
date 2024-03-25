package com.mashibing.service;

import com.mashibing.bean.TblUserRecord;

public interface LoginService {
    TblUserRecord login(String username, String password);
}
