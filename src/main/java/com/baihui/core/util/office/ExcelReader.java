package com.baihui.core.util.office;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/** 
 * ClassName: ExcelReader
 * @Description: Excel数据读取工具类，POI实现，兼容Excel2003和Excel2007
 * @author feifei.liu
 * @date 2015年12月25日 下午2:29:43
 */
public class ExcelReader {
	private Workbook wb;
	private List<String[]> dataList;
	private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * <p>Description: 有参构造器</p>
	 * @param path Excel文件路径
	 */
	public ExcelReader(String path){
		try {
			InputStream is = new FileInputStream(path);
			wb=WorkbookFactory.create(is);
			//dataList=new ArrayList<String[]>();
			dataList=this.getAllData(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Description: 有参构造器</p>
	 * @param file Excel文件
	 */
	public ExcelReader(File file){
		try {
			InputStream is = new FileInputStream(file);
			wb=WorkbookFactory.create(is);
			//dataList=new ArrayList<String[]>();
			dataList=this.getAllData(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Description: 有参构造器</p>
	 * @param is 流对象
	 */
	public ExcelReader(InputStream is){
		try {
			wb=WorkbookFactory.create(is);
			//dataList=new ArrayList<String[]>();
			dataList=this.getAllData(0);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Description: 有参构造器</p>
	 * @param path Excel文件路径
	 * @param maxReadRows 支持最大行的读取数
	 */
	public ExcelReader(String path, int maxReadRows){
		try {
			InputStream is = new FileInputStream(path);
			wb=WorkbookFactory.create(is);
			//dataList=new ArrayList<String[]>(maxReadRows);
			dataList=this.getAllData(0, maxReadRows);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <p>Description: 有参构造器</p>
	 * @param is 流文件
	 * @param maxReadRows 支持最大行的读取数
	 */
	public ExcelReader(InputStream is, int maxReadRows){
		try {
			wb=WorkbookFactory.create(is);
			//dataList=new ArrayList<String[]>(maxReadRows);
			dataList=this.getAllData(0, maxReadRows);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * @Description: 获取excel表中数据数组集合
	 * @param sheetIndex 表单下标
	 * @return   
	 * @return List<String[]>  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年12月25日 下午2:32:58
	 */
	public List<String[]> getAllData(int sheetIndex){
		dataList=new ArrayList<String[]>();
		int columnNum=0;
		Sheet sheet=wb.getSheetAt(sheetIndex);
		if(sheet.getRow(0)!=null){
			columnNum=sheet.getRow(0).getLastCellNum()-sheet.getRow(0).getFirstCellNum();
		}
		if(columnNum>0){
			for(Row row:sheet){
				String[] singleRow=new String[columnNum];
				String singleRowString = "";
				int n=0;
				for(int i=0;i<columnNum;i++){
					Cell cell=row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n]=Boolean.toString(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if(DateUtil.isCellDateFormatted(cell)){
							singleRow[n]=String.valueOf(dateFormat.format(cell.getDateCellValue()));
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp=cell.getStringCellValue();
							//判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串  
							if(temp.indexOf(".")>-1)
								singleRow[n]=String.valueOf(new Double(temp)).trim();
							else
								singleRow[n]=temp.trim();
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n]=cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n]="";
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						singleRow[n]=cell.getStringCellValue();
						if(singleRow[n]!=null)
							singleRow[n]=singleRow[n].replaceAll("#N/A", "").trim();
						break;
					default:
						singleRow[n] = ""; 
						break;
					}
					singleRowString+=singleRow[n];
					n++;
				}
				if("".equals(singleRowString))//行数据为空，跳过
					continue;
				dataList.add(singleRow);
			}
		}
		return dataList;
	}
	
	/**
	 * @Description: 获取excel表中数据数组集合
	 * @param sheetIndex 表单下标
	 * @return   
	 * @return List<String[]>  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年12月25日 下午2:32:58
	 */
	public List<String[]> getAllData(int sheetIndex,int maxReadRows){
		dataList=new ArrayList<String[]>();
		//获取表单对象
		Sheet sheet=wb.getSheetAt(sheetIndex);
		//获取表格总行数（实际要加1，此处当表格为空是也返回1）
		int rowNum=sheet.getLastRowNum()+1;
		//计算要遍历的行数
		int finalRowNum=rowNum>maxReadRows?maxReadRows:rowNum;
		//声明表格列数量为0
		int columnNum=0;
		//获取要遍历的列数
		if(sheet.getRow(0)!=null){
			columnNum=sheet.getRow(0).getLastCellNum()-sheet.getRow(0).getFirstCellNum();
		}
		if(columnNum>0){
			for(int rowIndex=0; rowIndex<finalRowNum; rowIndex++){
				Row row=sheet.getRow(rowIndex);
				String[] singleRow=new String[columnNum];
				String singleRowString = "";
				int n=0;
				for(int i=0;i<columnNum;i++){
					Cell cell=row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n]=Boolean.toString(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if(DateUtil.isCellDateFormatted(cell)){
							singleRow[n]=String.valueOf(dateFormat.format(cell.getDateCellValue()));
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp=cell.getStringCellValue();
							//判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串  
							if(temp.indexOf(".")>-1)
								singleRow[n]=String.valueOf(new Double(temp)).trim();
							else
								singleRow[n]=temp.trim();
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n]=cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n]="";
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						singleRow[n]=cell.getStringCellValue();
						if(singleRow[n]!=null)
							singleRow[n]=singleRow[n].replaceAll("#N/A", "").trim();
						break;
					default:
						singleRow[n] = ""; 
						break;
					}
					singleRowString+=singleRow[n];
					n++;
				}
				if("".equals(singleRowString))//行数据为空，跳过
					continue;
				dataList.add(singleRow);
			}
		}
		return dataList;
	}
	
	/**
	 * @Description: 返回Excel最大行index值，实际行数要加1
	 * @param sheetIndex
	 * @return   
	 * @return int  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年12月25日 下午2:33:23
	 */
	public int getRowNum(int sheetIndex){
		Sheet sheet=wb.getSheetAt(sheetIndex);
		return sheet.getLastRowNum();
	}
	
	/**
	 * @Description: 返回数据的列数
	 * @param sheetIndex
	 * @return   
	 * @return int  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年12月25日 下午2:33:40
	 */
	public int getColumnNum(int sheetIndex){
		Sheet sheet=wb.getSheetAt(sheetIndex);
		Row row=sheet.getRow(0);
		if(row!=null&&row.getLastCellNum()>0)
			return row.getLastCellNum();
		return 0;
	}
	    
	/**
	 * @Description: 获取某一行数据 
	 * @param sheetIndex 计数从0开始，rowIndex为0代表header行 
	 * @param rowIndex
	 * @return   
	 * @return String[]  
	 * @throws
	 * @author feifei.liu
	 * @date 2015年12月25日 下午2:47:14
	 */
	public String[] getRowData(int sheetIndex,int rowIndex){  
		String[] dataArray = null;
	    if(rowIndex>this.getColumnNum(sheetIndex)){  
	    	return dataArray;  
	     }else{  
	    	 return this.dataList.get(rowIndex);  
	     }  
	}  
	    
	/** 
	 * 获取某一列数据 
	 * @param colIndex 
	 * @return 
	 */  
	public String[] getColumnData(int sheetIndex,int colIndex){  
		String[] dataArray = null;  
	    if(colIndex>this.getColumnNum(sheetIndex)){  
	    	return dataArray;  
	    }else{     
	    	if(this.dataList!=null&&this.dataList.size()>0){  
	    		dataArray = new String[this.getRowNum(sheetIndex)+1];  
	    		int index = 0;  
	    		for(String[] rowData:dataList){  
	    			if(rowData!=null){  
	    				dataArray[index] = rowData[colIndex];  
	    				index++;  
	    			}  
	    		}  
	    	}  
	    }  
	    return dataArray;  
	}
	public static void main(String[] args) {
		ExcelReader reader=new ExcelReader("E:\\pcstars\\公司杂项\\7月考勤报表.xls");
		String[] columnData=reader.getColumnData(0, 0);
		for(String col:columnData){
			System.out.println(col);
		}
		/*List<String[]> list=reader.getAllData(0);
		for(String[] arry:list){
			System.out.println(arry[3]);
		}*/
	}
}
