package gplx.xowa.addons.builds.files.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.builds.*; import gplx.xowa.addons.builds.files.*;
import gplx.dbs.*; import gplx.xowa.addons.builds.files.dbs.*; import gplx.core.stores.*;
import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.addons.builds.files.utls.*;
import gplx.xowa.files.*;
public class Xobldr__xfer_temp__insert_thm extends Xob_cmd__base {
	public Xobldr__xfer_temp__insert_thm(Xob_bldr bldr, Xowe_wiki wiki) {super(bldr, wiki);}
	@Override public void Cmd_run() {
		Db_conn conn = Xob_db_file.New__file_make(wiki.Fsys_mgr().Root_dir()).Conn();
		Xob_xfer_temp_tbl.Create_table(conn);
		Db_stmt trg_stmt = Xob_xfer_temp_tbl.Insert_stmt(conn);
		conn.Txn_bgn("bldr__xfer_temp_thumb");
		DataRdr rdr = conn.Exec_sql_as_old_rdr(String_.Concat_lines_nl
		( "SELECT  l.lnki_id"
		, ",       l.lnki_tier_id"
		, ",       l.lnki_page_id"
		, ",       l.lnki_ext"
		, ",       l.lnki_type"
		, ",       l.lnki_src_tid"
		, ",       l.lnki_w"
		, ",       l.lnki_h"
		, ",       l.lnki_upright"
		, ",       l.lnki_time"
		, ",       l.lnki_page"
		, ",       l.lnki_count"
		, ",       o.orig_repo"
		, ",       o.orig_page_id"
		, ",       o.orig_file_ttl"
		, ",       o.orig_file_ext"
		, ",       o.orig_file_id"
		, ",       o.lnki_ttl"
		, ",       o.orig_w"
		, ",       o.orig_h"
		, ",       o.orig_media_type"
		, ",       o.orig_minor_mime"
		, "FROM    lnki_regy l"
		, "        JOIN orig_regy o ON o.lnki_ttl = l.lnki_ttl"
		, "WHERE   o.orig_file_ttl IS NOT NULL"
		, "ORDER BY o.orig_file_ttl, o.orig_repo DESC, l.lnki_w DESC"	// NOTE: local=1,common=0; DATE:2015-03-22
		));
		Xob_xfer_temp_itm temp_itm = new Xob_xfer_temp_itm();
		Xof_img_size img_size = new Xof_img_size();
		byte[] cur_ttl = Bry_.Empty; byte cur_repo = Byte_.Max_value_127;
		while (rdr.MoveNextPeer()) {
			temp_itm.Clear();
			temp_itm.Load(rdr);
			if (Bry_.Eq(cur_ttl, temp_itm.Orig_file_ttl())) {	// same ttl; DATE:2015-03-22
				if (temp_itm.Orig_repo() != cur_repo)			// if repo is different, ignore 2nd; handles images in both repos; take 1st only (which should be local)
					continue;
			}
			else {												// new ttl; update ttl, repo
				cur_ttl = temp_itm.Orig_file_ttl();
				cur_repo = temp_itm.Orig_repo();
			}
			if (temp_itm.Chk(img_size))
				temp_itm.Insert(trg_stmt, img_size);
		}
		conn.Txn_end();
	}

	public static final String BLDR_CMD_KEY = "file.xfer_temp.thumb";
	@Override public String Cmd_key() {return BLDR_CMD_KEY;} 
	public static final    Xob_cmd Prototype = new Xobldr__xfer_temp__insert_thm(null, null);
	@Override public Xob_cmd Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki) {return new Xobldr__xfer_temp__insert_thm(bldr, wiki);}
}
