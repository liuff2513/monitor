package com.baihui.core.util.file.image;


import com.baihui.core.util.str.StringUtil;

/**
 * ClassName: DocumentFileType
 * @Description:
 * 		文档管理  文件类型
 * @author yong.lou
 * @date 2016年12月15日 上午11:42:12
 */
public class DocumentFileType {

	//图片枚举类
	public enum ImageEnum{

		BMP(1,"bmp"),DIB(2,"dib"),GIF(3,"gif"),JFIF(4,"jfif"),
		JPE(5,"jpe"),JPEG(6,"jpeg"),JPG(7,"jpg"),PNG(8,"png"),
		TIF(9,"tif"),TIFF(10,"tiff"),ICO(11,"ico"),PSD(12,"psd"),
		SVG(13,"svg");

		private int indexPosition;
		private String imageName;

		ImageEnum(int indexPosition,String imageName){
			this.indexPosition = indexPosition;
			this.imageName = imageName;
		}

		public int numberOfIndex(){
			return this.indexPosition;
		}

		public String getImageName(){
			return this.imageName;
		}

	}

	//音频枚举类
	public enum AudioEnum{
		MP3(1,"mp3"),OGG(2,"ogg"),WAV(3,"wav"),
		APE(4,"ape"),CDA(5,"cda"),AU(6,"au"),
		MIDI(7,"midi"),MAC(8,"mac"),AAC(9,"aac");

		private int indexPosition;
		private String audioName;

		AudioEnum( int indexPosition,String audioName){
			this.indexPosition = indexPosition;
			this.audioName = audioName;
		}

		public int numberOfIndex(){
			return this.indexPosition;
		}

		public String getAudioName(){
			return this.audioName;
		}

	}

	//视频枚举类
	public enum VedioEnum{

		RMVB(1,"rmvb"),WMV(2,"wmv"),ASF(3,"asf"),
		AVI(4,"avi"),MPG(5,"mpg"),MKV(6,"mkv"),
		MP4(7,"mp4"),DVD(8,"dvd"),OGM(9,"ogm"),
		MOV(10,"mov"),MPEG2(11,"mpeg2"),MPEG4(12,"mp3g4");

		private int indexPosition;
		private String vedioName;

		VedioEnum(int indexPosition,String vedioName){
			this.indexPosition = indexPosition;
			this.vedioName = vedioName;
		}


		public int  numberOfIndex(){
			return this.indexPosition;
		}

		public String getVedioName(){
			return  this.vedioName;
		}

	}

	/**
	 * @Description:
	 * 		1 文档  2 图片  3 音乐  4 视频
	 * @param url
	 * @return
	 * @return Integer
	 * @throws
	 * @author yong.lou
	 * @date 2017年1月17日 下午4:09:23
	 */
	public static Integer getFileType(String url) throws Exception{

		if(StringUtil.empty(url)){
			throw new RuntimeException(" file can not be  null");
		}

		String suffix = url.substring(url.lastIndexOf(".")+1);

		ImageEnum[] images = ImageEnum.values();
		for(ImageEnum image : images){
			if(image.getImageName().equals(suffix.toLowerCase())){
				return 2;
			}
		}

		AudioEnum[] audios = AudioEnum.values();
		for(AudioEnum audio : audios){
			if(audio.getAudioName().equals(suffix.toLowerCase())){
				return 3;
			}
		}

		VedioEnum[] vedios = VedioEnum.values();
		for(VedioEnum vedio : vedios){
			if(vedio.getVedioName().equals(suffix.toLowerCase())){
				return 4;
			}
		}

		return 1;
	}


	public static void main(String[] args) throws Exception {
//		System.out.println(DocumentFileType.getFileType("F:/image/1.jpg"));
	}


}
