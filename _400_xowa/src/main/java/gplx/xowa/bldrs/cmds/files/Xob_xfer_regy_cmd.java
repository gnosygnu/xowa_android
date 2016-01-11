package gplx.xowa.bldrs.cmds.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.dbs.*;
import gplx.xowa.bldrs.wkrs.*;
public class Xob_xfer_regy_cmd extends Xob_itm_basic_base implements Xob_cmd {
	public Xob_xfer_regy_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki);}
	public String Cmd_key() {return Xob_cmd_keys.Key_file_xfer_regy;}
	public void Cmd_init(Xob_bldr bldr) {}
	public void Cmd_bgn(Xob_bldr bldr) {}
	public void Cmd_run() {
		Db_conn conn = Xob_db_file.new__file_make(wiki.Fsys_mgr().Root_dir()).Conn();
		conn.Txn_bgn("bldr__xfer_regy");
		Xob_xfer_regy_tbl.Create_table(conn);
		Xob_xfer_regy_tbl.Create_data(usr_dlg, conn);
		Xob_xfer_regy_tbl.Create_index(usr_dlg, conn);
		Xob_xfer_regy_log_tbl.Create_table(conn);
		conn.Txn_end();
	}
	public void Cmd_end() {}
	public void Cmd_term() {}
}
