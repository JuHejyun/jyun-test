package com.jyun.test.unicode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyun.test.http.HttpRequest;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhuhejun on 2016/7/20.
 */
public class Testaaaaaa {

	@Test
	public void test(){

//		String aa = "aaaa|bbbb";
//		String[] split = aa.split("\\|");
//
//
//		System.out.println(split);


//		Long cc = 0l;
//
//		Long bb = new Long(502455425);


//		boolean b = bb.compareTo(new Long(0))>0;
//		boolean b = bb>0l;

//		System.out.println(b);

		int a = 5%3;
		int b = indexFor(5,3);

		System.out.println(a+"bbb"+b);


	}

	/**
	 * Returns index for hash code h.
	 */
	static int indexFor(int h, int length) {
		// assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
		return h & (length-1);
	}

	@Test
	public  void test1(){
		String s = CharacterSetToolkit.toUnicode("测试商品，请勿购买 ，京米视频's",false);
//		String s = CharacterSetToolkit.toUnicode("#",false);
//		String s = string2Unicode("CITY&CASE 苹果6s手机壳硅胶防摔萌趣创意保护套 适用于iPhone6/6plus 小兔子吃萝卜(4.7英寸)");
//		Character.toString((char)"测试商品，请勿购买 ，京米视频's");
//		try {
//			String s= URLEncoder.encode("测试商品，请勿购买 ，京米视频's","UTF-8");
			System.out.println(s);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}


	}
	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);
			String str =  Integer.toHexString(c);
			unicode.append("\\u");
			for (int j = 0; j < 4-str.length(); j++) {
				unicode.append("0");
			}
			// 转换为unicode
			unicode.append(str);
		}

		return unicode.toString();
	}
	static String getUnicode(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 0; i < bytes.length - 1; i += 2) {
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				for (int j = str1.length(); j < 2; j++) {
					out.append("0");
				}
				out.append(str1);
				out.append(str);

			}
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Test
	public void test2() throws IOException {
//		String s = "\\u6D4B\\u8BD5\\u5546\\u54C1\\uFF0C\\u8BF7\\u52FF\\u8D2D\\u4E70\\u0020\\uFF0C\\u4EAC\\u7C73\\u89C6\\u9891\\u0027\\u0073";
//		String s1 = CharacterSetToolkit.fromUnicode(s.toCharArray(), 0, s.length(), new char[1024]);
//
//
//		System.out.println(s1);


		String res = HttpRequest.sendGet("http://gw.video.jd.com/le/mp4json.action?vu=b849923263&appid=27&test=1&vtype=mp4&protocol=https", "");

		ObjectMapper objectMapper = new ObjectMapper();


		Map<String, Map<String, Object>> maps = objectMapper.readValue(res, Map.class);
		System.out.println(maps.size());

		Map<String, Object> map = maps.get("data");
		String dataJson = objectMapper.writeValueAsString(map);

		String unicode = CharacterSetToolkit.toUnicode(dataJson, true);

		System.out.println(unicode);

	}


	@Test
	public void test3(){
		String a = "%E5%93%88%E5%93%88%E5%93%88%24%24";
		try {
			String decode = URLDecoder.decode(a, "UTF-8");
			System.out.println(decode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}



	@Test
	public void test4(){
		BigDecimal bbb = new BigDecimal(10).divide(new BigDecimal(1).multiply(new BigDecimal(3)),BigDecimal.ROUND_UP);
		System.out.println(bbb.intValue());
	}


	/**
	 * 计算时间差，向上取整
	 *
	 * @param startTime 开始日期
	 * @param endTime   结束日期
	 * @return 根据时间差取整数
	 */
	public static long getDateDiff(long startTime, long endTime, String unitId) {
		long result = 0L;
		long between = endTime - startTime;//时间取秒
		if ("d".equals(unitId)) {
			between += (3600 * 24 * 1000);//时间单位是天，向上取整，所以要先加上一天的时间
			result = between / (24 * 3600 * 1000);
		} else if ("h".equals(unitId)) {
			between += (3600 * 1000);//时间单位是小时，向上取整，所以要先加上一个小时的时间
			result = between / (3600 * 1000);
		}
		return result;
	}


	/**
	 * 计算时间差，向上取整
	 *
	 * @param startTime 开始日期
	 * @param endTime   结束日期
	 * @return 根据时间差取整数
	 */
	public static long getDateDiff2(long startTime, long endTime, String unitId) {
		long result = 0L;
		long between = endTime - startTime;//时间取秒
		if ("d".equals(unitId)) {
			int days = (int) (between / (1000 * 3600 * 24));//时间单位是天，向上取整，所以要先加上一天的时间
			long l = between % (1000 * 3600 * 24);
			if (l > 0) {
				days++;
			}
			return days;
		} else if ("h".equals(unitId)) {

			int hour = (int) (between / (1000 * 3600));//时间单位是小时，向上取整，所以要先加上一个小时的时间
			long l = between % (1000 * 3600);
			if (l > 0) {
				hour++;
			}
			return hour;
		}
		return result;
	}



	@Test
	public void test444(){
		Date star = new Date(1483333200000L);
		Date end = new Date(1483506000000L);


		long h1 = getDateDiff(star.getTime(), end.getTime(), "d");
		long h2 = getDateDiff2(star.getTime(), end.getTime(), "d");

		System.out.println(h1+"----"+h2);
	}


	@Test
	public void test446(){
		Long a = 2L;
		Integer b = null;


		System.out.println(a/(1000*60*60));
	}


}
