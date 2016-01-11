package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_showhook_itm implements To_str_able {
	public Site_showhook_itm(byte[] name, byte[] scribunto, byte[][] subscribers) {
		this.name = name; this.scribunto = scribunto; this.subscribers = subscribers;
	}
	public byte[] Name() {return name;} private final byte[] name;
	public byte[] Scribunto() {return scribunto;} private final byte[] scribunto;
	public byte[][] Subscribers() {return subscribers;} private final byte[][] subscribers;
	public String To_str() {return String_.Concat_with_obj("|", name, scribunto, String_.Concat_with_obj(";", (Object[])subscribers));}
}
