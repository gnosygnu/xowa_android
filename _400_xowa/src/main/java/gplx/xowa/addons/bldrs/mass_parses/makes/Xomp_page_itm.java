package gplx.xowa.addons.bldrs.mass_parses.makes; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
class Xomp_page_itm {
	public Xomp_page_itm(int id) {this.id = id;}
	public int Id() {return id;} private final    int id;
	public int Ns_id() {return ns_id;} private int ns_id;
	public byte[] Ttl_bry() {return ttl_bry;} private byte[] ttl_bry;
	public int Text_db_id() {return text_db_id;} private int text_db_id;
	public byte[] Text() {return text;} private byte[] text;

	public void Init_by_page(int ns_id, byte[] ttl_bry, int text_db_id) {
		this.ns_id = ns_id;
		this.ttl_bry = ttl_bry;
		this.text_db_id = text_db_id;
	}
	public void Init_by_text(byte[] text) {
		this.text = text;
	}

	public static final    Xomp_page_itm Null = new Xomp_page_itm(-1);
}
