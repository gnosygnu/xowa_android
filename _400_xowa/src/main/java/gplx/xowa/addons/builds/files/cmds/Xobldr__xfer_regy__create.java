package gplx.xowa.addons.builds.files.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*; import gplx.xowa.addons.builds.files.*;
import gplx.dbs.*; import gplx.xowa.addons.builds.files.dbs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;	
public class Xobldr__xfer_regy__create extends Xob_cmd__base {
	public Xobldr__xfer_regy__create(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		Db_conn conn = Xob_db_file.New__file_make(wiki.Fsys_mgr().Root_dir()).Conn();
		conn.Txn_bgn("bldr__xfer_regy");
		Xob_xfer_regy_tbl.Create_table(conn);
		Xob_xfer_regy_tbl.Create_data(usr_dlg, conn);
		Xob_xfer_regy_tbl.Create_index(usr_dlg, conn);
		Xob_xfer_regy_log_tbl.Create_table(conn);
		conn.Txn_end();
	}

	public static final String BLDR_CMD_KEY = "file.xfer_regy";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Xobldr__xfer_regy__create(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xobldr__xfer_regy__create(bldr, wiki);}
}