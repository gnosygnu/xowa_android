package gplx.xowa.addons.apps.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.apps.*; import gplx.xowa.addons.apps.searchs.*;
import gplx.dbs.*;
public class Srch_link_tbl {
	public final    String tbl_name;
	public final    Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	public final    String fld_word_id, fld_page_id, fld_link_score;
	public final    Db_conn conn;
	public Srch_link_tbl(Db_conn conn) {
		this.conn = conn;
		this.tbl_name = "search_link";
		fld_word_id			= flds.Add_int("word_id");
		fld_page_id			= flds.Add_int("page_id");
		fld_link_score		= flds.Add_int_dflt(Fld_link_score, 0);
	}
	public void Create_tbl()				{conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Create_idx__page_id()		{}	// TODO: conn.Meta_idx_create(Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "page_id", fld_page_id));}
	public void Create_idx__link_score()	{conn.Meta_idx_create(Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "word_id__link_score", fld_word_id, Fld_link_score));}

	public static final    Srch_link_tbl[] Ary_empty = new Srch_link_tbl[0];
	public static final String Fld_link_score = "link_score";
}
