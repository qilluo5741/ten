package com.pc.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class testMain2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) {
		try {
			String url="http://www.aihuhua.com/";
			Document doc=Jsoup.parse(new URL(url),3000);
			System.out.println(doc.html());
			/*Elements elements=doc.getElementsByTag("ul");
			for (Element element : elements) {
				System.out.println(element);
			}*/
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

}
