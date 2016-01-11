package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_library_itm implements To_str_able {
	public Site_library_itm(byte[] name, byte[] version) {this.name = name; this.version = version;}
	public byte[] Name() {return name;} private final byte[] name;
	public byte[] Version() {return version;} private final byte[] version;
	public String To_str() {return String_.Concat_with_obj("|", name, version);}
}
