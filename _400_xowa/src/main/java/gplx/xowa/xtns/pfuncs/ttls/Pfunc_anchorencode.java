package gplx.xowa.xtns.pfuncs.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.core.brys.*; import gplx.core.btries.*; import gplx.langs.htmls.encoders.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.amps.*; import gplx.xowa.parsers.lnkes.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.lnkis.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_anchorencode extends Pf_func_base {	// EX: {{anchorencode:a b}} -> a+b
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public int Id() {return Xol_kwd_grp_.Id_url_anchorencode;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_anchorencode().Name_(name);}
	public static void Func_init(Xop_ctx ctx) {
		if (anchor_ctx != null) return;// NOTE: called by Scrib_uri
		encode_trie.Add(Byte_ascii.Colon, Bfr_arg_.New_byte(Byte_ascii.Colon));
		encode_trie.Add(Byte_ascii.Space, Bfr_arg_.New_byte(Byte_ascii.Underline));
		anchor_ctx = Xop_ctx.New__sub__reuse_page(ctx);
		anchor_ctx.Para().Enabled_n_();
		anchor_tkn_mkr = anchor_ctx.Tkn_mkr();
		anchor_parser = ctx.Wiki().Parser_mgr().Anchor_encoder();
	}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		if (anchor_ctx == null) Func_init(ctx);
		byte[] val_ary = Eval_argx(ctx, src, caller, self); if (val_ary == Bry_.Empty) return;
		Anchor_encode(val_ary, bfr, ctx.Wiki().Utl__bfr_mkr().Get_b512().Mkr_rls());
	}		
	public static void Anchor_encode(byte[] src, Bry_bfr bfr, Bry_bfr tmp_bfr) {
		Xop_root_tkn root = anchor_ctx.Tkn_mkr().Root(src);
		anchor_parser.Parse_wtxt_to_wdom(root, anchor_ctx, anchor_tkn_mkr, src, Xop_parser_.Doc_bgn_bos);
		// anchor_parser.Parse_page_tmpl(root, anchor_ctx, anchor_tkn_mkr, src);
		int subs_len = root.Subs_len();
		for (int i = 0; i < subs_len; i++) {
			Xop_tkn_itm sub = root.Subs_get(i);
			Tkn(src, sub, root, i, tmp_bfr);
		}
		byte[] unencoded = tmp_bfr.To_bry_and_clear();
		Gfo_url_encoder_.Id.Encode(tmp_bfr, unencoded);
		bfr.Add_bfr_and_clear(tmp_bfr);
	}
	private static void Tkn(byte[] src, Xop_tkn_itm sub, Xop_tkn_grp grp, int sub_idx, Bry_bfr tmp_bfr) {
		switch (sub.Tkn_tid()) {
			case Xop_tkn_itm_.Tid_lnke: Lnke(src, (Xop_lnke_tkn)sub, tmp_bfr); break;	// FUTURE: need to move number to lnke_tkn so that number will be correct/consistent? 
			case Xop_tkn_itm_.Tid_lnki: Lnki(src, (Xop_lnki_tkn)sub, tmp_bfr); break;
			case Xop_tkn_itm_.Tid_apos: break; // noop
			case Xop_tkn_itm_.Tid_xnde: Xnde(src, (Xop_xnde_tkn)sub, tmp_bfr); break;
			case Xop_tkn_itm_.Tid_html_ncr: tmp_bfr.Add_u8_int(((Xop_amp_tkn_num)sub).Val()); break;
			case Xop_tkn_itm_.Tid_html_ref: tmp_bfr.Add_u8_int(((Xop_amp_tkn_ent)sub).Char_int()); break;
			case Xop_tkn_itm_.Tid_tmpl_invk:
				Xot_invk_tkn invk_tkn = (Xot_invk_tkn)sub;
				Arg_itm_tkn name_tkn = invk_tkn.Name_tkn().Key_tkn();
				int name_ary_bgn = name_tkn.Src_bgn() + 1, name_ary_end = name_tkn.Src_end();
				byte[] name_ary = Bry_.Mid(src, name_ary_bgn, name_ary_end);	// + 1 to skip :
				int name_ary_len = name_ary_end - name_ary_bgn; 
				if (name_ary_len > 0 && name_ary[0] == Byte_ascii.Colon)			// has initial colon; EX: {{:a}
					tmp_bfr.Add_mid(name_ary, 1, name_ary_len);						// 1 to skip initial colon
				else																// regular tmpl; EX: {{a}}
					tmp_bfr.Add(anchor_ctx.Wiki().Ns_mgr().Ns_template().Gen_ttl(name_ary));
				break;
			default: tmp_bfr.Add_mid(src, sub.Src_bgn_grp(grp, sub_idx), sub.Src_end_grp(grp, sub_idx)); break;
		}		
	}
	private static void Lnke(byte[] src, Xop_lnke_tkn lnke, Bry_bfr tmp_bfr) {
		int subs_len = lnke.Subs_len();
		for (int i = 0; i < subs_len; i++) {
			Xop_tkn_itm lnke_sub = lnke.Subs_get(i);
			tmp_bfr.Add_mid(src, lnke_sub.Src_bgn_grp(lnke, i), lnke_sub.Src_end_grp(lnke, i));
		}
	}
	private static void Lnki(byte[] src, Xop_lnki_tkn lnki, Bry_bfr tmp_bfr) {
		int src_end = lnki.Src_end();
		int trg_end = lnki.Trg_tkn().Src_end();
		
		if (trg_end == src_end - Xop_tkn_.Lnki_end_len) {		// only trg
			int trg_bgn = lnki.Trg_tkn().Src_bgn();
			if (lnki.Ttl().ForceLiteralLink()) ++trg_bgn;		// literal link; skip colon; EX: [[:a]] -> a
			tmp_bfr.Add_mid(src, trg_bgn, trg_end);			
		}
		else {
			tmp_bfr.Add_mid(src, trg_end + 1, src_end - Xop_tkn_.Lnki_end_len); //+1 is len of pipe
		}
	}
	private static void Xnde(byte[] src, Xop_xnde_tkn xnde, Bry_bfr tmp_bfr) {
		int subs_len = xnde.Subs_len();
		for (int i = 0; i < subs_len; i++) {
			Tkn(src, xnde.Subs_get(i), xnde, i, tmp_bfr);
		}		
	}
	private static Btrie_fast_mgr encode_trie = Btrie_fast_mgr.cs();
	private static Xop_ctx anchor_ctx;  static Xop_tkn_mkr anchor_tkn_mkr;
	private static Xop_parser anchor_parser; 
}
