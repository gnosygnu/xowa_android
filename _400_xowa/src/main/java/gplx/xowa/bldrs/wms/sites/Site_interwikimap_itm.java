package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_interwikimap_itm implements To_str_able {
	public Site_interwikimap_itm(byte[] prefix, boolean local, byte[] language, boolean localinterwiki, byte[] url, boolean protorel) {
		this.prefix = prefix;
		this.local = local;
		this.language = language;
		this.localinterwiki = localinterwiki;
		this.url = url;
		this.protorel = protorel;
	}
	public byte[] Prefix() {return prefix;} private final byte[] prefix;
	public boolean Local() {return local;} private final boolean local;
	public byte[] Language() {return language;} private final byte[] language;
	public boolean Localinterwiki() {return localinterwiki;} private final boolean localinterwiki;
	public byte[] Url() {return url;} private final byte[] url;
	public boolean Protorel() {return protorel;} private final boolean protorel;
	public String To_str() {return String_.Concat_with_obj("|", prefix, local, language, url, protorel);}
}
