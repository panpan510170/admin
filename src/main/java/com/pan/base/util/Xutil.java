package com.pan.base.util;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Xutil {
    public static double RATE = 0.70;
    public static String RATESTR = "70";
    public static String format(double num) {
        String str = "0.00";

        if ((Math.abs(num - 0.0) < 0.00000001) || Double.isNaN(num)) {
            return str;
        }

        DecimalFormat dFormat = new DecimalFormat("#,##0.00");
        str = dFormat.format(num);

        return str;
    }

    public static String formatfour(double num) {
        String str = "0.0000";

        if ((Math.abs(num - 0.0) < 0.00000001) || Double.isNaN(num)) {
            return str;
        }

        DecimalFormat dFormat = new DecimalFormat("#,##0.0000");
        str = dFormat.format(num);

        return str;
    }

    public static String formatTwo(String num) {
        return format(Double.parseDouble(num));
    }

    public static String format(double num, String formatStr) {
        String str = "0.00";
        DecimalFormat dFormat = new DecimalFormat(formatStr);
        str = dFormat.format(0.00);
        if ((Math.abs(num - 0.0) < 0.00000001) || Double.isNaN(num)) {
            return str;
        }

        str = dFormat.format(num);

        return str;
    }
    /**
     * 比较两个日期：fDate
     * @param fDate
     * @param
     * @return
     */
    public static String compareDate(Date fDate){
    	if (fDate.getTime()>=new Date().getTime()) {
    		return "1";
		}else {
			return "0";
		}

    }


    public static String format(String num, String formatStr) {
        return format(Double.parseDouble(num), formatStr);
    }

    public static String format(int num) {
        String str = "0";

        DecimalFormat dFormat = new DecimalFormat("#,##0");
        str = dFormat.format(num);

        return str;
    }

    public static String format(BigDecimal num) {
        if (num == null) {
            return "0.00";
        }

        String str = "0.00";
        DecimalFormat dFormat = new DecimalFormat("#,##0.00");
        str = dFormat.format(num);

        return str;
    }

    //去小数点，每隔三位加一个逗号
    public static String format4index(BigDecimal num) {
        if (num == null) {
            return "0";
        }
        String str = "0";
        DecimalFormat dFormat = new DecimalFormat("#,##0.##");
        str = dFormat.format(num);
        return str;
    }

  //前台截取字符串
    public static String string4index(String str) {
        if (str!=null&&str.length()>= 8) {
            return str.substring(0, 7);
        }
        return str;
    }

    //前台截取字符串
    public static String subString(String str,String num) {
    	int length = Integer.valueOf(num);
        if (str!=null&&str.length()>= length) {
            return str.substring(0, length-1);
        }
        return str;
    }

  //前台截取字符串
    public static String hidden4index(String str) {
        if (str!=null&&str.length()>= 2) {
        	String returnStr = str.substring(0, 1);
        	returnStr+="**";
        	returnStr+=str.substring(str.length()-1);
            return returnStr;
        }
        return str;
    }

  //前台截取字符串
    public static String nameString4index(String str) {
        if(str.length()==2){
        	 return str.substring(0,1)+"*";
        }else if(str!=null&&str.length()>= 3) {
            return str.substring(0,1)+"***"+str.substring(str.length()-1);
        }
        return str;
    }
    /**
     * 模式化手机号使之显示为（189***4123）
     * 如果传入的参数为null或长度不为11的话将返回“”
     * @param phoneNumber 要格式化的手机号码
     * @return 格式化后的手机号码
     * @author yuanwenzhen 20140910
     *
     */
    public static String formatPhoneNumber(String phoneNumber){
    	if(phoneNumber !=null || phoneNumber.length()==11){
    		return phoneNumber.substring(0, 3)+"***"+phoneNumber.substring(phoneNumber.length()-4, phoneNumber.length());
    	}
    	return "";
    }

  //去小数点，每隔三位加一个逗号
    public static String format4indexString(String num) {
        if (num == null) {
            return "0";
        }
        String str = "0";
        DecimalFormat dFormat = new DecimalFormat("#,##0.##");
        str = dFormat.format(new BigDecimal(num));
        return str;
    }

    // 去小数取整加1 自由格式
    public static String formatROUND_CEILING(BigDecimal num, String formatStr) {
        if (num == null) {
            return "0";
        }

        String str = "0";
        DecimalFormat dFormat = new DecimalFormat(formatStr);
        str = dFormat.format(num.setScale(0, BigDecimal.ROUND_CEILING));

        return str;
    }

    // 去小数取整
    /**
     * 去小数取整
     */
    public static double formatROUND_FLOOR(double num) {

        double str = 0;

        str = new BigDecimal(num).setScale(0, BigDecimal.ROUND_FLOOR)
            .doubleValue();

        return str;
    }

    // 去小数取整加1 自由格式
    /**
     * 去小数取整加1
     */
    public static double formatROUND_CEILING(double num) {
        double str = 0;

        str = new BigDecimal(num).setScale(0, BigDecimal.ROUND_CEILING)
            .doubleValue();

        return str;
    }

    // 去小数取整加1 格式：#,##0
    public static String formatROUND_CEILING(BigDecimal num) {
        return formatROUND_CEILING(num, "#,##0");
    }

    // 去小数取整 自由格式
    public static String formatROUND_FLOOR(BigDecimal num, String formatStr) {
        if (num == null) {
            return "0";
        }

        String str = "0";
        DecimalFormat dFormat = new DecimalFormat(formatStr);
        str = dFormat.format(num.setScale(0, BigDecimal.ROUND_FLOOR));

        return str;
    }

    // 去小数取整 格式：#,##0
    public static String formatROUND_FLOOR(BigDecimal num) {
        return formatROUND_FLOOR(num, "#,##0");
    }

    // 为double类型的数据进行保留n位小数的操作
    public static double formatROUND_HALF(double num, int half) {
        BigDecimal b = new BigDecimal(num + "").setScale(half,
            BigDecimal.ROUND_HALF_UP);
        return b.doubleValue();

    }

    public static String format(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    // 冯骥飞修改 加入"null".equals(str)去除字符串”null“
    public static String formatNull(String str) {
        if (str == null || "null".equals(str))
            return "";
        else
            return str;
    }

    public static String formatNull(StringBuffer str) {
        if (str == null)
            return "";
        else
            return str.toString();
    }

    /**
     * 去掉字符两端的空格，如果字符为空，返回空
     *
     * @param str
     *                要处理的字符串
     * @return 处理过的字符串
     */
    public static String TrimString(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    static private String[] timePatt = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
            "MM-dd", "dd/MM", "HH:mm:ss", "HH:mm", "yyyyMMdd" };

    public static Date parse2Date(String str) {
        if (str == null) {
            return null;
        }

        SimpleDateFormat df;
        Date dd = null;
        for (int i = 0; i < timePatt.length; i++) {
            df = new SimpleDateFormat(timePatt[i]);
            try {
                dd = df.parse(str);
            } catch (Exception e) {
                dd = null;
            }
            if (dd != null)
                break;
        }

        return dd;
    }

    /**
     * 解析字符串为日期
     *
     * @param str
     * @return
     */
    public static Date parse2Date(String str, String pattern)
        throws ParseException {
        if (str == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        return formatter.parse(str);
    }

    public static String TrimString(Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (time == null) {
            return "";
        } else {
            return formatter.format(new Date(time.getTime()));
        }
    }

    public static String TrimDate(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String format(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    // 数字的显示，防止科学计数法，留两位小数
    public static String show_number(double num) {
        String str = "";
        if (Double.isInfinite(num)) {
            return "0.00";
        }
        if (Double.isNaN(num)) {
            return "0.00";
        }

        str = String.valueOf(formatROUND_HALF1(num, 2));
        return str;
    }

    // 为double类型的数据进行保留n位小数的操作
    public static double formatROUND_HALF1(double num, int half) {
        StringBuffer str = new StringBuffer("0");
        for (int i = 0; i < half; i++) {
            if (i == 0)
                str.append(".");
            str.append("0");
        }
        DecimalFormat df = new DecimalFormat(str.toString());
        BigDecimal b = new BigDecimal(num + "").setScale(half,
            BigDecimal.ROUND_HALF_UP);
        return Double.parseDouble(df.format(b));

    }

    public static String noSecondsFormat(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String longFormat(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String longFormatNoSpace(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String longFormatNoSpaceYMD(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String noYearFormat(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String longFormat(Date time, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    /**
     * 便于字符串在页面输出
     *
     * @param Str
     *                准备处理的字符串
     * @return 处理过的字符串
     */
    public static String EncodeHTML(String Str) {
        if ((Str == null) || (Str.length() == 0)) {
            return "";
        } else {
            StringBuffer strHtml = new StringBuffer(Str);

            for (int i = 0; i < strHtml.length(); i++) {
                if (strHtml.charAt(i) == '\'') {
                    strHtml.replace(i, i + 1, "&#39");
                    i++;
                } else if (strHtml.charAt(i) == '<') {
                    strHtml.replace(i, i + 1, "&lt;");
                } else if (strHtml.charAt(i) == '>') {
                    strHtml.replace(i, i + 1, "&gt;");
                } else if (strHtml.charAt(i) == '"') {
                    strHtml.replace(i, i + 1, "&#34");
                } else if (strHtml.charAt(i) == '&') {
                    strHtml.replace(i, i + 1, "&amp;");
                }
            }

            Str = strHtml.toString();
            Str = Str.replaceAll("\r\n", "<br>");

            return Str;
        }
    }

    public static String DecodeHTML(String Str) {
        if ((Str == null) || (Str.length() == 0)) {
            return "";
        } else {
            Str = Str.replaceAll("&lt;", "<");
            Str = Str.replaceAll("&gt;", ">");
            Str = Str.replaceAll("&lt;", "<");
            Str = Str.replaceAll("&#34", "\"");
            Str = Str.replaceAll("&amp;", "&");
            Str = Str.replaceAll("<br>", "\r\n");
            Str = Str.replaceAll("\"", "'");

            return Str;
        }
    }

    public static int len(String Str) {
        if ((Str == null) || (Str.length() == 0)) {
            return 0;
        } else {
            try {
                int len = new String(Str.getBytes("GBK"), "8859_1").length();

                return len;
            } catch (UnsupportedEncodingException e) {
                return -1;
            }
        }
    }

    public static String left(String Str, int length) {
        if ((Str == null) || (Str.length() == 0)) {
            return "";
        } else {
            try {
                String str2 = new String(Str.getBytes("GBK"), "8859_1");

                if (str2.length() <= length) {
                    return Str;
                } else {
                    str2 = str2.substring(0, length);
                    str2 = new String(str2.getBytes("8859_1"), "GBK");

                    int compare = str2.substring(str2.length() - 1,
                        str2.length()).toLowerCase().compareTo("?");

                    if ((compare == 0) || (compare == 65470)) {
                        str2 = str2.substring(0, str2.length() - 2);
                    }

                    return str2;
                }
            } catch (UnsupportedEncodingException e) {
                return Str.substring(0, length);
            }
        }
    }

    public static String left(String Str, int length, String charactor) {
        if ((Str == null) || (Str.length() == 0)) {
            return "";
        } else {
            try {
                String str2 = new String(Str.getBytes("GBK"), "8859_1");

                if (str2.length() <= length) {
                    return Str;
                } else {
                    str2 = str2.substring(0, length);
                    str2 = new String(str2.getBytes("8859_1"), "GBK");

                    int compare = str2.substring(str2.length() - 1,
                        str2.length()).toLowerCase().compareTo("?");

                    if ((compare == 0) || (compare == 65470)) {
                        str2 = str2.substring(0, str2.length() - 2);
                    }

                    return str2 + charactor;
                }
            } catch (UnsupportedEncodingException e) {
                return Str.substring(0, length);
            }
        }
    }

    public static String getRidOfHtml(String msg) {
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(msg, "<");

        while (st.hasMoreTokens()) {
            String record = st.nextToken();

            StringTokenizer t = new StringTokenizer(record, ">");

            if (t.hasMoreTokens()) {
                t.nextToken();
            }

            if (t.hasMoreTokens()) {
                sb.append(" " + t.nextToken());
            }
        }

        return sb.toString();
    }

    // 时间的加减。包括，年，月，日，时，分，秒
    public static Date dateAdd(Date date, int filed, int amount) {

        GregorianCalendar calendar = new GregorianCalendar();
        Date trialTime = date;
        calendar.setTime(trialTime);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(filed, amount);
        // System.out.print(df1.format(trialTime));
        // System.out.print(df1.format(calendar.getTime()));
        return calendar.getTime();
    }

    // 时间的加减。包括，年，月，日，时，分，秒
    public static String dateAddStr(Date date, int filed, int amount) {

        GregorianCalendar calendar = new GregorianCalendar();
        // Date trialTime = date;
        calendar.setTime(date);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(filed, amount);
        // System.out.print(df1.format(trialTime));
        // System.out.print(df1.format(calendar.getTime()));
        return df1.format(calendar.getTime());
    }

    /**
     * 把逗号删掉，转化为没有的逗号的字符串
     *
     * @param param
     * @return
     */
    public static String formatString(String param) {
        StringTokenizer st = new StringTokenizer(param, ",");
        StringBuffer endmoney = new StringBuffer();
        while (st.hasMoreTokens()) {
            endmoney.append(st.nextToken());
        }
        return endmoney.toString();
    }

    /*
     * @计算某数据字段的和
     */
//    public static String sumCaculate(String field, String tableName,
//        String whereSql, String dbsrc) {
//
//        Connection conn = null;
//        String sum = "0";
//        whereSql = getStr(whereSql);
//
//        String sql = " select sum(nvl(" + field + ",0)) sum1   from "
//            + tableName + "  where 1=1 ";
//        if (!whereSql.trim().equals(""))
//            sql += whereSql;
//
//        try {
//            conn = ConnectionFactory.getConnection(dbsrc);
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            // System.out.println("sql= " + sql);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                sum = (String.valueOf(rs.getDouble(1)));
//                if (sum == null)
//                    sum = "0";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ConnectionFactory.close(conn);
//        }
//        return sum;
//    }

    /*
     * @字符串格式去掉显示null：
     */
    public static String getStr(String s) {
        if (s == null) {
            return "";
        } else if (s.equalsIgnoreCase("null")) {
            return "";
        } else {
            return s.trim();
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    // 金额转大写
    public static String GetBigMoney(double eSender) {
        double NewMoney;
        int Index, len, flag;
        String StrTemp = null;
        char[] p;
        String Number[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String MonetaryUnit[] = { "万", "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万",
                "仟", "佰", "拾", "元", "角", "分" };
        NewMoney = Math.abs(eSender);
        // System.out.println(NewMoney);
        DecimalFormat deciformat;
        deciformat = (DecimalFormat) DecimalFormat.getInstance();
        deciformat.applyPattern("#######");
        String m = String.valueOf(deciformat.format(NewMoney * 100));
        int i;
        if ((i = m.indexOf('.')) != -1)
            m = m.substring(0, i);
        p = new char[m.length()];
        // p = new char[40];
        m.getChars(0, m.length(), p, 0);
        // len=m.length();
        if (NewMoney > 100000000000.00) {
            StrTemp = ""; // FloatAsComma( NewMoney, False );
            return StrTemp;
        }
        if (NewMoney < 0.01) {
            StrTemp = "零";
            return StrTemp;
        }
        if (eSender < 0) {
            StrTemp = "负";
        } else {
            StrTemp = "";
        }
        flag = 1;
        len = p.length;
        // System.out.println("1111");
        // System.out.println(len);
        for (Index = (15 - len); Index < 15; Index++) {
            if (p[Index - 15 + len] != '0') {
                StrTemp = StrTemp
                    + Number[Integer.parseInt(String
                        .valueOf(p[Index - 15 + len]))];
                StrTemp = StrTemp + MonetaryUnit[Index];
            } else {
                if (Index == 5) {
                    if ((p[Index - 14 + len] != '0')
                        || (p[Index - 13 + len] != '0')) {
                        StrTemp = StrTemp + MonetaryUnit[Index + 3];
                        flag = 0;
                    }

                } else {
                    if ((Index == 12) || ((Index == 8) && (flag == 1))
                        || (Index == 4)) {
                        StrTemp = StrTemp + MonetaryUnit[Index];
                    }
                    if ((p[Index - 15 + len] != '0') && (Index != 14)) {
                        StrTemp = StrTemp
                            + Number[Integer.parseInt(String.valueOf(p[Index
                                - 15 + len]))];

                    }
                }
            }
        }
        if (p[m.length() - 1] == '0') {
            StrTemp = StrTemp + "整";
        }
        return StrTemp;
    }

    /**
     * 处理null结果
     *
     * @param param
     * @return
     */
    public static String getParameter(Object param) {

        if (param != null) {
            return param.toString().trim();
        } else {
            return "";
        }
    }

    /**
     * 出现 null 或 "" 用 replace替代
     *
     * @param param
     * @return
     */
    public static String getParameter(Object param, String replace) {
        if (param != null && !param.equals("")) {
            return param.toString().trim();
        } else {
            return replace;
        }
    }

    /**
     * 得到百分比
     *
     * @param param
     *                数字类型
     * @return
     */
    public static String getPercent(double param) {
        NumberFormat precent = NumberFormat.getPercentInstance(); // 建立百分比格式化引用
        precent.setMaximumFractionDigits(2); // 百分比小数最多2位

        return precent.format(param);
    }

    /**
     * 得到百分比
     *
     * @param param
     *                字符串类型
     * @return
     */
    public static String getPercent(String param) {

        double temp = 0;

        if (param != null && !param.equals("")) {
            temp = Double.parseDouble(param.trim());
        }

        return getPercent(temp);
    }

    /**
     * @Description:(显示格式控制，显示指定长度)
     * @author daoshen
     * @param val
     * @param len
     * @return
     * @date Dec 2, 2011 11:48:38 AM
     */
    public static String getSubString(String val, int len) {

        String temp = getParameter(val);

        if (val != null && val.length() > 0) {
            if (val.length() > len) {
                temp = val.substring(0, len) + "...";
            }
        }
        return temp;
    }

    public static String dayFormat(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String moonFormat(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }

    public static String yearFormat(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }


    public static String percentageDiff(BigDecimal front,BigDecimal behind){
    	String str = "0.00";

        if (behind.doubleValue()==0) {
            return str;
        }
        //front = front.subtract(behind);
        front = front.divide(behind, 4, BigDecimal.ROUND_UP);
        front = front.multiply(BigDecimal.valueOf(100));
        DecimalFormat dFormat = new DecimalFormat("#,##0.00");
        str = dFormat.format(front);

        return str;
    }

    public static String numberDiff(BigDecimal front,BigDecimal behind){
    	String str = "0.00";
        front = front.subtract(behind);

        DecimalFormat dFormat = new DecimalFormat("#,##0.00");
        str = dFormat.format(front);
        return str;
    }
    
    public static String getString4Front100(String str){
    	if(str==null){
    		return "";
    	}
    	if(str.length()==0){
    		return "";
    	}
    	
    	if(str.length()>100){
    		return str.substring(0,100) + "...";
    	}else{
    		return str;
    	}
    	
    }
}
