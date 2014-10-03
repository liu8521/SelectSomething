package cn.liuning.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.liuning.javabean.Book;
import cn.liuning.javabean.Score;
import cn.liuning.utils.PublicUtils;


public class FindLibrary {
		CloseableHttpClient httpclient= HttpClients.createDefault();
		List<Score> list = new ArrayList<Score>();
		
		//以post方式登陆系统
		public void findLibrary_Login(String account, String password) throws Exception
		{
            HttpPost httpPost = new HttpPost("http://222.206.65.12/reader/redr_verify.php");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("number", account));
            nvps.add(new BasicNameValuePair("passwd", password));
            nvps.add(new BasicNameValuePair("select", "cert_no"));
            nvps.add(new BasicNameValuePair("returnUrl", ""));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                EntityUtils.toString(response2.getEntity());
            } finally {
                response2.close();
            }
		 	
		}
		
	//get方式获取数据
	 public List<Book> findLibrary_Get() throws Exception
	 {	
            HttpGet httpGet = new HttpGet("http://222.206.65.12/reader/book_lst.php");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            try {
               String str = EntityUtils.toString(response1.getEntity());
               str = new String(str.getBytes("iso8859-1"), "utf-8");
               PublicUtils utils = new PublicUtils();
               List<Book> list = utils.getList_BookHtmltojavabean(str);
               return list;
            } finally {
                response1.close();
            }
	  }
	 
 	//post方式获取
	 public  String findLibrary_Post(CloseableHttpClient httpclient) throws Exception
	 { 
		 return null; 
	 }

	//查询入口
	public List<Book> findLibrary(String account, String password) throws Exception {
		findLibrary_Login(account,password);//先登录
		return findLibrary_Get();//后查询
	}
	
	//输出函数
	public String output(List<Book> list2) {
		String str="";
		for (int j = 0; j < list2.size(); j++) {
			Book book = list2.get(j);
			PublicUtils utils = new PublicUtils();
			String a = utils.bookTostring(book);
			
			str=str+a+"_";
		}
		return str;
	}

}

  