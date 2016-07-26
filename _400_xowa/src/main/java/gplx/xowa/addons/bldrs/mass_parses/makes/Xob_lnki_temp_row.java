package gplx.xowa.addons.bldrs.mass_parses.makes; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*;
import gplx.dbs.*;
class Xob_lnki_temp_row implements CompareAble {
	public int Xomp_uid() {return xomp_uid;} private int xomp_uid;
	public int Make_uid() {return make_uid;} private int make_uid;
	public int Lnki_tier_id() {return lnki_tier_id;} private int lnki_tier_id;
	public int Lnki_page_id() {return lnki_page_id;} private int lnki_page_id;
	public byte[] Lnki_ttl() {return lnki_ttl;} private byte[] lnki_ttl;
	public byte[] Lnki_commons_ttl() {return lnki_commons_ttl;} private byte[] lnki_commons_ttl;
	public byte Lnki_ext() {return lnki_ext;} private byte lnki_ext;
	public byte Lnki_type() {return lnki_type;} private byte lnki_type;
	public byte Lnki_src_tid() {return lnki_src_tid;} private byte lnki_src_tid;
	public int Lnki_w() {return lnki_w;} private int lnki_w;
	public int Lnki_h() {return lnki_h;} private int lnki_h;
	public double Lnki_upright() {return lnki_upright;} private double lnki_upright;
	public double Lnki_time() {return lnki_time;} private double lnki_time;
	public int Lnki_page() {return lnki_page;} private int lnki_page;
	public void Load(Db_rdr rdr, int xomp_uid) {
		this.xomp_uid = xomp_uid;
		this.make_uid = rdr.Read_int("lnki_make_uid");
		this.lnki_tier_id = rdr.Read_int("lnki_tier_id");
		this.lnki_page_id = rdr.Read_int("lnki_page_id");
		this.lnki_ttl = rdr.Read_bry_by_str("lnki_ttl");
		this.lnki_commons_ttl = rdr.Read_bry_by_str("lnki_commons_ttl");
		this.lnki_ext = rdr.Read_byte("lnki_ext");
		this.lnki_type = rdr.Read_byte("lnki_type");
		this.lnki_src_tid = rdr.Read_byte("lnki_src_tid");
		this.lnki_w = rdr.Read_int("lnki_w");
		this.lnki_h = rdr.Read_int("lnki_h");
		this.lnki_upright = rdr.Read_double("lnki_upright");
		this.lnki_time = rdr.Read_int("lnki_time");
		this.lnki_page = rdr.Read_int("lnki_page");
	}
	public int compareTo(Object obj) {
		Xob_lnki_temp_row comp = (Xob_lnki_temp_row)obj;
		int rv = Int_.Compare(xomp_uid, comp.xomp_uid);
		if (rv != CompareAble_.Same) return rv;
		return Int_.Compare(make_uid, comp.make_uid);
	}
}
