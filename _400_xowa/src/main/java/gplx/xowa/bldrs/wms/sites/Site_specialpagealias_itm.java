package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_specialpagealias_itm implements To_str_able {
	public Site_specialpagealias_itm(byte[] realname, byte[][] aliases) {this.realname = realname; this.aliases = aliases;}
	public byte[] Realname() {return realname;} private final byte[] realname;
	public byte[][] Aliases() {return aliases;} private final byte[][] aliases;
	public String To_str() {return String_.Concat_with_obj("|", realname, String_.Concat_with_obj(";", (Object[])aliases));}
}
