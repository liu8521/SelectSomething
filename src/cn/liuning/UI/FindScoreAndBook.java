package cn.liuning.UI;

import java.util.List;

import cn.liuning.javabean.Book;
import cn.liuning.javabean.Score;
import cn.liuning.service.FindCETScore;
import cn.liuning.service.FindLibrary;

public class FindScoreAndBook {
	public String getCetScore(String examNumber,String name) throws Exception{
		FindCETScore findCet=new FindCETScore();
		List<Score> list1 = findCet.findCETScore_Get(examNumber, name);
		
		return findCet.output(list1);
	}
	
	public String getLibraryInfo(String accountNumber,String password) throws Exception{
		FindLibrary findBook = new FindLibrary();
		List<Book> list2=findBook.findLibrary(accountNumber,password);
		return findBook.output(list2);
	}
}
