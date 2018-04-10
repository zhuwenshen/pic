package com.zhuwenshen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.service.ImgService;

@Controller
public class UploadPicController {
	
	@Autowired
	ImgService imgService;

	//跳转到上传文件的页面
    @RequestMapping(value="/uploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    //处理文件上传
    @RequestMapping(value="/uploadimg", method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult uploadImg(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {       
        
        JsonResult json = null;
        try {
        	JsonResult.ok(imgService.save(file));
           
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("上传失败");
        }
        
        return json;
    }
    
    //处理文件上传
    @RequestMapping(value="/uploadimgList", method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult uploadImg(MultipartRequest request) {       
        
    	List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
    	List<String> listAddress= new ArrayList<String>(); 
    	List<String> errorList= new ArrayList<String>(); 
    	MultipartFile file = null;
    	String name = null ;
    	String url = null;
    	
    	for (int i =0; i< files.size(); ++i) {  
            file = files.get(i);  
            name =  file.getOriginalFilename();  
            if (!file.isEmpty()) {  
                try {  
                	url = imgService.save(file);
                	listAddress.add(url);
                } catch (Exception e) {  
                	errorList.add("第" + i +" 张图片上传失败，文件名为 " +name);
                }  
            } else {  
            	errorList.add("第" + i +" 张图片上传失败，文件错误");
            }  
        }  
    	Map<String , Object> data = new HashMap<String , Object>();
    	data.put("urlList", listAddress);
    	data.put("errorLsit", errorList);
        return JsonResult.ok("成功上传 " + listAddress.size()+" 张图片 ，失败  "+ errorList.size() + " 张" , data);          
    }
    
}
