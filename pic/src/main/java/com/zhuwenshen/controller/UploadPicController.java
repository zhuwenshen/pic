package com.zhuwenshen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.service.ImgService;

@Controller
public class UploadPicController {
	
	@Autowired
	ImgService imgService;

	//跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    //处理文件上传
    @RequestMapping(value="/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult uploadImg(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {       
        
        JsonResult json = null;
        try {
        	json = imgService.save(file);
           
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("上传失败");
        }
        
        return json;
    }
}
