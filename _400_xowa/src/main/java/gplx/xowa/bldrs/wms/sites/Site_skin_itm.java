package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_skin_itm implements To_str_able {
	public Site_skin_itm(byte[] code, boolean dflt, byte[] name, boolean unusable) {
		this.code = code; this.dflt = dflt; this.name = name; this.unusable = unusable;
	}
	public byte[] Code() {return code;} private final byte[] code;
	public boolean Dflt() {return dflt;} private final boolean dflt;
	public byte[] Name() {return name;} private final byte[] name;
	public boolean Unusable() {return unusable;} private final boolean unusable;
	public String To_str() {return String_.Concat_with_obj("|", code, dflt, name, unusable);}
}
