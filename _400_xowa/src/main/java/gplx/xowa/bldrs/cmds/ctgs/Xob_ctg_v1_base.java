package gplx.xowa.bldrs.cmds.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.cmds.*;
import gplx.core.primitives.*; import gplx.core.btries.*; import gplx.core.flds.*; import gplx.core.ios.*; import gplx.xowa.wikis.tdbs.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.bldrs.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.wikis.data.tbls.*;
public abstract class Xob_ctg_v1_base extends Xob_itm_dump_base implements Xobd_parser_wkr, Gfo_invk {
	protected Xob_ctg_v1_base() {}	// TEST:needed for fxt
	public Xob_ctg_v1_base Ctor(Xob_bldr bldr, Xowe_wiki wiki) {this.Cmd_ctor(bldr, wiki); return this;}
	public abstract String Wkr_key();
	public abstract Io_sort_cmd Make_sort_cmd();
	public Ordered_hash Wkr_hooks() {return wkr_hooks;} private Ordered_hash wkr_hooks = Ordered_hash_.New_bry();
	public void Wkr_bgn(Xob_bldr bldr) {
		this.Init_dump(this.Wkr_key(), wiki.Tdb_fsys_mgr().Site_dir().GenSubDir(Xotdb_dir_info_.Name_category));
		Bry_bfr tmp_bfr = wiki.Utl__bfr_mkr().Get_b512();
		Xol_lang_itm lang = wiki.Lang();
		wkr_hooks_add(tmp_bfr, lang.Ns_names());
		wkr_hooks_add(tmp_bfr, lang.Ns_aliases());
		wkr_hooks_add(tmp_bfr, Xow_ns_canonical_.Ary);
		tmp_bfr.Mkr_rls();
		fld_wtr.Bfr_(dump_bfr);
	}
	public int Wkr_run(Xowd_page_itm page, byte[] src, int src_len, int bgn, int end) {
		int ttl_bgn = end, ttl_end = -1;
		int pos = end;
		while (true) {
			if (pos == src_len) {		// fail: EOS
				return end;
			}
			Object o = trie.Match_bgn(src, pos, src_len);
			if (o != null) {
				Btrie_itm_stub stub = (Btrie_itm_stub)o;
				byte[] bry = stub.Val();
				switch (stub.Tid()) {
					case Tid_brack_end: case Tid_pipe:
						ttl_bgn = Move_fwd_while_space(src, src_len, ttl_bgn);
						ttl_end = Move_bwd_while_space(src, src_len, pos - 1);
						if (ttl_end > ttl_bgn)	// NOTE: ignore examples like [[Category: ]]
							Process_ctg(page, src, src_len, ttl_bgn, ttl_end);
						break;
					case Tid_brack_bgn:	// fail: [[ is invalid
						return pos;
					case Tid_nl:		// fail: \n is invalid
						return pos;
				}
				return pos + bry.length;
			}
			++pos;
		}
	}
	@gplx.Virtual public void Process_ctg(Xowd_page_itm page, byte[] src, int src_len, int bgn, int end) {
		Process_ctg_row(fld_wtr, dump_fil_len, dump_url_gen, page.Id(), src, src_len, bgn, end);
	}
	public static void Process_ctg_row(Gfo_fld_wtr fld_wtr, int dump_fil_len, Io_url_gen dump_url_gen, int page_id, byte[] src, int src_len, int bgn, int end) {
		int len = end - bgn;
		Bry_bfr dump_bfr = fld_wtr.Bfr();
		if (dump_bfr.Len() + row_fixed_len + len > dump_fil_len) Io_mgr.Instance.AppendFilBfr(dump_url_gen.Nxt_url(), dump_bfr);
		byte[] ttl = Bry_.Mid(src, bgn, end);
		Bry_.Replace_reuse(ttl, Byte_ascii.Space, Byte_ascii.Underline);
		fld_wtr.Write_bry_escape_fld(ttl).Write_int_base85_len5_row(page_id);
	}
	public void Wkr_end() {
		this.Term_dump(this.Make_sort_cmd());
		if (delete_temp) Io_mgr.Instance.DeleteDirDeep(temp_dir);
	}
	private Gfo_fld_wtr fld_wtr = Gfo_fld_wtr.xowa_();
	Btrie_fast_mgr trie = Btrie_fast_mgr.cs().Add_stub(Tid_brack_end, "]]").Add_stub(Tid_pipe, "|").Add_stub(Tid_nl, "\n").Add_stub(Tid_brack_bgn, "[[");
	static final int row_fixed_len = 5 + 1 + 1;	// 5=rowId; 1=|; 1=\n
	List_adp category_list = List_adp_.New(); Int_obj_ref cur_pos = Int_obj_ref.New_zero();
	static final byte Tid_eos = 0, Tid_brack_end = 1, Tid_pipe = 2, Tid_nl = 3, Tid_brack_bgn = 4;
	private static int Move_fwd_while_space(byte[] src, int src_len, int pos) {
		while (true) {
			if (pos == src_len) return pos;
			switch (src[pos]) {
				case Byte_ascii.Space:	break;
				default:				return pos;
			}
			++pos;
		}
	}
	private static int Move_bwd_while_space(byte[] src, int src_len, int pos) {
		while (true) {
			if (pos == -1) return 0;
			switch (src[pos]) {
				case Byte_ascii.Space:	break;
				default:				return pos + 1;
			}
			--pos;
		}
	}
	private void wkr_hooks_add(Bry_bfr tmp_bfr, Xow_ns[] ary) {
		int len = ary.length;
		for (int i = 0; i < len; i++) {
			Xow_ns ns = ary[i];
			if (ns.Id_is_ctg()) wkr_hooks_add(tmp_bfr, ns.Name_db());
		}
	}
	private void wkr_hooks_add(Bry_bfr tmp_bfr, Xol_ns_grp ns_grp) {
		int len = ns_grp.Len();
		for (int i = 0; i < len; i++) {
			Xow_ns ns = ns_grp.Get_at(i);
			if (ns.Id_is_ctg()) wkr_hooks_add(tmp_bfr, ns.Name_db());
		}
	}
	private void wkr_hooks_add(Bry_bfr tmp_bfr, byte[] word) {
		tmp_bfr.Add_byte(Byte_ascii.Brack_bgn).Add_byte(Byte_ascii.Brack_bgn).Add(word).Add_byte(Byte_ascii.Colon);
		byte[] key = tmp_bfr.To_bry_and_clear();
		if (!wkr_hooks.Has(key)) wkr_hooks.Add(key, key);
	}	
	static final String GRP_KEY = "xowa.bldr.make_ctg";
}
