package com.zhuwenshen.model.custom;

public class JsonResult {

	private boolean status;
	
	private String msg;
	
	private Object data;
	
	
	public JsonResult() {
		super();
	}

	public JsonResult(boolean b) {
		status = true;
	}	

	public JsonResult(boolean status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}	

	public JsonResult(boolean status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static JsonResult ok() {
		return new JsonResult(true);
	}
	
	public static JsonResult ok(String msg) {
		return new JsonResult(true, msg);
	}
	
	public static JsonResult fail(String msg) {
		return new JsonResult(false, msg);
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
