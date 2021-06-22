package com.htf.zdh.common;

import java.io.Serializable;

/***
 * 返回数据封装类
 */
public class Results implements Serializable {
	private static final long serialVersionUID = 1L;

	static String result_void = null;
	
	private String msg;
	private int status;
	private transient Object data;

	public Results(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public Results() {
		super();
	}

	public Results(Object data) {
		this(Status.OK,Status.OK_MSG, data);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}


	@Override
	public String toString() {
		return "Result [msg=" + msg + ", status=" + status + ", data=" + data + "]";
	}

	public static Results okMsg(){
		return new Results(Status.OK,Status.OK_MSG,result_void);
	}
	
	public static Results okMsg(String msg){
		return new Results(Status.OK,msg,result_void);
	}
	
	public static Results ok(Object data){
		return new Results(Status.OK,Status.OK_MSG,data);
	}
	
	public static Results ok(int status, String msg, Object data){
		return new Results(status,msg,data);
	}
	
	public static Results ok(int status, String msg){
		return new Results(status,msg,result_void);
	}
	
	public static Results fail(int status){
		return new Results(status,result_void,result_void);
	}
	
	public static Results fail(int status, String msg){
		return new Results(status,msg,result_void);
	}
	
	public static Results fail(int status, String msg, Object data){
		return new Results(status,msg,data);
	}
}
