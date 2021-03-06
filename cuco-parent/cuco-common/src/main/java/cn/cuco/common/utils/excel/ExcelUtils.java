package cn.cuco.common.utils.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.cuco.constant.Constant;
import cn.cuco.wechat.util.RelyDate;

/**
 * description：excel导出工具类
 * author：WangShuai
 * time：2017年02月20 14:54:00
 * 修改时间：2017年02月20 14:54:00
 * 修改备注：
 */
public class ExcelUtils {

    //显示的导出表的标题
    private String title;
    //导出表的列名
    private String[] rowName ;

    private List<Object[]> dataList = new ArrayList<Object[]>();

    HttpServletResponse response;

    protected Logger logger = Logger.getLogger(this.getClass());

    //构造方法，传入要导出的数据
    public ExcelUtils(String title, String[] rowName, List<Object[]>  dataList){
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }

    //构造方法
    public ExcelUtils(){
    }


    /*
     *  多工作表导出数据
     */
    public void export(List<Map<String, Object>> list) throws Exception{
        try{
            logger.info("开始创建工作簿");
            HSSFWorkbook workbook = new HSSFWorkbook();						// 创建工作簿对象
            for (Map<String, Object> map : list) {
                logger.info("开始创建title为-----"+map.get("title").toString()+"-----工作簿");
                HSSFSheet sheet = workbook.createSheet(map.get("title").toString());		 			// 创建工作表

                // 产生表格标题行
                HSSFRow rowm = sheet.createRow(0);
                HSSFCell cellTiltle = rowm.createCell(0);

                //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
                HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
                HSSFCellStyle style = this.getStyle(workbook);					//单元格样式对象
                String[] rowName =(String[]) map.get("rowName");
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));
                cellTiltle.setCellStyle(columnTopStyle);
                cellTiltle.setCellValue(map.get("title").toString());

                // 定义所需列数
                int columnNum = rowName.length;
                HSSFRow rowRowName = sheet.createRow(2);				// 在索引2的位置创建行(最顶端的行开始的第二行)

                // 将列头设置到sheet的单元格中
                logger.info("开始----插入列头");
                for(int n=0;n<columnNum;n++){
                    HSSFCell  cellRowName = rowRowName.createCell(n);				//创建列头对应个数的单元格
                    cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);				//设置列头单元格的数据类型
                    HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                    cellRowName.setCellValue(text);									//设置列头单元格的值
                    cellRowName.setCellStyle(columnTopStyle);						//设置列头单元格样式
                }
                logger.info("结束----插入列头");
                logger.info("开始----插入数据");
                List<Object[]>  dataList =(List<Object[]>) map.get("dataList");
                //将查询出的数据设置到sheet对应的单元格中
                for(int i=0;i<dataList.size();i++){

                    Object[] obj = dataList.get(i);//遍历每个对象
                    HSSFRow row = sheet.createRow(i+3);//创建所需的行数

                    for(int j=0; j<obj.length; j++){
                        HSSFCell  cell = null;   //设置单元格的数据类型
                        if(j == 0){
                            cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(i+1);
                        }else{
                            cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                            if(!"".equals(obj[j]) && obj[j] != null){
                                cell.setCellValue(obj[j].toString());						//设置单元格的值
                            }
                        }
                        cell.setCellStyle(style);									//设置单元格样式
                    }
                }
                logger.info("结束----插入数据");
                //让列宽随着导出的列长自动适应
                for (int colNum = 0; colNum < columnNum; colNum++) {
                    int columnWidth = sheet.getColumnWidth(colNum) / 256;
                    for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                        HSSFRow currentRow;
                        //当前行未被使用过
                        if (sheet.getRow(rowNum) == null) {
                            currentRow = sheet.createRow(rowNum);
                        } else {
                            currentRow = sheet.getRow(rowNum);
                        }
                        if (currentRow.getCell(colNum) != null) {
                            HSSFCell currentCell = currentRow.getCell(colNum);
                            if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                int length = currentCell.getStringCellValue().getBytes().length;
                                if (columnWidth < length) {
                                    columnWidth = length;
                                }
                            }
                        }
                    }
                    if(colNum == 0){
                        sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
                    }else{
                        sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                    }
                }
                logger.info("结束创建title为-----"+map.get("title").toString()+"-----工作簿");
            }
            if(workbook !=null){
                OutputStream out = new FileOutputStream("d://a.xls");
                workbook.write(out);
                out.close();
            }
            logger.info("创建工作簿结束");
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 多工作表导出数据
     * @param list
     * @param cols
     * @param rows
     * @param mergeColIndexes 需要合并列的下标（从0开始）
     * @param path 保存路径,如 /hycx/excels/20170220
     * @param fileName 文件名，如 20170220123.xls
     * @return
     * @throws Exception
     */
    public void export(List<Map<String, Object>> list,List<Integer> cols,Integer rows,List<Integer> mergeColIndexes,String path,String fileName) throws Exception{

        String fileUrl = "";
        try{
            logger.info("开始创建工作簿");
            HSSFWorkbook workbook = new HSSFWorkbook();						// 创建工作簿对象
            for (Map<String, Object> map : list) {
                logger.info("开始创建title为-----"+map.get("title").toString()+"-----工作簿");
                HSSFSheet sheet = workbook.createSheet(map.get("title").toString());		 			// 创建工作表

                // 产生表格标题行
                HSSFRow rowm = sheet.createRow(0);
                HSSFCell cellTiltle = rowm.createCell(0);

                //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
                HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
                HSSFCellStyle style = this.getStyle(workbook);					//单元格样式对象
                String[] rowName =(String[]) map.get("rowName");
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));
                Integer total = 3;
                for(int i=0;i<rows;i++){
                    Integer c = cols.get(i);
                    if(c<1){
                        continue;
                    }
                    int rowStart = total;
                    int rowEnd = total+c-1;

                    for(Integer colMegerIndex : mergeColIndexes){
                        sheet.addMergedRegion(new CellRangeAddress(rowStart,rowEnd,colMegerIndex,colMegerIndex));
                    }
                    total+=c;
                }
                cellTiltle.setCellStyle(columnTopStyle);
                cellTiltle.setCellValue(map.get("title").toString());

                // 定义所需列数
                int columnNum = rowName.length;
                HSSFRow rowRowName = sheet.createRow(2);				// 在索引2的位置创建行(最顶端的行开始的第二行)

                // 将列头设置到sheet的单元格中
                logger.info("开始----插入列头");
                for(int n=0;n<columnNum;n++){
                    HSSFCell  cellRowName = rowRowName.createCell(n);				//创建列头对应个数的单元格
                    cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);				//设置列头单元格的数据类型
                    HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                    cellRowName.setCellValue(text);									//设置列头单元格的值
                    cellRowName.setCellStyle(columnTopStyle);						//设置列头单元格样式
                }
                logger.info("结束----插入列头");
                logger.info("开始----插入数据");
                List<Object[]>  dataList =(List<Object[]>) map.get("dataList");
                //将查询出的数据设置到sheet对应的单元格中
                for(int i=0;i<dataList.size();i++){

                    Object[] obj = dataList.get(i);//遍历每个对象
                    HSSFRow row = sheet.createRow(i+3);//创建所需的行数

                    for(int j=0; j<obj.length; j++){
                        HSSFCell  cell = null;   //设置单元格的数据类型
                        if(j == 0){
                            cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(i+1);
                        }else{
                            cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                            if(!"".equals(obj[j]) && obj[j] != null){
                                cell.setCellValue(obj[j].toString());						//设置单元格的值
                            }
                        }
                        cell.setCellStyle(style);									//设置单元格样式
                    }
                }
                logger.info("结束----插入数据");
                //让列宽随着导出的列长自动适应
                for (int colNum = 0; colNum < columnNum; colNum++) {
                    int columnWidth = sheet.getColumnWidth(colNum) / 256;
                    for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                        HSSFRow currentRow;
                        //当前行未被使用过
                        if (sheet.getRow(rowNum) == null) {
                            currentRow = sheet.createRow(rowNum);
                        } else {
                            currentRow = sheet.getRow(rowNum);
                        }
                        if (currentRow.getCell(colNum) != null) {
                            HSSFCell currentCell = currentRow.getCell(colNum);
                            if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                int length = currentCell.getStringCellValue().getBytes().length;
                                if (columnWidth < length) {
                                    columnWidth = length;
                                }
                            }
                        }
                    }
                    if(colNum == 0){
                        sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
                    }else{
                        sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                    }
                }
                logger.info("结束创建title为-----"+map.get("title").toString()+"-----工作簿");
            }
            if(workbook !=null){
                Date now = new Date();
                //TODO String fileName = generateFileName("xls");
                //TODO String path = getdownLoadServerPath();
                fileUrl = getAccessResourcePath(fileName);
                File file = new File(path);
                if(!file.exists()){
                    file.mkdirs();
                }
                OutputStream out = new FileOutputStream(path+"/"+fileName);

                workbook.write(out);
                out.close();
            }
            logger.info("创建工作簿结束");
        }catch(Exception e){
            e.printStackTrace();
        }
        //TODO return fileUrl;
    }

    /*
     * 单工作簿导出数据
     * */
    public String export() throws Exception{
        try{
            logger.info("开始创建工作簿");
            HSSFWorkbook workbook = new HSSFWorkbook();						// 创建工作簿对象
            logger.info("开始创建title为-----"+title+"-----工作簿");
            HSSFSheet sheet = workbook.createSheet(title);		 			// 创建工作表

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
            HSSFCellStyle style = this.getStyle(workbook);					//单元格样式对象

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(2);				// 在索引2的位置创建行(最顶端的行开始的第二行)

            // 将列头设置到sheet的单元格中
            for(int n=0;n<columnNum;n++){
                HSSFCell  cellRowName = rowRowName.createCell(n);				//创建列头对应个数的单元格
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);				//设置列头单元格的数据类型
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);									//设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle);						//设置列头单元格样式
            }

            //将查询出的数据设置到sheet对应的单元格中
            for(int i=0;i<dataList.size();i++){

                Object[] obj = dataList.get(i);//遍历每个对象
                HSSFRow row = sheet.createRow(i+3);//创建所需的行数

                for(int j=0; j<obj.length; j++){
                    HSSFCell  cell = null;   //设置单元格的数据类型
                    if(j == 0){
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i+1);
                    }else{
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                        if(!"".equals(obj[j]) && obj[j] != null){
                            cell.setCellValue(obj[j].toString());						//设置单元格的值
                        }
                    }
                    cell.setCellStyle(style);									//设置单元格样式
                }
            }
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        try{
                            HSSFCell currentCell = currentRow.getCell(colNum);
                            if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                int length = currentCell.getStringCellValue().getBytes().length;
                                if (columnWidth < length) {
                                    columnWidth = length;
                                }
                            }
                        }catch(Exception e){
                            logger.info("######colNum:"+colNum);
                        }

                    }
                }
                if(colNum == 0){
                    sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
                }else{
                    if(((columnWidth+4) * 256)>65280){
                        sheet.setColumnWidth(colNum, 32640);
                    }else{
                        sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                    }
                }
            }
            logger.info("结束创建title为-----"+title+"-----工作簿");
            if(workbook !=null){
                //文件夹
                File file = new File(Constant.APPLICATION_LOAD.getProperty(Constant.EXPORT_SELL_PATH));
//				 File file = new File("d://a.xls");
//				 deleteDir(file);
                if(!file.exists() && !file.isDirectory()){
                    file.mkdirs();
                }
                //文件存储路径
                String fileName = generateFileName("xls");
                String path = Constant.APPLICATION_LOAD.getProperty(Constant.EXPORT_SELL_PATH)+"/"+fileName ;
                OutputStream out = new FileOutputStream(path);
                workbook.write(out);
                out.close();
                logger.info("导出Exce路径为---------"+path);
//			        String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
//			        String headStr = "attachment; filename=\"" + fileName + "\"";
//			        response = getResponse();
//			        response.setContentType("APPLICATION/OCTET-STREAM");
//			        response.setHeader("Content-Disposition", headStr);
//			        OutputStream out = response.getOutputStream();
//			        workbook.write(out);
                return Constant.APPLICATION_LOAD.getProperty(Constant.EXPORT_SELL_ROOT_PATH)+fileName;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
   * 列数据信息单元格样式
   */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(true);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /**
     * 根据[年月日+随机数]生成文件名
     * 如：20160817123456.jpg
     * @return
     */
    private String generateFileName(String fileSuffix){
        String fileName = RelyDate.getDate("yyyyMMdd") + RandomStringUtils.randomNumeric(6);
        if(StringUtils.isNotEmpty(fileSuffix)){
            fileName = fileName + "." + fileSuffix;
        }
        return fileName;
    }
    /**
     * 导出车辆列表下载到服务器的配置路径上
     * @return
     */
    private String getdownLoadServerPath(){
        /*String path = Constant.APPLICATION_LOAD.getProperty(Constant.EXPORT_SAVE_PATH)
                +Constant.APPLICATION_LOAD.getProperty(com.hy.gps.common.Constant.CAR_REPORT_EXPORT_PATH)
                +"/"+DateUtils.getDate("yyyyMMdd");
        return path;*/
        return null;
    }
    /**
     * 保存服务器完成后返回前端可以访问资源的url
     * @param fileName
     * @return
     */
    private String getAccessResourcePath(String fileName){
        /*String fileUrl = Constant.APPLICATION_LOAD.getProperty(com.hy.gps.common.Constant.ACCESS_EXPORT_PATH)
                +Constant.APPLICATION_LOAD.getProperty(com.hy.gps.common.Constant.CAR_REPORT_EXPORT_PATH)
                +"/"+DateUtils.getDate("yyyyMMdd")
                +"/"+fileName;
        return fileUrl;*/
        return "";
    }
//	public static void main(String[] args) {
//        String title = "信息";
//		String[] rowsName = new String[]{"序号","货物运输批次号","提运单号","状态","录入人","录入时间"};
//		List<Object[]>  dataList = new ArrayList<Object[]>();
//		Object[] objs = null;
//		for (int i = 0; i < 10; i++) {
//			objs = new Object[rowsName.length];
//			objs[0] = i;
//			objs[1] = i*111;
//			objs[2] = i*222;
//			objs[3] = i*333;
//			objs[4] = i*444;
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String date = df.format(new Date());
//			objs[5] = date;
//			dataList.add(objs);
//		}
//		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
//		try {
//			ex.export();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

    public static void main(String[] args) {
//        String title = "信息";
//		String[] rowsName = new String[]{"序号","货物运输批次号","提运单号","状态","录入人","录入时间"};
//		List<Object[]>  dataList = new ArrayList<Object[]>();
//		Object[] objs = null;
//		for (int i = 0; i < 10; i++) {
//			objs = new Object[rowsName.length];
//			objs[0] = i;
//			objs[1] = i*111;
//			objs[2] = i*222;
//			objs[3] = i*333;
//			objs[4] = i*444;
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String date = df.format(new Date());
//			objs[5] = date;
//			dataList.add(objs);
//		}
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		for (int i = 0; i < 3; i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("title", title+i);
//			map.put("rowName", rowsName);
//			map.put("dataList", dataList);
//			list.add(map);
//		}
//
//		ExportExcel ex = new ExportExcel();
//		try {
//			ex.export(list);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        String newDir2 = "D:\\1\\22";
        boolean success = deleteDir(new File(newDir2));
        if (success) {
            System.out.println("Successfully deleted populated directory: " + newDir2);
        } else {
            System.out.println("Failed to delete populated directory: " + newDir2);
        }
    }


}
