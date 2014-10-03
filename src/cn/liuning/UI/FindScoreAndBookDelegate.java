package cn.liuning.UI;


@javax.jws.WebService(targetNamespace = "http://UI.liuning.cn/", serviceName = "FindScoreAndBookService", portName = "FindScoreAndBookPort", wsdlLocation = "WEB-INF/wsdl/FindScoreAndBookService.wsdl")
public class FindScoreAndBookDelegate {

	cn.liuning.UI.FindScoreAndBook findScoreAndBook = new cn.liuning.UI.FindScoreAndBook();

	public String getCetScore(String examNumber, String name) throws Exception {
		return findScoreAndBook.getCetScore(examNumber, name);
	}

	public String getLibraryInfo(String accountNumber, String password)
			throws Exception {
		return findScoreAndBook.getLibraryInfo(accountNumber, password);
	}

}