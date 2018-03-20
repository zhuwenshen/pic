package com.zhuwenshen.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        System.out.println(filePath+fileName);
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
