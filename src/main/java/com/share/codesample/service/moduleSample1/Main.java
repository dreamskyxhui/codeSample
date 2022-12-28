package com.share.codesample.service.moduleSample1;

import com.share.codesample.service.classSample3.UserInfo;
import com.share.codesample.util.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.catalina.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Main {

    /**
     * 获取单元格值
     *
     * @param row 获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, Integer column) {
        if (null == row || null == column) {
            return null;
        }

        try {
            Cell cell = row.getCell(column);
            if (null == cell) {
                return null;
            }

            if (CellType.STRING == cell.getCellType()) {
                return cell.getStringCellValue();
            }

            if (CellType.BOOLEAN == cell.getCellType()) {
                return cell.getBooleanCellValue();
            }

            if (CellType.NUMERIC == cell.getCellType()
                    || CellType.FORMULA == cell.getCellType()) {
                Object val = cell.getNumericCellValue();
                if (DateUtil.isCellDateFormatted(cell)) {
                    return DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                }

                if ((Double) val % 1 != 0) {
                    return new BigDecimal(val.toString());
                }

                return new DecimalFormat("0").format(val);
            }

            if (cell.getCellType() == CellType.ERROR) {
                return cell.getErrorCellValue();
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<UserInfo> readExcel(String sheetName, File file) {
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheet(sheetName);
            if (null == sheet) {
                return null;
            }

            int rows = sheet.getPhysicalNumberOfRows();
            if (rows <= 0) {
                return null;
            }

            //读取第1行作为表头
            Map<String, Integer> fieldHead = new LinkedHashMap<>();
            Row heard = sheet.getRow(0);
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                Cell cell = heard.getCell(i);
                if (null == cell) {
                    fieldHead.put(null, i);
                    continue;
                }

                String value = getCellValue(heard, i).toString();
                fieldHead.put(value, i);
            }

            //从第2行开始读取数据
            List<UserInfo> resultList = new LinkedList<>();
            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }

                UserInfo userInfo = new UserInfo();

                Integer cellIndex = fieldHead.get("idNo");
                Object cellValue = getCellValue(row, cellIndex);
                if (null != cellValue) {
                    userInfo.setIdNo(cellValue.toString());
                }

                cellIndex = fieldHead.get("mobile");
                cellValue = getCellValue(row, cellIndex);
                if (null != cellValue) {
                    userInfo.setMobile(cellValue.toString());
                }

                cellIndex = fieldHead.get("name");
                cellValue = getCellValue(row, cellIndex);
                if (null != cellValue) {
                    userInfo.setName(cellValue.toString());
                }

                cellIndex = fieldHead.get("sex");
                cellValue = getCellValue(row, cellIndex);
                if (null != cellValue) {
                    userInfo.setSex((Integer) cellValue);
                }

                cellIndex = fieldHead.get("age");
                cellValue = getCellValue(row, cellIndex);
                if (null != cellValue) {
                    userInfo.setAge((Integer) cellValue);
                }

                resultList.add(userInfo);

            }

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
