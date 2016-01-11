package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_magicword_itm implements To_str_able {
	public Site_magicword_itm(byte[] name, boolean case_match, byte[][] aliases) {
		this.name = name; this.case_match = case_match; this.aliases = aliases;
	}
	public byte[] Name() {return name;} private final byte[] name;
	public boolean Case_match() {return case_match;} private final boolean case_match;
	public byte[][] Aliases() {return aliases;} private final byte[][] aliases;
	public String To_str() {return String_.Concat_with_obj("|", case_match, name, String_.Concat_with_obj(";", (Object[])aliases));}
}
