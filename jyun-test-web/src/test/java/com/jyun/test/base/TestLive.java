package com.jyun.test.base;

import com.alibaba.fastjson.JSONObject;
import com.jyun.test.http.HttpRequest ;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuhejun on 2016/8/15.
 */
public class TestLive {

	public static final String URL_BASE = "http://gw-faner.jd.local/api/faner/video/";//达人鉴权接口
	public static final String URL_GETVIDEOAUTH = URL_BASE+"getVideoAuth.do";//获取视频权限
	protected static final String ENCODING = "UTF-8";

	/**
	 * 验证主播是否有主播权限
	 * @param pin
	 * @param appId
	 * @return
	 */
	public boolean checkLivePurview(String pin, int appId){
		boolean flag = false;

		try {
//			logger.info("##checkLivePurview----入参：pin="+pin+"|appId="+appId);
			if(StringUtils.isNotEmpty(pin)&&appId>0){
				String data = StringUtils.EMPTY;
				String url = URL_GETVIDEOAUTH;
//				String url = "";
//					String url = Constants.URL_GETVIDEOAUTH+"?pin="+URLEncoder.encode(pin, ENCODING)+"&appId="+appId;

				switch (appId){
					case 36:
						url = URL_GETVIDEOAUTH;
						break;
					default:
						return false;
				}
//				logger.info("##checkLivePurview----发送请求：url="+url);
				data = HttpRequest.sendGet(url,"pin="+ URLEncoder.encode(pin, ENCODING)+"&appId="+appId);
//				logger.info("##checkLivePurview----返回结果："+data);
				if(StringUtils.isNotBlank(data)){
					JSONObject obj = JSONObject.parseObject(data);
					String succ = obj.getString("succ");
					if("1".equals(succ)){
						Integer  isVideoAuth = obj.getInteger("isVideoAuth");
						if(isVideoAuth.equals(1)){
							flag = true;
						}
					}
				}
			}
		}catch(Exception e){
//			logger.error("##checkLivePurview----异常！error=",e);
		}

		return flag;
	}



	@Test
	public void test1(){
		boolean binglexu = checkLivePurview("binglexu", 40);
		System.out.println(binglexu);




	}



	@Test
	public  void test2() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date start = calendar.getTime();

		calendar.add(Calendar.DAY_OF_MONTH, 2);
		calendar.add(Calendar.SECOND, -1);

		Date end = calendar.getTime();

		System.out.println(start);
		System.out.println(end);
	}



	// 过滤特殊字符
	public static String StringFilter(String str){
		// 只允许字母和数字
		// String   regEx  =  "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p   =   Pattern.compile(regEx);
		Matcher m   =   p.matcher(str);
		return   m.replaceAll("").trim();
	}
	@Test
	public void testStringFilter(){
//        String   str   =   "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";
		String   str   =   "洪光测试😄";
		System.out.println(str);
		System.out.println(StringFilter(str));
	}




}
