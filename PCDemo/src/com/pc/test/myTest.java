package com.pc.test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pc.dao.pcDao;

public class myTest {
	
	public static String getImg(String name,String path) {
		String imgName=name+new Date().getTime();
		try {
			 URL url = new URL(path);    
	         HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
	         conn.setRequestMethod("GET");
	         InputStream inStream = conn.getInputStream();//通过输入流获取图片数据    
		     byte data[] = readInputStream(inStream); 
		     FileOutputStream fos = new FileOutputStream("D:\\食谱图片集\\"+imgName+".jpg");  
		     fos.write(data);  
		     fos.close(); 
		     return imgName+".jpg";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取文件出错！");
		}
		 return "";
	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{    
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
        byte[] buffer = new byte[2048];    
        int len = 0;    
        while( (len=inStream.read(buffer)) != -1 ){    
            outStream.write(buffer, 0, len);    
        }    
        inStream.close();    
        return outStream.toByteArray();    
 } 
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		//jsoup是java中的javascript+jquery
		//抓取类型
		int typeId=0;
		int recipeId=0;
		int recipeShowId=0;
		 for(int i=1;i<20;i++){//类型的多少页~~
			try {
				String url="http://www.xinshipu.com/chuyoufenlei/?page="+i;
				Document doc=Jsoup.parse(new URL(url),3000);
				Elements element=doc.getElementsByTag("a");
				for(Element el:element){
					
					String href="http://www.xinshipu.com"+el.attr("href");//类型的路径
					String text=el.text();//类型名字
					if(href!=null&&!href.equals("")&&href.contains("/caipu/")){
						System.out.println("菜谱类型：=========="+text+"url="+href);//类型名称
						/******添加类型*******/
						typeId++;
						pcDao.addType(typeId,text.substring(0,text.indexOf("(")));
						
						//一个类型中的一些食谱
						for(int i_1=1;i_1<100;i_1+=3){
							try {
								String url_1=href+"?page="+i_1;
									//预览抓取类型
									Document doc_1=Jsoup.parse(new URL(url_1),3000);
									Elements element_1=doc_1.getElementsByTag("a");
									if(element_1.size()<=15){
										break;
									}
									for(Element el_1:element_1){
										String href_1=el_1.attr("href");
										String img_url=el_1.getElementsByTag("img").attr("src");
										String text_1=el_1.text();
										//判断是否继续循环下去
										if(href_1!=null&&!href_1.equals("")&&href_1.contains("zuofa")){
											img_url=img_url.substring(0, img_url.indexOf("@"));
											System.out.println("菜谱路径：http://www.xinshipu.com"+href_1+"菜谱名称：======="+text_1+"===图片 路径："+img_url);
											String imgName=getImg(text_1,img_url);//图片名
											/*************添加菜谱*******************/
											recipeId++;
											pcDao.addRecipe(recipeId, text_1, imgName, typeId);
											//**下载菜谱图片**//*
											//抓取菜谱详情
											/*菜谱详情*/
											String cailiao="";
											String zuofa="";
											Document doc_2=Jsoup.parse(new URL("http://www.xinshipu.com/"+href_1),10000);
											Elements elems= doc_2.getElementsByTag("div");
											
											for (Element element_2 : elems) {
												//材料
												if(element_2.attr("itemprop").equals("ingredients")){
													cailiao=element_2.text();
													//System.out.println(element_2.text());
												}
												if(element_2.attr("class").equals("mlr1 bbm")){//内容
													zuofa=element_2.getElementsByTag("p").html();
													//System.out.println(element_2.getElementsByTag("p"));
												}
												/*//图片以及名字
												if(element_2("class").equals("mlr1 mt14 new-menu-pic")){
													
													System.out.println(element_2());
												}*/
											}
											System.out.println("菜谱材料："+cailiao+"   "+zuofa);
											recipeShowId++;
											pcDao.addRecipeShow(recipeShowId, cailiao, zuofa, imgName, recipeId);
										}
									}
							} catch (Exception e) {
							}
						}
						Thread.sleep(100);
					}
				}
			} catch (Exception e) {
			}
		}
		
	}
}
