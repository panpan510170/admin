package com.pan.vo;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * web前端json数据处理类
 * 
 * @author
 */
@ApiModel(value = "通用返回对象")
public class JsonResult<T> implements Serializable {
	public static interface ErrorView {
	}

	public static interface NormalView extends ErrorView {
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 3863559687276427577L;

	@JsonView(NormalView.class)
	private Integer code;

	@JsonView(NormalView.class)
	private T data;

	@JsonView(ErrorView.class)
	private String message;

	public JsonResult() {
	}

	public JsonResult(T data, Integer code, String message) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.code = code;
		this.message = message;

	}

	public JsonResult(Integer code, String message) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.message = message;

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static <T> JsonResult<T> newResult() {

		return new JsonResult<T>();
	}
}
