package gplx.xowa.addons.builds.files.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*; import gplx.xowa.addons.builds.files.*;
import gplx.dbs.*; import gplx.xowa.addons.builds.files.dbs.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*;	
public class Xobldr__lnki_regy__create extends Xob_cmd__base implements Xob_cmd {
	public Xobldr__lnki_regy__create(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		Db_conn conn = Xob_db_file.New__file_make(wiki.Fsys_mgr().Root_dir()).Conn();
		Xob_lnki_regy_tbl.Create_table(conn);
		Xob_lnki_regy_tbl.Create_data(usr_dlg, conn, Xobldr__lnki_temp__create.Ns_file_is_case_match_all(wiki));
	}

	public static final String BLDR_CMD_KEY = "file.lnki_regy";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Xobldr__lnki_regy__create(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xobldr__lnki_regy__create(bldr, wiki);}
}
