package com.zl.erp.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Description: easypoi 工具类
 * @Author: zhutao
 * @Date: 2019/9/20
 */
public final class EasyPoiUtils {

    private EasyPoiUtils() {
    }

    /**
     * excel下载
     *
     * @param fileName 文件名
     * @param response 响应
     * @param workbook workbook
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 导出
     *
     * @param dataList     dataList
     * @param clz          clz
     * @param fileName     fileName
     * @param response     response
     * @param exportParams exportParams
     * @param <T>          T
     */
    private static <T> void defaultExport(List<T> dataList, Class<?> clz, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, clz, dataList);
        if (workbook != null) {
            CellStyle titleStyle = workbook.createCellStyle();
            // 居中
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setBorderRight(BorderStyle.THIN);
            titleStyle.setWrapText(true);
            Font font = workbook.createFont();
            font.setBold(true);
            titleStyle.setFont(font);
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * 导出
     *
     * @param dataList       dataList
     * @param title          title
     * @param sheetName      sheetName
     * @param clz            clz
     * @param fileName       fileName
     * @param isCreateHeader isCreateHeader
     * @param response       response
     * @param <T>            T
     */
    public static <T> void exportExcel(List<T> dataList, String title, String sheetName, Class<?> clz, String fileName, boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(dataList, clz, fileName, response, exportParams);
    }

    /**
     * 导出
     *
     * @param dataList  dataList
     * @param title     title
     * @param sheetName sheetName
     * @param clz       clz
     * @param fileName  fileName
     * @param response  response
     * @param <T>       T
     */
    public static <T> void exportExcel(List<T> dataList, String title, String sheetName, Class<?> clz, String fileName, HttpServletResponse response) {
        defaultExport(dataList, clz, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     * 导出
     *
     * @param dataList dataList
     * @param fileName fileName
     * @param response response
     */
    private static void defaultExport(List<Map<String, Object>> dataList, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(dataList, ExcelType.HSSF);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * exportExcel
     *
     * @param dataList dataList
     * @param fileName fileName
     * @param response response
     */
    public static void exportExcel(List<Map<String, Object>> dataList, String fileName, HttpServletResponse response) {
        defaultExport(dataList, fileName, response);
    }

    /**
     * importExcel
     *
     * @param filePath   filePath
     * @param titleRows  titleRows
     * @param headerRows headerRows
     * @param clz        clz
     * @param <T>        T
     * @return return
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> clz) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }

        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);

        try {
            return ExcelImportUtil.importExcel(new File(filePath), clz, params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * importExcel
     *
     * @param file       file
     * @param titleRows  titleRows
     * @param headerRows headerRows
     * @param clz        clz
     * @param <T>        T
     * @return return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> clz) {
        if (file == null) {
            return null;
        }

        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);

        try {
            return ExcelImportUtil.importExcel(file.getInputStream(), clz, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * importExcel
     *
     * @param file file
     * @param clz  clz
     * @return return
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> clz) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        try {
            return ExcelImportUtil.importExcel(file.getInputStream(), clz, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

