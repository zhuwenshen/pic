package com.zhuwenshen.utils;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class ImgUtil {
	
	
	public static void saveImg(File pic, String outFilepath) throws IOException {
		Thumbnails.of(pic).scale(1f).toFile(outFilepath);
	}
	
	
	
	public static Builder<File> toBuilder(File pic) {
		return Thumbnails.of(pic).scale(1f);
	}	

	/**
	 * 变为sizeX*sizeY,遵循原图比例缩或放到sizeY*某个高度
	 * @param builder
	 * @param sizeX
	 * @param sizeY
	 * @return
	 */
	public static Builder<File> compressToSpecifiedSize(Builder<File> builder, int sizeX, int sizeY) {
		// 按指定大小把图片进行缩和放（会遵循原图高宽比例）		
		// 变为sizeX*sizeY,遵循原图比例缩或放到sizeY*某个高度
		return builder.size(sizeX, sizeY);
	}
		
	
	/**
	 * 按照比例进行缩小和放大  
	 * @param builder
	 * @param scale 大于1为放大，小于1为缩小
	 * @return
	 */
	public static Builder<File> scale(Builder<File> builder, double scale) {
		return builder.scale(scale);
	}
	
	
	/**
	 * 不按比例，就按指定的大小进行缩放  sizeX*int sizeY
	 * @param builder
	 * @param sizeX
	 * @param sizeY
	 * @return
	 */
	public static Builder<File> forceSize(Builder<File> builder, int sizeX, int sizeY) {
		return builder.forceSize(sizeX,sizeY);
	}
	
	/**
	 * 图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量 
	 * @param builder
	 * @param quality
	 * @return
	 */
	public static Builder<File> outputQuality(Builder<File> builder, double quality) {
		return builder.scale(1f).outputQuality(quality);
	}
	
	/**
	 * 用sourceRegion()实现图片裁剪  
	 *	图片中心300*300的区域,Positions.CENTER表示中心，还有许多其他位置可选  
	 * @param builder
	 * @param quality
	 * @return
	 */
	public static Builder<File> sourceRegionCenter(Builder<File> builder, int sizeX, int sizeY) {
		return builder.sourceRegion(Positions.CENTER,sizeX,sizeY).size(sizeX,sizeY);
	}
	
	public static Builder<File> outputFormatToJpg(Builder<File> builder) {
		return builder.scale(1f).outputFormat("jpg");
	}
	
	/**
	 * 将图片输出到指定位置
	 * @param builder
	 * @param outFilepath
	 * @throws IOException
	 */
	public static void toFile(Builder<File> builder , String outFilepath) throws IOException {
		builder.toFile(outFilepath);
	}
}