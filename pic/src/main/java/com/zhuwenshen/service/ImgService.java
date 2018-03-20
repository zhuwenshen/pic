package com.zhuwenshen.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.utils.FileUtil;
import com.zhuwenshen.utils.ImgUtil;
import com.zhuwenshen.utils.MySid;

@Service
public class ImgService {
	final static Logger log = LoggerFactory.getLogger(ImgService.class);
	
	private static String PREFIX = "static/img/";
	private static String TMP = "tmp/";

	public JsonResult save(MultipartFile file) {

		String sid = MySid.nextLong();	
		String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = "";
		try {
			FileUtil.uploadFile(file.getBytes(), PREFIX+TMP, sid+suffix);
			fileName = PREFIX+TMP+sid+suffix;
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
			
			ImgUtil.saveImg(new File(fileName), newFileName);
			log.info("最后的路径为"+newFileName);
			temp.delete();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("上传失败");
			return JsonResult.fail("上传失败");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("上传失败");
			return JsonResult.fail("上传失败");
		}
		log.info("上传成功");
		return JsonResult.ok(newFileName);
	}

	public static String getRandomFilePath() {
		StringBuffer sb = new StringBuffer();
		sb.append(PREFIX).append(getRandomInt()).append('/').append(getRandomInt()).append('/');
		File targetFile = new File(sb.toString());
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
