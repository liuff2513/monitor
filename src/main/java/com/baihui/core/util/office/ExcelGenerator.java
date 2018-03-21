package com.baihui.core.util.office;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * ClassName: ExcelWriter
 * @Description: Excel数据写入工具类，POI实现，兼容Excel2003和Excel2007
 * @author feifei.liu
 * @date 2016年3月23日 下午8:31:58
 */
public class ExcelGenerator {

	private final int maximum = 3000; // Excel每个工作簿的行数
	private List<String> titles = null; // excel标题数据集
	private List<List<String>> data = null; // excel数据内容
	private HSSFWorkbook workbook = null;

	/**
	 * @param titles
	 * @param data
	 */
	public ExcelGenerator(List<String> titles, List<List<String>> data) {
		this.titles = titles;
		this.data = data;
	}
	
	public ExcelGenerator() {
		
	}

	/**
	 * @Description: 创建HSSFWorkbook对象
	 * @return   
	 * @return HSSFWorkbook  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月23日 下午8:46:35
	 */
	public HSSFWorkbook createWorkbook() {
		//实例化：工作簿对象
		workbook = new HSSFWorkbook();
		int rows = this.data.size();//总的记录数
		//创建（表单）：创建表单i
		HSSFSheet sheet = workbook.createSheet("表单 1");//使用workbook对象创建sheet对象
		//创建（行）：标题行
		HSSFRow headRow = sheet.createRow(0); 
		//遍历：标题
		for (int i = 0; i < titles.size(); i++) {//循环excel的标题
			HSSFCell cell = headRow.createCell( i);//使用行对象创建列对象，0表示第1列
			/**************对标题添加样式begin********************/
//			//设置列的宽度/
//			sheet.setColumnWidth(i, 6000);
//			HSSFCellStyle cellStyle = workbook.createCellStyle();//创建列的样式对象
//			HSSFFont font = workbook.createFont();//创建字体对象
//			//字体加粗
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//			//字体颜色变红
//			font.setColor(HSSFColor.RED.index);
//			//如果font中存在设置后的字体，并放置到cellStyle对象中，此时该单元格中就具有了样式字体
//			cellStyle.setFont(font);
//			/**************对标题添加样式end********************/
			
			//添加样式
//			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			cell.setCellStyle(cellStyle);
			cell.setCellValue(titles.get(i)==null?"-":titles.get(i));//为标题中的单元格设置值
		}
		//遍历：写入excel数据
		for(int j=0; j < rows; j++) {
			HSSFRow row = sheet.createRow(j+1);
			List<String> rowData = data.get(j);
			for(int k =0; k<rowData.size(); k++) {
				HSSFCell cell = row.createCell(k);
				cell.setCellValue(rowData.get(k)==null?"":rowData.get(k));
			}
		}
		return workbook;
	}

	/**
	 * @Description: TODO
	 * @param os
	 * @throws Exception   
	 * @return void  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月23日 下午9:55:25
	 */
	public void workbookWrite(OutputStream os) throws Exception {
		workbook = createWorkbook();
		workbook.write(os);//将excel中的数据写到输出流中，用于文件的输出
	}  
}
