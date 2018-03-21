package com.baihui.core.util.file;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassName:    FileWriteUtil.java
 * @Description:  (文件写操作) 
 * @All rights Reserved, Designed By PCstars
 * @Copyright:  Copyright(C) 2014-2015
 * @Company:	baihui
 * @author      ziyu.zhang
 * @version     V2.0  
 * @Date        2015年8月24日 上午10:48:04 
 */
public class FileWriteUtil {

	/**
	 * 创建文件
	 * @param filename 文件名
	 * @param folderName 文件夹名
	 * @return
	 */
	public static String creatfile(String filename,String folderName){
	     
	     String discname = getFilediscName(folderName);//文件夹路径
	     //判断路径是否存在
	     if (!new File(discname).exists()) {
			 File file = new File(discname); //没有路径创建路径
			 file.mkdirs();
		 }
		 String fnam = discname+filename;
		 //判断文件是否存在
		 boolean isfileexists = FileUtil.checkExist(fnam);//判断文件是否存在
		 if(!isfileexists){
			 //不存在创建文件
	    	 try {
				 FileWriter fw=new FileWriter(new File(fnam));
				 //写入中文字符时会出现乱码
				 BufferedWriter  bw = new BufferedWriter(fw);
				 bw.close();
				 fw.close();
				 return fnam;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		 }else{
			 return fnam;
		 }
		return "";
	}
	
	/**
	 * 更新文件名
	 * @param filepath
	 * @return
	 */
	public static String updatefilename(String filepath){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String  datepath = new SimpleDateFormat("yyyyMMddHHmmssS").format(cal.getTime());
		filepath += "."+datepath;
		return filepath;
	}
	
	/**
	 * 文件写入 追加式
	 * @param content 内容
	 * @param fileurl 存储路径
	 * @param filename 文件名
	 * @param folderName 文件夹名
	 * @return
	 */
	public static boolean writerAddfile(String content,String fileurl,String filename,String folderName){
		String nfileUrl = fileurl;
		if(!FileUtil.checkExist(fileurl)){
		    //文件不存在去创建文件
			nfileUrl = creatfile(filename,folderName);
		}
		BufferedWriter bw = null;
		try {
			
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nfileUrl, true)));     
			bw.write(content+"\n");
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {     
                if(bw != null){  
                	bw.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }    
		}
		return true;
	}
	
	/**
	 * 文件写入 覆盖式
	 * @param content 内容
	 * @param fileurl 存储路径
	 * @param filename 文件名
	 * @param folderName 文件夹名
	 * @return
	 */
	public static boolean writerCoverfile(String content,String fileurl,String filename,String folderName){
		String nfileUrl = fileurl;
		if(!FileUtil.checkExist(fileurl)){
		    //文件不存在去创建文件
			nfileUrl = creatfile(filename,folderName);
		}
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter(new File(nfileUrl));
			bw = new BufferedWriter(fw);
			bw.write(content+"\n");
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {     
                if(bw != null){  
                	bw.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }    
		}
		return true;
	}
	
	
	/**
	 * 获取文件路径
	 * @return
	 */
	public static String getFilediscName(String folderName){
		File filea = new File(".\\");
		String srcPath = "";
		try {
			srcPath = filea.getCanonicalPath();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String separator = File.separator;
		return srcPath.split(":")[0]+":"+separator+folderName+separator;
	}


	/**
	 * 文件下载
	 * Created by ziyu.zhang on 2016/5/17 20:30
	 * @Param filepath 完整路径
	 * 		  fileName 文件名
	 * @return
	 */
	public static void downLoadFile(HttpServletResponse response,String filepath,String fileName){
		FileInputStream fos = null;
		ServletOutputStream sos = null;
		try{
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			response.setContentType("application/octet-stream");
			response.setContentType("application/OCTET-STREAM;charset=UTF-8");
			byte b[] = new byte[1024*1024*1];//1M
			int read = 0;
			fos = new FileInputStream(new File(filepath));
			sos = response.getOutputStream();
			while((read=fos.read(b))!=-1){
				sos.write(b,0,read);//每次写1M
			}
		}catch (Exception e) {
			throw new RuntimeException("");
		}finally{
			try {
				if(sos!=null){
					sos.close();
				}
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("");
			}
		}
	}


	
}
