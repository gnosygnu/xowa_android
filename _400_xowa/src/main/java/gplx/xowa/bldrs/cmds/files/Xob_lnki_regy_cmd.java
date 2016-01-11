package gplx.xowa.bldrs.cmds.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.dbs.*; import gplx.xowa.wikis.dbs.*;
import gplx.xowa.bldrs.wkrs.*;
public class Xob_lnki_regy_cmd extends Xob_itm_basic_base implements Xob_cmd {
	public Xob_lnki_regy_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	public String Cmd_key() {return Xob_cmd_keys.Key_file_lnki_regy;}
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {
		Db_conn conn = Xob_db_file.new__file_make(wiki.Fsys_mgr().Root_dir()).Conn();
		Xob_lnki_regy_tbl.Create_table(conn);
		Xob_lnki_regy_tbl.Create_data(usr_dlg, conn, Xob_lnki_temp_wkr.Ns_file_is_case_match_all(wiki));
	}
	public void Cmd_run() {}
	public void Cmd_end() {}
	public void Cmd_term() {}
}
