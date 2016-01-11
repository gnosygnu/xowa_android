package gplx.xowa.bldrs.wms.sites; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Site_namespace_itm implements To_str_able {
	public Site_namespace_itm(int id, byte[] case_tid, byte[] canonical, byte[] localized, boolean subpages, boolean content, byte[] defaultcontentmodel) {
		this.id = id; this.case_tid = case_tid; this.canonical = canonical; this.localized = localized;
		this.subpages = subpages; this.content = content; this.defaultcontentmodel = defaultcontentmodel;
	}
	public int Id() {return id;} private final int id;
	public byte[] Case_tid() {return case_tid;} private final byte[] case_tid;
	public byte[] Canonical() {return canonical;} private final byte[] canonical;
	public byte[] Localized() {return localized;} private final byte[] localized;
	public boolean Subpages() {return subpages;} private final boolean subpages;
	public boolean Content() {return content;} private final boolean content;
	public byte[] Defaultcontentmodel() {return defaultcontentmodel;} private final byte[] defaultcontentmodel;
	public String To_str() {
		return String_.Concat_with_obj("|", id, case_tid, canonical, localized, subpages, content, defaultcontentmodel);
	}
}
