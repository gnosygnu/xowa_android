package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_language_itm implements To_str_able {
	public Site_language_itm(byte[] code, byte[] name) {this.code = code; this.name = name;}
	public byte[] Code() {return code;} private final byte[] code;
	public byte[] Name() {return name;} private final byte[] name;
	public String To_str() {return String_.Concat_with_obj("|", code, name);}
}
