package com.pan.model.vo;

public class Constants {
	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * HTTP POST请求
	 */
	public static final String POST = "POST";
	/**
	 * HTTP GET请求
	 */
	public static final String GET = "GET";
	/**
	 * cookie中的JSESSIONID名称
	 */
	public static final String JSESSION_COOKIE = "JSESSIONID";
	/**
	 * url中的jsessionid名称
	 */
	public static final String JSESSION_URL = "jsessionid";

	/**
	 * 默认密码
	 */
	public static final String DEF_PASS = "670b14728ad9902aecba32e22fa4f6bd";

	/**
	 * 文件保存位置
	 */
	public static final String FILE_URL = "/WEB-INF/tmp";

	/**
	 * 文件下载根目录
	 */
	public static final String DOWNLOAD_FILE_URL = "/download";

	/**
	 * 主题
	 */
	public static final String THEMES = "themes";


	/**
	 * 日志类型
	 */
	public static final Integer LOG_BACK = 1;//后台操作日志
	public static final Integer LOG_FRONT = 2;//前台操作日志
	public static final Integer LOG_OTHER = 3;//其他操作日志

	/**
	 * 线程池创建线程的数量
	 */
	public static final Integer threadsNum = 10;
	/**
	 * 每条线程处理数据条数
	 */
	public static final Integer threadsNumber = 10;

	/**
	 * 系统编号前缀
	 */
	public static final String userPrefix = "U"; //用户

}
