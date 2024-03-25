package com.mashibing.family_service_platform;

import com.mashibing.bean.TblCompany;
import com.mashibing.bean.TblUserRecord;
import com.mashibing.bean.ZhCustomer;
import com.mashibing.mapper.TblCompanyMapper;
import com.mashibing.mapper.TblUserRecordMapper;
import com.mashibing.mapper.ZhCustomerMapper;
import com.mashibing.service.EstateService;
import com.mashibing.service.LoginService;
import com.mashibing.service.ZhCustomerService;
import com.mashibing.vo.CustomerMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class FamilyServicePlatformApplicationTests {

    @Resource
    TblUserRecordMapper tblUserRecordMapper;

    @Resource
    LoginService loginService;

    @Resource
    TblCompanyMapper companyMapper;

    @Resource
    EstateService service;

    @Resource
    ZhCustomerService service1;

    @Resource
    ZhCustomerMapper mapper;


    @Test
    void contextLoads() {
        CustomerMessage message = new CustomerMessage();
        message.setCustomerType("正式业主");
        List<ZhCustomer> customers = service1.SelectCustomer(message);
        for (ZhCustomer customer : customers) {
            log.debug("abc");
            log.error("输出"+customer);
        }
    }

}
