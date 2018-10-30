import hscs.utils.dto.FormHeadDto;
import hscs.utils.dto.FormTextDto;
import hscs.utils.dto.FormTitleDto;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Created by kai on 2017/6/6.
 */
public class ExportExcelUtil {
    public static final String TITLE_MAP = "titleMap";
    private static final String MSIE = "MSIE";
    private static final String MOZILLA = "Mozilla";
    private static final String STRING_TRIDENT="Trident";
    private static final String STRING_EDGE="Edge";

    public static <E> void startDownLoad(HttpServletResponse response, String sheetName, Map<Integer, FormHeadDto> formHeadMap, List<FormTitleDto> formTitleDtoList, FormTextDto formTextDto, List<E> dataList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);// 创建一个表

        try {
            Map map = writeDataTitle(workbook, sheet, formTitleDtoList);
            Map temp = (Map) map.get(TITLE_MAP);
            writeDataHead(workbook, sheet, formHeadMap, temp.size());
            writeText(workbook, sheet, formTextDto, dataList, map);
            ioWrite(workbook, sheet, response, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 此方法支持单sheet和多sheet导出
     *
     * @param response
     * @param sheetsList
     * @param excelName
     * @param <E>
     */
    public static <E> void startDownLoadForSheets(HttpServletRequest request, HttpServletResponse response, List sheetsList, String excelName) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        for (int i = 0; i < sheetsList.size(); i++) {

            String sheetName = null;
            Map<Integer, FormHeadDto> sheetFormHeadMap = null;
            List<FormTitleDto> sheetFormTitleDtolist = null;
            FormTextDto sheetFormTextDto = null;
            List dataList = null;

            Map sheetMap = (Map) sheetsList.get(i);
            sheetName = (String) sheetMap.get("sheetName");
            sheetFormHeadMap = (Map) sheetMap.get("formHead");
            sheetFormTitleDtolist = (List<FormTitleDto>) sheetMap.get("formTitle");
            sheetFormTextDto = (FormTextDto) sheetMap.get("textStyle");
            dataList = (List) sheetMap.get("data");

            try {
                HSSFSheet hssfSheet = workbook.createSheet(sheetName);
                Map map = writeDataTitle(workbook, hssfSheet, sheetFormTitleDtolist);
                Map temp = (Map) map.get(TITLE_MAP);
                writeDataHead(workbook, hssfSheet, sheetFormHeadMap, temp.size());
                writeText(workbook, hssfSheet, sheetFormTextDto, dataList, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ioWriteForSheets(request, workbook, response, excelName);

    }

    public static void writeDataHead(HSSFWorkbook workbook, HSSFSheet sheet, Map<Integer, FormHeadDto> headMap, int length) {
        //初始化表头
        if (headMap != null) {
            for (int i = 0; i < headMap.size(); i++) {
                HSSFRow row2 = sheet.createRow(i);
                for (int j = 0; j < length; j++) {
                    FormHeadDto tempDto = headMap.get(i);
                    // 表头标题样式,时间样式
                    HSSFFont headfont = workbook.createFont();
                    headfont.setFontName(tempDto.getFontName());
                    headfont.setFontHeightInPoints(tempDto.getFontHeightInPoints());// 字体大小
                    headfont.setBoldweight(tempDto.getBoldweight());
                    headfont.setColor(tempDto.getFontColor());
                    HSSFCellStyle headstyle = workbook.createCellStyle();//加粗
                    headstyle.setFillForegroundColor(tempDto.getFillForegroundColor());//背景
                    headstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                    headstyle.setFont(headfont);
                    headstyle.setAlignment(tempDto.getAlignment());// 左右居中
                    headstyle.setVerticalAlignment(tempDto.getVerticalAlignment());// 上下居中

                    HSSFCell cell3 = row2.createCell(j);
                    cell3.setCellValue("");
                    cell3.setCellStyle(headstyle);
                }

            }

            for (int i = 0; i < headMap.size(); i++) {
                FormHeadDto tempDto = headMap.get(i);
                // 表头标题样式,时间样式
                HSSFFont headfont = workbook.createFont();
                headfont.setFontName(tempDto.getFontName());
                headfont.setFontHeightInPoints(tempDto.getFontHeightInPoints());// 字体大小
                headfont.setBoldweight(tempDto.getBoldweight());
                headfont.setColor(tempDto.getFontColor());
                HSSFCellStyle headstyle = workbook.createCellStyle();//加粗
                headstyle.setFillForegroundColor(tempDto.getFillForegroundColor());//背景
                headstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headstyle.setFont(headfont);
                headstyle.setAlignment(tempDto.getAlignment());// 左右居中
                headstyle.setVerticalAlignment(tempDto.getVerticalAlignment());// 上下居中

                // 表头标题
                if (length > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(i, i, 0, length - 1));
                }
                HSSFRow row = sheet.getRow(i);
                row.setHeight(tempDto.getRowHeight());
                HSSFCell cell = row.getCell(0);
                cell.setCellStyle(headstyle);
                cell.setCellValue(tempDto.getHeadName());
            }
        }

    }

    public static Map writeDataTitle(HSSFWorkbook workbook, HSSFSheet sheet, List<FormTitleDto> list) {
        //用来存标题属性(父标题除外)
        Map<String, Integer> titleMap = new HashMap<String, Integer>();
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> col = new HashSet<Integer>();


        //判断是否存在row
        for (int i = 0; i < list.size(); i++) {
            FormTitleDto tempDto = list.get(i);
            String[] temp = tempDto.getXyLocation().split(",");
            int startrow = Integer.parseInt(temp[0]);
            int overrow = Integer.parseInt(temp[1]);
            int overcol = Integer.parseInt(temp[3]);
            set.add(startrow);
            set.add(overrow);
            col.add(overcol);

        }
        Arrays.sort(col.toArray());
        int maxRow = (int) set.toArray()[set.toArray().length - 1];
        int maxcol = (int) col.toArray()[col.toArray().length - 1];
        //初始化标题
        for (Integer row : set) {
            HSSFRow row2 = sheet.createRow(row);
            for (int i = 0; i <= maxcol; i++) {
                FormTitleDto tempDto = new FormTitleDto();
                //开始渲染列
                HSSFFont font = workbook.createFont();
                font.setFontName(tempDto.getFontName());
                font.setFontHeightInPoints(tempDto.getFontHeightInPoints());// 字体大小
                font.setBoldweight(tempDto.getBoldweight());
                font.setColor(tempDto.getFontColor());
                HSSFCellStyle style = workbook.createCellStyle();
                style.setFillForegroundColor(tempDto.getFillForegroundColor());//背景
                style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
                style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
                style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
                style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

                style.setFont(font);
                style.setAlignment(tempDto.getAlignment());// 左右居中
                style.setVerticalAlignment(tempDto.getVerticalAlignment());// 上下居中

                HSSFCell cell3 = row2.createCell(i);
                cell3.setCellValue("");
                cell3.setCellStyle(style);
            }
        }


        for (int i = 0; i < list.size(); i++) {
            FormTitleDto tempDto = list.get(i);
            if (!("".equals(tempDto.getColName())) && tempDto.getColName() != null) {
                titleMap.put(tempDto.getColName(), Integer.parseInt(tempDto.getXyLocation().split(",")[3]));
            }

            //开始渲染列
            HSSFFont font = workbook.createFont();
            font.setFontName(tempDto.getFontName());
            font.setFontHeightInPoints(tempDto.getFontHeightInPoints());// 字体大小
            font.setBoldweight(tempDto.getBoldweight());
            font.setColor(tempDto.getFontColor());
            HSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(tempDto.getFillForegroundColor());//背景
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

            style.setFont(font);
            style.setAlignment(tempDto.getAlignment());// 左右居中
            style.setVerticalAlignment(tempDto.getVerticalAlignment());// 上下居中


            //合并动态列
            String[] temp = tempDto.getXyLocation().split(",");
            int startrow = Integer.parseInt(temp[0]);
            int overrow = Integer.parseInt(temp[1]);
            int startcol = Integer.parseInt(temp[2]);
            int overcol = Integer.parseInt(temp[3]);
            if (startrow != overrow || startcol != overcol) {
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                        startcol, overcol));
            }

            sheet.setColumnWidth(startcol, tempDto.getColumnWidth());
            //设置列名值
            if (sheet.getRow(startrow) == null) {
                HSSFRow row = sheet.createRow(startrow);
                HSSFCell cell3 = row.createCell(startcol);
                cell3.setCellValue(tempDto.getTextName());
                cell3.setCellStyle(style);

            } else {
                HSSFRow row = sheet.getRow(startrow);
                HSSFCell cell3 = row.getCell(startcol);
                cell3.setCellValue(tempDto.getTextName());
                cell3.setCellStyle(style);
            }
        }
        Map map = new HashMap();
        map.put("maxRow", maxRow);
        map.put(TITLE_MAP, titleMap);
        map.put("maxcol", maxcol);
        return map;
    }

    public boolean isContains(String colName, Map titleMap) {
        return titleMap.containsKey(colName);
    }

    public static <E> void writeText(HSSFWorkbook workbook, HSSFSheet sheet, FormTextDto formTextDto, List<E> list, Map map) throws Exception {
        // 普通单元格样式（中文）
        HSSFFont font2 = workbook.createFont();
        font2.setFontName(formTextDto.getFontName());
        font2.setFontHeightInPoints(formTextDto.getFontHeightInPoints());
        font2.setColor(formTextDto.getFontColor());
        font2.setBoldweight(formTextDto.getBoldweight());
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(formTextDto.getFillForegroundColor());//背景
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style2.setFont(font2);
        style2.setAlignment(formTextDto.getAlignment());// 左右居中
        style2.setWrapText(formTextDto.getWrapText()); // 换行
        style2.setVerticalAlignment(formTextDto.getVerticalAlignment());// 上下居中

        //开始赋值
        int maxRow = (int) map.get("maxRow");
        Map titleMap = (Map) map.get(TITLE_MAP);
        HSSFRow row;
        HSSFCell cell;
        //真正的赋值开始了,写数据
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + maxRow + 1);
            Class tClass = list.get(0).getClass();
            //获得该类的所有属性
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();//获取属性的修饰
                if (!titleMap.containsKey(field.getName())) {//如果该getter方法不是想要的属性方法,则跳过
                    continue;
                }
                if (modifiers == 26 || modifiers == 25) {
                    continue;
                }
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
//                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), tClass);
//                //获得get方法
//                Method get = pd.getReadMethod();
//                if (pd == null) {
//                    continue;
//                }
                Object getValue = hscs.ae.service.impl.ReflectUitls.getValue(list.get(i),field.getName());
                if (titleMap.get(field.getName()) != null) {
                    int cellNum = (int) titleMap.get(field.getName());

                    cell = row.createCell(cellNum);
                    cell.setCellStyle(style2);
                    if (getValue == null) {
                        cell.setCellValue("");
                    } else {
                        if (getValue instanceof BigDecimal) {
                            //保留两位小数
                            getValue = ((BigDecimal) getValue).setScale(2, RoundingMode.HALF_UP);
                        }
                        //日期类型进行格式化
                        if (getValue instanceof Date) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            getValue = sdf.format(getValue);
                        }
                        if (getValue instanceof Integer) {
                            int value = ((Integer) getValue).intValue();
                            cell.setCellValue(value);
                        } else if (getValue instanceof Double) {
                            double d = ((Double) getValue).doubleValue();
                            cell.setCellValue(d);
                        } else {
                            cell.setCellValue((getValue.toString()));
                        }
                    }
                }
            }
        }

    }

    public static void ioWrite(HSSFWorkbook workbook, HSSFSheet sheet, HttpServletResponse response, String sheetName) {

        try {
            String fileName = new String(sheetName.getBytes("utf-8"), "ISO8859-1");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            response.setContentType("application/x-download;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + fileName + ".xls");
//            response.setHeader("Content-disposition","attachment; filename=" + fileName +".xls");

            OutputStream os = response.getOutputStream();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            byte[] b = new byte[1024];
            while ((bais.read(b)) > 0) {
                os.write(b);
            }
            baos.close();
            bais.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于多sheet导出
     *
     * @param workbook
     * @param response
     * @param excelName
     */
    public static void ioWriteForSheets(HttpServletRequest request, HSSFWorkbook workbook, HttpServletResponse response, String excelName) {

        try {
            String agent = request.getHeader("USER-AGENT");
            boolean flagOne = false;
            if(null != agent && -1 != agent.indexOf(MSIE)){
                flagOne = true;
            }
            boolean flagTwo = false;
            if(null != agent && -1 != agent.indexOf(STRING_TRIDENT)){
                flagTwo = true;
            }
            boolean flagThree = false;
            if(null != agent && -1 != agent.indexOf(STRING_EDGE)){
                flagThree = true;
            }
            if (flagOne || flagTwo ||flagThree ) {// ie

                String fileName = java.net.URLEncoder.encode(excelName, "UTF8");
                response.addHeader("Content-Disposition", "attachment;filename="
                        + fileName + ".xls");
            } else if (null != agent && -1 != agent.indexOf(MOZILLA)) {// 火狐,chrome等

                String fileName = new String(excelName.getBytes("utf-8"), "ISO8859-1");
                response.addHeader("Content-Disposition", "attachment;filename="
                        + fileName + ".xls");
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            response.setContentType("application/x-download;charset=utf-8");
            OutputStream os = response.getOutputStream();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            byte[] b = new byte[1024];
            while ((bais.read(b)) > 0) {
                os.write(b);
            }
            baos.close();
            bais.close();
            os.flush();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
