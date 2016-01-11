package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
//#{
import org.w3c.dom.Node;
public class XmlAtr {
	public String Name() {return xatr.getNodeName();}
	public String Value() {return xatr.getNodeValue();}
	@gplx.Internal protected XmlAtr(Node xatr) {this.xatr = xatr;} Node xatr;
}
//#}
