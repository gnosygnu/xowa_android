package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.brys.*; import gplx.core.ios.*; import gplx.core.times.*;
import gplx.xowa.wikis.ctgs.*; import gplx.xowa.bldrs.wkrs.*; import gplx.xowa.bldrs.sqls.*;
public abstract class Xob_categorylinks_base extends Xob_sql_dump_base implements Sql_file_parser_cmd {
	private DateAdp_parser date_parser = DateAdp_parser.new_(); private Sql_file_parser sql_parser; Uca_trie trie; private Bry_bfr uca_bfr = Bry_bfr_.Reset(255);		
	public abstract Io_sort_cmd Make_sort_cmd(Sql_file_parser sql_parser);
	@Override public String Sql_file_name() {return "categorylinks";}
	@Override public void Cmd_bgn_hook(Xob_bldr bldr, Sql_file_parser parser) {
		this.sql_parser = parser;
		wiki.Html_mgr().Importing_ctgs_(Bool_.Y);
		parser.Fld_cmd_(this).Flds_req_(Fld_cl_from, Fld_cl_to, Fld_cl_timestamp, Fld_cl_collation, Fld_cl_sortkey, Fld_cl_type);
	}
	public void Exec(byte[] src, byte[] fld_key, int fld_idx, int fld_bgn, int fld_end, Bry_bfr file_bfr, Sql_file_parser_data data) {
		if		(Bry_.Eq(fld_key, Fld_cl_from))			cur_id = Bry_.To_int_or(src, fld_bgn, fld_end, -1);
		else if (Bry_.Eq(fld_key, Fld_cl_to))			cur_ctg = Bry_.Mid(src, fld_bgn, fld_end);
		else if (Bry_.Eq(fld_key, Fld_cl_collation))	cur_collation_is_uca = Bry_.Has_at_bgn(src, Collation_uca, fld_bgn, fld_end);
		else if (Bry_.Eq(fld_key, Fld_cl_timestamp)) {
			date_parser.Parse_iso8651_like(cur_modified_on, src, fld_bgn, fld_end);
			cur_date = fld_end - fld_bgn == 0	// ignore null dates added by ctg_v1
				? 0 : Int_flag_bldr_.To_int_date_short(cur_modified_on);
		}
		else if (Bry_.Eq(fld_key, Fld_cl_sortkey)) {
			int nl_pos = Bry_find_.Find_fwd(src, Byte_ascii.Nl, fld_bgn, fld_end);
			if (nl_pos != Bry_find_.Not_found)	// sortkey sometimes has format of "sortkey\ntitle"; EX: "WALES, JIMMY\nJIMMY WALES"; discard 2nd to conserve hard-disk space
				fld_end = nl_pos;
			cur_sortkey = Bry_.Mid(src, fld_bgn, fld_end);
		}
		else if (Bry_.Eq(fld_key, Fld_cl_type)) {
			byte char_0 = src[fld_bgn];
			switch (char_0) {
				case Byte_ascii.Ltr_f:
				case Byte_ascii.Ltr_p:	cur_tid = char_0; break;
				case Byte_ascii.Ltr_s:	cur_tid = Byte_ascii.Ltr_c; break; 
				default:				throw Err_.new_unhandled(char_0);
			}
			if (cur_collation_is_uca) {
				if (trie == null) {
					trie = new Uca_trie();
					trie.Init();
				}
				trie.Decode(uca_bfr, cur_sortkey, 0, cur_sortkey.length);
				cur_sortkey = uca_bfr.Len() == 0 ? Sortkey_space : uca_bfr.To_bry_and_clear();
			}
			fld_wtr.Bfr_(file_bfr);
			fld_wtr.Write_bry_escape_fld(cur_ctg);
			fld_wtr.Write_byte_fld(cur_tid);
			fld_wtr.Write_bry_escape_fld(cur_sortkey);
			fld_wtr.Write_int_base85_len5_fld(cur_id);
			fld_wtr.Write_int_base85_len5_fld(cur_date);
		}
	}	int cur_id = -1, cur_date = -1; byte[] cur_ctg = null, cur_sortkey = null; byte cur_tid = Byte_.Max_value_127; boolean cur_collation_is_uca; int[] cur_modified_on = new int[7];
	@Override public void Cmd_end() {
		Xobdc_merger.Basic(bldr.Usr_dlg(), dump_url_gen, temp_dir.GenSubDir("sort"), sort_mem_len, Xoctg_link_sql_sorter.Instance, Io_line_rdr_key_gen_.noop, Make_sort_cmd(sql_parser));
		wiki.Html_mgr().Importing_ctgs_(Bool_.N);
	}		
	private static final    byte[] Fld_cl_from = Bry_.new_a7("cl_from"), Fld_cl_to = Bry_.new_a7("cl_to"), Fld_cl_timestamp = Bry_.new_a7("cl_timestamp"), Fld_cl_collation = Bry_.new_a7("cl_collation"), Fld_cl_sortkey = Bry_.new_a7("cl_sortkey"), Fld_cl_type = Bry_.new_a7("cl_type");
	private static final    byte[] Collation_uca = Bry_.new_a7("uca"), Sortkey_space = new byte[] {Byte_ascii.Space};
}
class Xoctg_link_sql_sorter implements gplx.core.lists.ComparerAble {
	public int compare(Object lhsObj, Object rhsObj) {
		Io_sort_split_itm lhs = (Io_sort_split_itm)lhsObj, rhs = (Io_sort_split_itm)rhsObj;
		byte[] lhs_bry = lhs.Bfr(); int lhs_bgn = 0, lhs_end = lhs.Row_bgn() - 1;
		byte[] rhs_bry = rhs.Bfr(); int rhs_bgn = 0, rhs_end = rhs.Row_bgn() - 1;
		for (int i = 0; i < 3; i++) {
			lhs_bgn = lhs_end + 1; lhs_end = Bry_find_.Find_fwd(lhs_bry, Byte_ascii.Pipe, lhs_bgn);
			rhs_bgn = rhs_end + 1; rhs_end = Bry_find_.Find_fwd(rhs_bry, Byte_ascii.Pipe, rhs_bgn);
			int comp = Bry_.Compare(lhs_bry, lhs_bgn, lhs_end, rhs_bry, rhs_bgn, rhs_end);
			if (comp != CompareAble_.Same) return comp;
		}
		return CompareAble_.Same;
	}
	public static final    Xoctg_link_sql_sorter Instance = new Xoctg_link_sql_sorter(); Xoctg_link_sql_sorter() {}
}
