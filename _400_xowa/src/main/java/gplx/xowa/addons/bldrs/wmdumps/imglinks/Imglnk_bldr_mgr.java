package gplx.xowa.addons.bldrs.wmdumps.imglinks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.wmdumps.*;
import gplx.dbs.*;
import gplx.xowa.bldrs.*;
import gplx.xowa.files.repos.*;
class Imglnk_bldr_mgr {
	private final    Db_conn conn;
	private final    Xowe_wiki wiki;
	public Imglnk_tmp_tbl Tmp_tbl() {return tmp_tbl;} private Imglnk_tmp_tbl tmp_tbl;
	public Imglnk_bldr_mgr(Xowe_wiki wiki) {
		wiki.Init_assert();
		this.wiki = wiki;
		this.conn = Xob_db_file.New__img_link(wiki).Conn();
		this.tmp_tbl = new Imglnk_tmp_tbl(conn);
		conn.Meta_tbl_remake(tmp_tbl);
		tmp_tbl.Create_tbl();
		tmp_tbl.Insert_bgn();
	}
	public void On_cmd_end() {
		// finalize txn; create idx
		tmp_tbl.Insert_end();
		tmp_tbl.Create_idx__img_ttl();

		// create reg_tbl
		Imglnk_reg_tbl reg_tbl = new Imglnk_reg_tbl(conn);
		conn.Meta_tbl_remake(reg_tbl);
		reg_tbl.Create_idx__src_ttl();
		reg_tbl.Insert(conn, Xof_repo_itm_.Repo_local , wiki);
		reg_tbl.Insert(conn, Xof_repo_itm_.Repo_remote, wiki.Appe().Wiki_mgr().Wiki_commons());
		reg_tbl.Create_idx__trg_ttl();

//			Imglnk_bulk_cmd__img_id.Bulk_exec(conn, Bool_.Y, wiki);
//			Imglnk_bulk_cmd__img_id.Bulk_exec(conn, Bool_.N, wiki.Appe().Wiki_mgr().Wiki_commons());
	}
}
