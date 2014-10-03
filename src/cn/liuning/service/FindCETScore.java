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
		
		//get��ʽ��ȡ
		public  List<Score> findCETScore_Get(String examnumber,String name) throws Exception {  
			try {
        		//371012141209811 ����
        		HttpGet httpGet1 = new HttpGet("http://www.chsi.com.cn/cet/query?zkzh="+examnumber+"&xm="+name);
        		//��������ͷ
        		httpGet1.setHeader("Referer", "http://www.chsi.com.cn/cet/");//��仰�������ã���ʶ����֤�������õĻ���֪����http://www.chsi.com.cn/cet/����������Ϣ��
        		//ִ��
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
	 
	//post��ʽ��ȡ
	 public  String findCETScore_Post(CloseableHttpClient httpclient) throws Exception {
		return null; 
	 
	 }
	 
	 public String output(List<Score> list){
		 	Score score=new Score();
			score=(Score) list.get(0);
			
			return "������"+score.getName()+"_"
	      +"ѧУ��"+score.getSchool()+"_"
	       +"�������"+score.getCategory()+"_"
	       +"׼��֤�ţ�"+score.getExamnumber()+"_"
	        +"����ʱ�䣺"+score.getTestTime()+"_"
	        +"�ܷ֣�"+score.getTotalScore()+"_"
	        +"������"+score.getListen()+"_"
	        +"�Ķ���"+score.getReading()+"_"
	        +"д���뷭�룺"+score.getTranslate();
			
		 
	 }
}
