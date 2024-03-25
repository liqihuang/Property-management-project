package com.mashibing.controller;

import com.mashibing.bean.*;
import com.mashibing.result.R;
import com.mashibing.service.EstateService;
import com.mashibing.vo.CellMessage;
import com.mashibing.vo.UnitMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//房产控制器
@RestController
public class EstateController {

    @Resource
    private EstateService estateService;

    /**
     * 查询所属公司
     * @return
     */
    @RequestMapping("/estate/selectCompany")
    public R selectCompany(){
        System.out.println("selectCompany");
        List<TblCompany> companies = estateService.selectCompany();
        return new R(companies);
    }

    /**
     * 新增房产
     * @param fcEstate
     * @return
     */
    @RequestMapping("/estate/insertEstate")
    public R insertEstate(FcEstate fcEstate){
        System.out.println("insertEstate");
        Integer result = estateService.insertEstate(fcEstate);
        if(result == 0){
            return new R("房产编码已经存在！");
        }
        return new R(200,result.toString(),"插入成功");
    }

    /**
     * 查询楼宇
     * @param buildingNumber
     * @param estateCode
     * @return
     */
    @RequestMapping("/estate/selectBuilding")
    public R selectBuilding(Integer buildingNumber,String estateCode){
        System.out.println("selectBuilding");
        List<FcBuilding> fcBuildings = estateService.selectBuilding(buildingNumber,estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/updateBuilding")
    public R updateBuilding(FcBuilding fcBuilding){
        System.out.println("updateBuilding");
        Integer result = estateService.updateBuilding(fcBuilding);
        if(result == 1){
            return new R("更新数据成功");
        }
        return new R("更新数据失败！");
    }

    @RequestMapping("/estate/selectUnit")
    /**
     * 前端传递参数（数组类型）：
     * buildingCode：楼宇编码
     * unitCount：单元数量
     * 如果前端传递的参数与后端没有完全匹配的实体类，那么我们需要通过
     * 另外的类型来进行接受，也就是值对象：“VO”
     */
    public R selectUnit(@RequestBody UnitMessage[] unitMessage){
        System.out.println("selectUnit");
        List<FcUnit> allUnit = new ArrayList<>();
        for (UnitMessage message : unitMessage) {
            allUnit.addAll(estateService.selectUnit(message));
        }
        return new R(allUnit);
    }

    @RequestMapping("/estate/updateUnit")
    public R updateUnit(FcUnit fcUnit){
        System.out.println("updateUnit");
        Integer result = estateService.updateUnit(fcUnit);
        if(result == 1){
            return new R("更新数据成功！");
        }
        return new R("更新数据失败！");
    }

    @RequestMapping("/estate/insertCell")
    public R insertCell(@RequestBody CellMessage[] cellMessages){
        System.out.println("insertCell");
        List<FcCell> allfcCell = new ArrayList<>();
        for (CellMessage cellMessage : cellMessages) {
            allfcCell.addAll(estateService.insertCell(cellMessage));
        }
        return new R(allfcCell);
    }

    /**
     * 根据房产编码查询楼宇
     * @param estateCode
     * @return
     */
    @RequestMapping("/estate/selectBuildingByEstate")
    public R selectBuildingByEstate(String estateCode){
        System.out.println("selectBuildingByEstate");
        List<FcBuilding> fcBuildings = estateService.selectBuildingByEstate(estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/selectUnitByBuildingCode")
    public R selectUnitByBuildingCode(String buildingCode){
        System.out.println("selectUnitByBuildingCode");
        List<FcUnit> fcUnits = estateService.selectUnitByBuildingCode(buildingCode);
        return new R(fcUnits);
    }

    @RequestMapping("/estate/selectCell")
    public R selectCell(String unitCode){
        System.out.println("selectCell");
        List<FcCell> fcCells = estateService.selectCell(unitCode);
        return new R(fcCells);
    }

    @RequestMapping("/estate/selectEstate")
    public R selectEstate(String company){
        System.out.println("selectEstate");
        List<FcEstate> estates = estateService.selectEstate(company);
        return new R(estates);
    }

    @RequestMapping("/estate/selectAllEstate")
    public R selectAllEstate(){
        System.out.println("selectAllEstate");
        List<FcEstate> estates = estateService.selectAllEstate();
        return new R(estates);
    }

    /**
     * 此接口可以省略，在前端直接改为之前的selectBuildingByEstate即可，
     * 要注意业务层需要做更改，变成查询全部数据
     * @param estateCode
     * @return
     */
    @RequestMapping("/estate/selectBuildingByEstateCode")
    public R selectBuildingByEstateCode(String estateCode){
        System.out.println("selectBuildingByEstateCode");
        List<FcBuilding> fcBuildings = estateService.selectBuildingByEstateCode(estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/selectBuildingByEstateCodeAndBuildingCode")
    public R selectBuildingByEstateCodeAndBuildingCode(@RequestParam("estateCode") String estateCode,
                                                       @RequestParam("buildingCode") String buildingCode){
        System.out.println("selectBuildingByEstateCodeAndBuildingCode");
        FcBuilding fcBuilding = estateService.selectBuildingByEstateCodeAndBuildingCode(estateCode,buildingCode);
        return new R(fcBuilding);
    }
}
