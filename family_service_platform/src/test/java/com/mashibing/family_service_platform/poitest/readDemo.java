package com.mashibing.family_service_platform.poitest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用Poi读取excel表格数据
 */
public class readDemo {
    public static void main(String[] args) {
        try {
            List<Product> products = read("E:\\idea-family/a.xlsx");
            System.out.println(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Product> read(String path) throws Exception {
        List<Product> products = new ArrayList<>();
        // 1. 创建输入流
        FileInputStream fip = new FileInputStream(path);
        // 2. 再输入流中获取工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(fip);
        // 3. 在工作簿中获取目标工作表
        Sheet sheet = workbook.getSheetAt(0);
        // 4. 获取工作表中的行数（有数据的）
        int rowNum = sheet.getPhysicalNumberOfRows();
        // 5. 遍历所有的行，但是要注意第一行标题不获取，所以从下标1开始获取
        for(int i = 1;i<rowNum;i++){
            // 获取所有行
            Row row = sheet.getRow(i);
            if(row!=null){
                //用于保存每条数据的集合
                List<String> list = new ArrayList<>();
                for (Cell cell : row) {
                    if(cell!=null){
                        //把单元各种的所有数据格式设置为String
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        //获取所有单元格数据
                        String value = cell.getStringCellValue();
                        if(value!=null&&!value.equals("")){
                            //将每个单元格的数据存储到集合中
                            list.add(value);
                        }
                    }
                }
                //把获取到的每一条数据封装成一个Product类型
                if(list.size()>0){
                    Product product = new Product(Integer.parseInt(list.get(0)),list.get(1),Double.parseDouble(list.get(2)),Integer.parseInt(list.get(3)));
                    //将product封装到list集合中
                    products.add(product);
                }
            }
        }
        return products;
    }
}
