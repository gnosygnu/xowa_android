package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_interwikimap_itm implements To_str_able {
	public Site_interwikimap_itm(byte[] prefix, boolean local
		, boolean extralanglink, byte[] linktext, byte[] sitename
		, byte[] language, boolean localinterwiki, byte[] url, boolean protorel) {
		this.Prefix = prefix;
		this.Local = local;
		this.Extralanglink = extralanglink;
		this.Linktext = linktext;
		this.Sitename = sitename;
		this.Language = language;
		this.Localinterwiki = localinterwiki;
		this.Url = url;
		this.Protorel = protorel;
	}
	public final    byte[]		Prefix;
	public final    boolean		Local;
	public final    boolean		Extralanglink;
	public final    byte[]		Linktext;
	public final    byte[]		Sitename;
	public final    byte[]		Language;
	public final    boolean		Localinterwiki;
	public final    byte[]		Url;
	public final    boolean		Protorel;
	public String To_str() {return String_.Concat_with_obj("|", Prefix, Local, Extralanglink, Linktext, Sitename, Language, Localinterwiki, Url, Protorel);}
}
