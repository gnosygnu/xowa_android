package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
//#{
import org.w3c.dom.Document;
public class XmlDoc {
	public XmlNde Root() {return new XmlNde(xdoc.getDocumentElement());}		
	@gplx.Internal protected XmlDoc(Document xdoc) {this.xdoc = xdoc;} Document xdoc;
}
//#}