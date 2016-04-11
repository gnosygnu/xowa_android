package gplx.xowa.bldrs.cmds.texts.sqls; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*; import gplx.xowa.bldrs.cmds.texts.*;
import gplx.xowa.bldrs.*; import gplx.xowa.apps.apis.xowa.bldrs.imports.*;
import gplx.xowa.xtns.wdatas.imports.*;
public class Xob_init_cmd extends Xob_init_base {
	public Xob_init_cmd(Xob_bldr bldr, Xowe_wiki wiki) {this.Ctor(bldr, wiki);}
	@Override public String Cmd_key() {return Xob_cmd_keys.Key_text_init;}
	@Override public void Cmd_ini_wdata(Xob_bldr bldr, Xowe_wiki wiki) {
		bldr.Cmd_mgr().Add_cmd(wiki, Xob_cmd_keys.Key_wbase_qid);
		bldr.Cmd_mgr().Add_cmd(wiki, Xob_cmd_keys.Key_wbase_pid);
	}
	@Override public void Cmd_init(Xob_bldr bldr) {
		super.Cmd_init(bldr);
//			gplx.dbs.qrys.bats.Db_batch__journal_wal.Batch__init(gplx.dbs.Db_conn_pool.Instance.Batch_mgr());
	}

	@Override public void Cmd_run_end(Xowe_wiki wiki) {
		if (gplx.xowa.wikis.Xow_fsys_mgr.Find_core_fil(wiki) != null)
			throw wiki.Appe().Bldr().Usr_dlg().Fail_many("", "", "directory must not contain any .xowa or .sqlite3 files: dir=~{0}", wiki.Fsys_mgr().Root_dir().Raw());
		Xowe_wiki_.Create(wiki, wiki.Import_cfg().Src_rdr_len(), wiki.Import_cfg().Src_fil().NameOnly());
	}
	@Override public void Cmd_term() {
		super.Cmd_term();
//			gplx.dbs.qrys.bats.Db_batch__journal_wal.Batch__term(gplx.dbs.Db_conn_pool.Instance.Batch_mgr());
//			gplx.dbs.Db_conn_pool.Instance.Rls_all();
	}
}
