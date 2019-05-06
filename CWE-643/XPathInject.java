import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathInject {
	public static void main(String[] args) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = null;
		Document doc = null;
		String username = "";
		String password = "";
		if(args.length>=2) {
			username = args[0];
			password = args[1];
		}
		String s = "//users/user[username/text()=%s and password/text() = %s]/home_dir/text()";
		s = String.format(s, username, password);
		System.out.println(s);
		try {
			expr = xpath.compile(s);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("user.xml"));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			NodeList homedir = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);
			if(homedir.getLength()>0) {
				for(int i=0;i<homedir.getLength();i++) {
					System.out.println(homedir.item(i).getNodeValue());
				}
			}else {
				System.out.println(String.format("Can not found the given user directory"));
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
}
