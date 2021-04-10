package com.nicho.service;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2020/12/16 19:28
 */

public class ExcelUtil {

    public static HSSFWorkbook createExcel(String sheetName, List<Map<String,Object>> dataList) throws IllegalAccessException {
        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建sheet对象
        HSSFSheet sheet = wb.createSheet(sheetName);
        //在sheet里创建第一行，这里即是表头
        HSSFRow rowTitle = sheet.createRow(0);

        //写入表头的每一个列
        Map<String,Object> map = dataList.get(0);
        int i = 0;
        for (String key : map.keySet()) {

            //创建单元格(表头)
            rowTitle.createCell(i).setCellValue(key);
            System.out.println(i+ "   key == " + key);
            i++;
        }
        // 写入数据
        int j = 0;
        for (int o = 0; o < dataList.size(); o++) {
            Map<String,Object> mapList = dataList.get(o);
            HSSFRow rowData = sheet.createRow(j + 1);
            int l = 0;
            for (String mp : mapList.keySet()) {
                // 返回与此项对应的值
                Object value = mapList.get(mp);
                System.out.println(j +"    "+ l + "  value == " + value);
                rowData.createCell(l).setCellValue(String.valueOf(value));
                l++;
            }
            j++;
        }
        return wb;
    }
}
