package gplx.xowa.wikis.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xoctg_data_ctg {
	public Xoctg_data_ctg(byte[] ctg_name) {this.ctg_name = ctg_name;}
	public byte[] Ctg_name() {return ctg_name;} private byte[] ctg_name;
	public boolean Db_mgr_is_txt() {return db_mgr_is_txt;} public Xoctg_data_ctg Db_mgr_is_txt_(boolean v) {db_mgr_is_txt = v; return this;} private boolean db_mgr_is_txt = true;
	public int Total_by_tid(byte tid) {
		Xoctg_idx_mgr grp = Grp_by_tid(tid);
		return grp == null ? 0 : grp.Total();
	}
	public int Total_count() {
		int rv = 0;
		for (int i = 0; i < 3; i++) {
			Xoctg_idx_mgr grp_mgr = grp_mgrs[i];
			if (grp_mgr != null) rv += grp_mgr.Total();
		}
		return rv;
	}
	public boolean Hidden() {return hidden;} public Xoctg_data_ctg Hidden_(boolean v) {hidden = v; return this;} private boolean hidden;
	public Xoctg_idx_mgr[] Grp_mgrs() {return grp_mgrs;} private Xoctg_idx_mgr[] grp_mgrs = new Xoctg_idx_mgr[3];
	public Xoctg_idx_mgr Grp_by_tid(byte i) {return grp_mgrs[i];}
}
