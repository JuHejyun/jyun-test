package com.jyun.test.unicode;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhuhejun on 2016/6/14.
 */
public class JavaUnicode {


	@Test
	public void test1() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				System.out.print("請輸入文字：");

				String character = in.readLine();
				char[] ch = character.toCharArray();
				StringBuilder sb = new StringBuilder(ch.length);
				for (int i : ch)
					sb.append(Integer.toHexString(i))
							.append(" ");
				System.out.printf("%s 的 Unicode 編碼是: %s%n", character, sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test2() {

			try {

				String character = "哈的好不好玩";
				char[] ch = character.toCharArray();
				StringBuilder sb = new StringBuilder(ch.length);
				for (int i : ch)
					sb.append(Integer.toHexString(i))
							.append(" ");
				System.out.printf("%s 的 Unicode 編碼是: %s%n", character, sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

	}


	@Test
	public void test3(){
		System.out.println("Hello, it's: " +  new Date());

		//print available locales
		Locale list[] = DateFormat.getAvailableLocales();
		System.out.println("======System available locales:======== ");
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i].toString() + "\t" + list[i].getDisplayName());
		}

		//print JVM default properties
		System.out.println("======System property======== ");
		System.getProperties().list(System.out);
	}

	@Test
	public void test4() throws ParseException {

		String beginTime = "20160729093400";


		DateFormat fromFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date beginDate = fromFormat.parse(beginTime);


		boolean flag = false;
		long begin = beginDate.getTime();


		long currentTime = System.currentTimeMillis();

		if(currentTime<begin){
			long weekTime = currentTime +60*60*24*7*1000;
			if(weekTime>begin){
				flag = true;
			}
		}

		System.out.println(flag);

	}




}
