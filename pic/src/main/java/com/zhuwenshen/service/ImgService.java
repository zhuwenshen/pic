package com.zhuwenshen.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhuwenshen.utils.FileUtil;
import com.zhuwenshen.utils.ImgUtil;
import com.zhuwenshen.utils.MySid;

@Service
public class ImgService {
	final static Logger log = LoggerFactory.getLogger(ImgService.class);
	
	@Value("${uploadimg.prefix}")
	private  String prefix ;
	
	@Value("${uploadimg.tmp}")
	private  String tmp;
	
	@Value("${uploadimg.appPath}")
	private String appPath;	

	
	public String save(MultipartFile file) throws Exception {
		String sid = MySid.nextLong();	
		String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = "";
		try {
			FileUtil.uploadFile(file.getBytes(), prefix+tmp, sid+suffix);
			fileName = prefix+tmp+sid+suffix;
			File temp = new File(fileName);			
			log.info("插入的临时文件路径为"+temp.getAbsolutePath());
			newFileName = getRandomFilePath()+sid+suffix;
			//判断路径下是否存在文件
			while(true) {
				File newF = new File(newFileName);
				if(newF.exists())    
				{    
					newFileName = getRandomFilePath()+sid+suffix;
				}else {
					break;
				}
			}			
			
			ImgUtil.saveImg(new File(fileName), prefix+newFileName);
			log.info("最后的路径为"+newFileName);
			temp.delete();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("上传失败");
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("上传失败");
			throw e;
		}
		log.info("上传成功");
		return appPath+newFileName;		
	}
	
	public  String getRandomFilePath() {
		StringBuffer sb = new StringBuffer();
		sb.append(getRandomInt()).append('/').append(getRandomInt()).append('/');
		File targetFile = new File(prefix+sb.toString());
		System.out.println(targetFile.getAbsolutePath());
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		return sb.toString();
	}

	private static long getRandomInt() {
		return Math.round(Math.random() * 8999 + 1000);
	}

}
