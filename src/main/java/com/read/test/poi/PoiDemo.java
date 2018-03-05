package com.read.test.poi;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bjyfwangrui on 2018/2/9.
 */
public class PoiDemo {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 按列解析Excel
     *
     * @param file 上传的文件
     */
    public static List<List<String>> parseExcelByCol(File file) {
        List<List<String>> result = Lists.newArrayList();
        try {
            Workbook workbook;
            String fileName = file.getName();
            if (fileName.endsWith(XLS)) {
                workbook = new HSSFWorkbook(new FileInputStream(file));
            } else {
                workbook = new XSSFWorkbook(new FileInputStream(file));
            }
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            while (rows.hasNext()) {
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    int colIndex = cell.getColumnIndex();
                    List<String> column;
                    try {
                        column = result.get(colIndex);
                    } catch (IndexOutOfBoundsException e) {
                        column = Lists.newArrayList();
                        result.add(column);
                    }
                    column.add(cell.getStringCellValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public static void main(String[] args) {
        System.out.println(parseExcelByCol(new File("C:\\Users\\wang.read\\Desktop\\测试商品.xlsx")));
    }
}
