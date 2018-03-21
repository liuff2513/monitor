package com.baihui.core.util.office;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class CSVGenerator {
	
	/**
	 * @Description: 生成CSV文件
	 * @param data
	 * @param titles
	 * @param filePath
	 * @param fileName
	 * @return   
	 * @return File  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月24日 上午11:56:06
	 */
	public static File createCSVFile2(List<String> titles, List<List<String>> data) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
//			File file = new File(filePath);
//			if (!file.exists()) {
//				file.mkdirs();
//			}
			// 定义文件名格式并创建
			
			csvFile = File.createTempFile(System.currentTimeMillis()+"", ".csv");
//			csvFile = File.createTempFile(fileName, ".csv", new File(filePath));
			System.out.println("csvFile：" + csvFile);
			// UTF-8使正确读取分隔符","
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
			System.out.println("csvFileOutputStream：" + csvFileOutputStream);
			// 写入文件头部
			for(Iterator<String> iterator = titles.iterator(); iterator.hasNext();) {
				String title = iterator.next();
				csvFileOutputStream.write( title==null?"": title);
				if(iterator.hasNext())
					csvFileOutputStream.write(",");
			}
			csvFileOutputStream.newLine();
			// 写入文件内容
			for(Iterator<List<String>> rowIterator = data.iterator(); rowIterator.hasNext();) {
				List<String> row = rowIterator.next();
				for(Iterator<String> colIterator = row.iterator(); colIterator.hasNext();) {
					String colData=colIterator.next();
					if(colData.indexOf(",")!=-1) colData = "\""+colData+"\"";//单元格内容带逗号处理
					csvFileOutputStream.write(colData==null?"":colData+"\t");//\t处理科学计数法
					if(colIterator.hasNext())
						csvFileOutputStream.write(",");
				}
				if(rowIterator.hasNext())
					csvFileOutputStream.newLine();
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvFile;
	}
	
	/**
	 * @Description: 生成CSV文件
	 * @param data
	 * @param titles
	 * @param filePath
	 * @param fileName
	 * @return   
	 * @return File  
	 * @throws
	 * @author feifei.liu
	 * @date 2016年3月24日 上午11:56:06
	 */
	public static File createCSVFile(List<String> titles, List<List<String>> data, String filePath, String fileName) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 定义文件名格式并创建
			csvFile = File.createTempFile(fileName, ".csv", new File(filePath));
			System.out.println("csvFile：" + csvFile);
			// UTF-8使正确读取分隔符","
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
			System.out.println("csvFileOutputStream：" + csvFileOutputStream);
			// 写入文件头部
			for(Iterator<String> iterator = titles.iterator(); iterator.hasNext();) {
				String title = iterator.next();
				csvFileOutputStream.write( title==null?"": title);
				if(iterator.hasNext())
					csvFileOutputStream.write(",");
			}
			csvFileOutputStream.newLine();
			// 写入文件内容
			for(Iterator<List<String>> rowIterator = data.iterator(); rowIterator.hasNext();) {
				List<String> row = rowIterator.next();
				for(Iterator<String> colIterator = row.iterator(); colIterator.hasNext();) {
					String colData=colIterator.next();
					csvFileOutputStream.write(colData==null?"":colData);
					if(colIterator.hasNext())
						csvFileOutputStream.write(",");
				}
				if(rowIterator.hasNext())
					csvFileOutputStream.newLine();
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvFile;
	}

	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param csvFilePath
	 *            文件路径
	 * @param fileName
	 *            文件名称
	 * @throws IOException
	 */
	public static void exportFile(HttpServletResponse response,
			String csvFilePath, String fileName) throws IOException {
		response.setContentType("application/csv;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(fileName, "UTF-8"));

		InputStream in = null;
		try {
			in = new FileInputStream(csvFilePath);
			int len = 0;
			byte[] buffer = new byte[1024];
			response.setCharacterEncoding("UTF-8");
			OutputStream out = response.getOutputStream();
			while ((len = in.read(buffer)) > 0) {
				out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
				out.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 删除该目录filePath下的所有文件
	 * 
	 * @param filePath
	 *            文件目录路径
	 */
	public static void deleteFiles(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					files[i].delete();
				}
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param filePath
	 *            文件目录路径
	 * @param fileName
	 *            文件名称
	 */
	public static void deleteFile(String filePath, String fileName) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					if (files[i].getName().equals(fileName)) {
						files[i].delete();
						return;
					}
				}
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void generatorMethod1() throws IOException{
		List data = new ArrayList<>();
		List row1 = new ArrayList();
		List row2 = new ArrayList();
		row1.add("11");
		row1.add("12");
		row1.add("13");
		row1.add("14");
		row2.add("21");
		row2.add("22");
		row2.add("23");
		row2.add("24");
		data.add(row1);
		data.add(row2);
		List<String> titles = new ArrayList<>();
		titles.add("第一列");
		titles.add("第二列");
		titles.add("第三列");
		titles.add("第四列");

		File file = CSVGenerator.createCSVFile2(titles, data);
		System.out.println(file.getCanonicalPath());
		System.out.println(file.delete());
//		String fileName2 = file.getName();
//		System.out.println("文件名称：" + fileName2);
//		System.out.println("FilePath::"+file.getCanonicalPath());
	}
	
	/**
	 * 测试数据
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		generatorMethod1();
	}
}
