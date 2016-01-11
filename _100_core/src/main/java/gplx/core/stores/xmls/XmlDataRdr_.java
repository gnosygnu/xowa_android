package gplx.core.stores.xmls; import gplx.*; import gplx.core.*; import gplx.core.stores.*;
public class XmlDataRdr_ {
	public static XmlDataRdr file_(Io_url url) {
		String text = Io_mgr.Instance.LoadFilStr(url);
		return new XmlDataRdr(text);
	}
	public static XmlDataRdr text_(String text) {return new XmlDataRdr(text);}
}
