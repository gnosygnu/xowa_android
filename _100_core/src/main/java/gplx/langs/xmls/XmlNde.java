package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
//#{import
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
//#}
public class XmlNde {
	//#{XmlNde
	public XmlAtrList Atrs() {return new XmlAtrList(xnde.getAttributes());}
	public XmlNdeList SubNdes() {return new XmlNdeList_cls_xml(xnde.getChildNodes());}
	public String Name() {return xnde.getNodeName();}
	public String Xml_outer() {
	    Transformer transformer = transformer_();
	    StringWriter writer = new StringWriter();
	    try {transformer.transform(new DOMSource(xnde), new StreamResult(writer));}
	    catch (TransformerException e) {throw Err_.new_exc(e, "xml", "failed to get xml string");}
	    return writer.toString(); 
	}
	public String Text_inner() {return xnde.getTextContent();}
	public boolean NdeType_element() {return xnde.getNodeType() == Node.ELEMENT_NODE;}
	public boolean NdeType_textOrEntityReference() {return xnde.getNodeType() == Node.TEXT_NODE || xnde.getNodeType() == Node.ENTITY_REFERENCE_NODE;}
	@gplx.Internal protected XmlNde(Node xnde) {this.xnde = xnde;} Node xnde;
	static Transformer transformer_() {
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {transformer = transformerfactory.newTransformer();}
		catch (TransformerConfigurationException e) {throw Err_.new_exc(e, "xml", "failed to get create transformer");}
	    transformer.setOutputProperty("omit-xml-declaration", "yes");
		return transformer;
	}
	//#}
}
