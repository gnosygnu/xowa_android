package gplx.dbs; import gplx.*;
import gplx.core.strings.*;
public abstract class Db_conn_info__base implements Db_conn_info {
	public abstract String Tid();
	public String Xto_raw() {return raw;} private String raw = "";
	public String Xto_api() {return api;} private String api = "";
	public String Database() {return database;} protected String database = "";
	public String Server() {return server;} private String server = "";
	public abstract Db_conn_info New_self(String raw, GfoMsg m);
	protected void Ctor(String server, String database, String raw, String api) {this.server = server; this.database = database; this.raw = raw; this.api = api;}
	protected static String BldApi(GfoMsg m, KeyVal... xtnAry) {
		String_bldr sb = String_bldr_.new_();
		Hash_adp hash = Hash_adp_.new_();
		for (int i = 0; i < m.Args_count(); i++) {
			KeyVal kv = m.Args_getAt(i);
			sb.Add_fmt("{0}={1};", kv.Key(), kv.Val_to_str_or_empty());
			hash.Add_as_key_and_val(kv.Key());
		}
		for (KeyVal xtn : xtnAry) {
			if (hash.Has(xtn.Key())) continue;
			sb.Add_fmt("{0}={1};", xtn.Key(), xtn.Val_to_str_or_empty());
		}
		return sb.To_str();
	}
	protected static String Bld_raw(String... ary) {
		Bry_bfr bfr = Bry_bfr.reset_(255);
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			String itm = ary[i];
			bfr.Add_str_u8(itm);
			bfr.Add_byte(i % 2 == 0 ? Byte_ascii.Eq : Byte_ascii.Semic);
		}
		return bfr.To_str();
	}
}
