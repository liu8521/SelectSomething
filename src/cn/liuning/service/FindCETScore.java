package cn.liuning.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import cn.liuning.javabean.Score;
import cn.liuning.utils.PublicUtils;

public class FindCETScore {

	
		CloseableHttpClient httpclient= HttpClients.createDefault();
		List<Score> list = new ArrayList<Score>();
		
		//get方式获取
		public  List<Score> findCETScore_Get(String examnumber,String name) throws Exception {  
			try {
        		//371012141209811 刘宁
        		HttpGet httpGet1 = new HttpGet("http://www.chsi.com.cn/cet/query?zkzh="+examnumber+"&xm="+name);
        		//设置请求头
        		httpGet1.setHeader("Referer", "http://www.chsi.com.cn/cet/");//这句话必须设置，有识别验证，不设置的话不知道是http://www.chsi.com.cn/cet/传过来的信息。
        		//执行
        		CloseableHttpResponse response3 = httpclient.execute(httpGet1);
        		try {
        			
		               String str = EntityUtils.toString(response3.getEntity(),"utf-8");
		               PublicUtils utils = new PublicUtils();
		               list = utils.getList_CetHtmltojavabean(str);
		               return list;
		               
	             } finally {
	                 response3.close();     
	             }
	        } finally {
	            httpclient.close();
	        }
	    }
	 
	//post方式获取
	 public  String findCETScore_Post(CloseableHttpClient httpclient) throws Exception {
		return null; 
	 
	 }
	 
	 public String output(List<Score> list){
		 	Score score=new Score();
			score=(Score) list.get(0);
			
			return "姓名："+score.getName()+"_"
	      +"学校："+score.getSchool()+"_"
	       +"考试类别："+score.getCategory()+"_"
	       +"准考证号："+score.getExamnumber()+"_"
	        +"考试时间："+score.getTestTime()+"_"
	        +"总分："+score.getTotalScore()+"_"
	        +"听力："+score.getListen()+"_"
	        +"阅读："+score.getReading()+"_"
	        +"写作与翻译："+score.getTranslate();
			
		 
	 }
}
