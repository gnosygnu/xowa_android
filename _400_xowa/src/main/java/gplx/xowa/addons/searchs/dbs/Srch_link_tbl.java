package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.core.primitives.*;
import gplx.dbs.*; import gplx.dbs.qrys.*; import gplx.dbs.utls.*; import gplx.xowa.wikis.data.tbls.*;
public class Srch_link_tbl {
	public final String tbl_name;
	public final Dbmeta_fld_list flds = Dbmeta_fld_list.new_();
	public final String fld_word_id, fld_page_id, fld_link_score;
	public final boolean schema_is_1;
	public final Db_conn conn; private final Srch_link_tbl__in_wkr in_wkr = new Srch_link_tbl__in_wkr();
	public Srch_link_tbl(Db_conn conn, boolean schema_is_1) {
		this.conn = conn; this.schema_is_1 = schema_is_1;
		String fld_prefix = "";
		if (schema_is_1)	{tbl_name = "search_title_page"; fld_prefix = "stp_";}
		else				{tbl_name = "search_link";}
		fld_word_id			= flds.Add_int(fld_prefix + "word_id");
		fld_page_id			= flds.Add_int(fld_prefix + "page_id");
		fld_link_score		= flds.Add_int_dflt(fld_prefix + Fld_link_score, 0);
		in_wkr.Ctor(tbl_name, flds, fld_page_id, fld_word_id);
	}
	public void Create_tbl()				{conn.Meta_tbl_create(Dbmeta_tbl_itm.New(tbl_name, flds));}
	public void Create_idx__page_id()	 {}//conn.Meta_idx_create(Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "page_id", fld_page_id));}
	public void Create_idx__link_score()	{conn.Meta_idx_create(Dbmeta_idx_itm.new_normal_by_tbl(tbl_name, "word_id__link_score", fld_word_id, Fld_link_score));}

	// v1
	public void Select_in(Cancelable cancelable, List_adp rv, List_adp words) {
		in_wkr.Init(words, rv);
		in_wkr.Select_in(cancelable, conn, 0, words.Count());
	}
	public static final Srch_link_tbl[] Ary_empty = new Srch_link_tbl[0];
	public static final String Fld_link_score = "link_score";
}
