package gplx.xowa.addons.bldrs.centrals.dbs.datas; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*; import gplx.xowa.addons.bldrs.centrals.dbs.*;
public class Xobc_version_regy_itm {
	public Xobc_version_regy_itm(int id, String date, String note) {
		this.id = id;
		this.date = date;
		this.note = note;
	}
	public int Id() {return id;} private final    int id;
	public String Date() {return date;} private final    String date;
	public String Note() {return note;} private final    String note;
}
