package gplx.langs.xmls; import gplx.*; import gplx.langs.*;
//#{
import org.w3c.dom.NodeList;
public interface XmlNdeList {
	int Count();
	XmlNde Get_at(int i);
}
class XmlNdeList_cls_xml implements XmlNdeList {
	public int Count() {return list.getLength();}
	public XmlNde Get_at(int i) {return new XmlNde(list.item(i));}		
	@gplx.Internal protected XmlNdeList_cls_xml(NodeList list) {this.list = list;} NodeList list;
}
class XmlNdeList_cls_list implements XmlNdeList {
	public int Count() {return list.Count();}
	public XmlNde Get_at(int i) {return (XmlNde)list.Get_at(i);}
	public void Add(XmlNde xnde) {list.Add(xnde);}
	@gplx.Internal protected XmlNdeList_cls_list(int count) {list = List_adp_.new_(); list.Resize_bounds(count);} List_adp list;
}
//#}