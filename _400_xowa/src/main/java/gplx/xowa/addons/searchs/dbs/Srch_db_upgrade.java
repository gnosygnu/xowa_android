package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*;
public class Srch_db_upgrade {
	private final Srch_db_mgr search_db_mgr;
	public Srch_db_upgrade(Srch_db_mgr search_db_mgr) {this.search_db_mgr = search_db_mgr;}
	public int Version() {
		if (version == Version__unknown) {
			Srch_word_tbl word_tbl = search_db_mgr.Tbl__word();
			if		(word_tbl.fld_link_count		== Dbmeta_fld_itm.Key_null) version = Version__initial;
			else if (word_tbl.fld_link_score_min	== Dbmeta_fld_itm.Key_null) version = Version__page_count;
			else																version = Version__link_score;
		}
		return version;
	}	private int version = Version__unknown;
	public boolean Version_check() {
		boolean rv = version_check;
		version_check = true;
		return rv;
	}	private boolean version_check;
	public void Upgrade() {
		switch (version) {
			case Version__initial:					Upgrade__page_count(); break;
			case Version__page_count:	Upgrade__page_score_max(); break;
			default: throw Err_.new_unhandled_default(version);
		}
	}
	private void Upgrade__page_count() {
		Srch_word_tbl word_tbl = search_db_mgr.Tbl__word();
		Srch_link_tbl link_tbl = search_db_mgr.Tbl__link__get_at(0);
		Db_conn conn = word_tbl.conn;
		conn.Txn_bgn("schema__search_word__upgrade");
		conn.Meta_fld_assert(word_tbl.tbl_name, "word_page_count", Dbmeta_fld_tid.Itm__int, 0);
		conn.Exec_sql_plog_ntx("calculating page count per word (please wait)", String_.Format(String_.Concat_lines_nl_skip_last
		( "REPLACE INTO {0} ({1}, {2}, word_page_count)"
		, "SELECT   w.{1}"
		, ",        w.{2}"
		, ",        Count(l.{4})"
		, "FROM     {0} w"
		, "         JOIN {3} l ON w.{1} = l.{4}"
		, "GROUP BY w.{1}"
		, ",        w.{2};"
		), word_tbl.tbl_name, word_tbl.fld_id, word_tbl.fld_text
		, link_tbl.tbl_name, link_tbl.fld_word_id
		));
		conn.Txn_end();
	}
	private void Upgrade__page_score_max() {
		Srch_word_tbl word_tbl = search_db_mgr.Tbl__word();
		Db_conn conn = word_tbl.conn;
		conn.Txn_bgn("schema__search_word__upgrade");
		conn.Meta_fld_assert(word_tbl.tbl_name, Srch_word_tbl.Fld_link_score_min, Dbmeta_fld_tid.Itm__int, 0);
		conn.Meta_fld_assert(word_tbl.tbl_name, Srch_word_tbl.Fld_link_score_max, Dbmeta_fld_tid.Itm__int, 0);
		conn.Txn_end();
	}
	public static final int 
	  Version__unknown					= 0
	, Version__initial					= 1
	, Version__page_count				= 2
	, Version__link_score				= 3
	;
}
