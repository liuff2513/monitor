package com.baihui.core.util.file.image;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author yong.lou
 * @date 2017年5月10日
 *  图片压缩
 */
public class ThumbnailsUtil {

	/**
	 *  等比例压缩图片
	 * @param sourceFile 源文件
	 * @param destFile  目标文件
	 * @param forScale 压缩比例
	 * @param imageType 文件类型
	 * @author yong.lou
	 * @date 2017年5月10日
	 */
	public  static void  compressImageByScale(String sourceFile,String destFile,double forScale,String imageType){

		try {
			BufferedImage sourceImage = ImageIO.read(new File(sourceFile));
			BufferedImage compressedImage = Thumbnails.of(sourceImage)
					.scale(forScale)
					.asBufferedImage();
			ImageIO.write(compressedImage, imageType, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 	  固定宽高压缩图片
	 * @param sourceFile
	 * @param destFile
	 * @param width
	 * @param height
	 * @param imageType
	 * @author aaa
	 * @date 2017年5月10日
	 */
	public static void compressImageByFixedWidthHeight(String sourceFile,String destFile,int width,int height,String imageType){

		try {
			BufferedImage sourceImage = ImageIO.read(new File(sourceFile));
			BufferedImage compressedImage = Thumbnails.of(sourceImage)
					.size(width, height)
					.asBufferedImage();
			ImageIO.write(compressedImage, imageType, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 * 	固定宽高压缩图片 
	 * 		宽不超过 1000
	 * 		高不超过 750
	 * @param sourceFile
	 * @param destFile
	 * @param imageType
	 * @author aaa
	 * @date 2017年5月11日
	 */
	public static void compressImageByFixedWidthHeight(String sourceFile,String destFile,String imageType){
		try {
			BufferedImage sourceImage = ImageIO.read(new File(sourceFile));
			int width = sourceImage.getWidth();
			int height = sourceImage.getHeight();

			int newWidth = width;
			int newHeight = height;


			if(width>height){
				if(width>1000){
					newWidth = 1000;
					newHeight = newWidth/(width/height);
				}
			}else{
				if(height>750){
					newHeight = 750;
					newWidth = newHeight/(height/width);
				}
			}


			BufferedImage compressedImage = Thumbnails.of(sourceImage)
					.size(newWidth, newHeight)
					.asBufferedImage();
			ImageIO.write(compressedImage, imageType, new File(destFile));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {

//		ThumbnailsUtil.compressImageByScale("F:\\高清图\\2_app.jpg", "F:\\高清图\\2_app.jpg", 0.25f, "jpg");
		ThumbnailsUtil.compressImageByFixedWidthHeight("F:\\高清图\\3.jpg","F:\\高清图\\压缩后\\3_app.jpg","jpg");
	}

}
