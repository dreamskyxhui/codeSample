package com.share.codesample.service.moduleSample1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import jxl.Sheet;
import jxl.Workbook;

public class Main {

    public List<List<String>> readExcel(File file) {
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            Workbook wb = Workbook.getWorkbook(is);

            //获取Excel sheet数量
            int sheetSize = wb.getNumberOfSheets();
            if (sheetSize <= 0) {
                return null;
            }

            //默认只读取第一个sheet
            Sheet sheet = wb.getSheet(0);
            Integer rowCount = sheet.getRows();
            if (rowCount <= 0) {
                return null;
            }

            //读取第一行作为表头
            Map<String, Integer> filedInfo = new LinkedHashMap<>();

            List<List<String>> sheetResult = new LinkedList<>();
            for (int i = 0; i < sheet.getRows(); i++) {
                List<String> rowResult = new LinkedList<>();
                for (int j = 0; j < sheet.getColumns(); j++) {
                    String cellInfo = sheet.getCell(j, i).getContents();
                    if (cellInfo.isEmpty()) {
                        continue;
                    }
                    rowResult.add(cellInfo);
                }
                sheetResult.add(rowResult);
            }

            return sheetResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
