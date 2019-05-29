package com.pan.util;

import java.util.List;

/**
 * 查询结果集
 * 
 * @author 呼云飞
 *
 * @param <T>
 */
public class QueryResult<T> {

	private int currentPage;

	private int pageSize;

	private int total;

	private List<T> rows;

	public QueryResult() {

	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public static <T> QueryResult<T> newResult(List<T> rows, int total) {
		return new QueryResult<T>(rows,total);
	}

	public QueryResult(int currentPage, int pageSize, int total, List<T> rows) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.rows = rows;
		this.total = total;
	}


	public QueryResult(List<T> rows, int total) {
		this.total = total;
		this.rows = rows;
	}

}
