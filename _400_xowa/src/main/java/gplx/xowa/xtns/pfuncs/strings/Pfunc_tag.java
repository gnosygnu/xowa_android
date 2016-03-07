package gplx.xowa.xtns.pfuncs.strings; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.htmls.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_tag extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_misc_tag;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_tag().Name_(name);}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] tag_name = Eval_argx(ctx, src, caller, self); if (tag_name.length == 0) return;
		int args_len = self.Args_len();
		Xoae_app app = ctx.App();
		Bry_bfr tmp = app.Utl__bfr_mkr().Get_b512();			
		try {
			int tag_idx = ++tag__next_id;
			Xop_xnde_tag tag = (Xop_xnde_tag)ctx.Xnde_tag_regy().Get_trie(ctx.Xnde_names_tid()).Match_exact(tag_name, 0, tag_name.length);
			boolean tag_is_ref = tag != null && tag.Id() == Xop_xnde_tag_.Tid__ref;
			if (tag_is_ref)	// <ref>; add <xtag_bgn> to handle nested refs; PAGE:en.w:Battle_of_Midway; DATE:2014-06-27
				tmp.Add(Xtag_bgn_lhs).Add_int_pad_bgn(Byte_ascii.Num_0, 10, tag_idx).Add(Xtag_rhs);				
			tmp.Add_byte(Byte_ascii.Lt).Add(tag_name);
			if (args_len > 1) {
				for (int i = 1; i < args_len; i++) {
					byte[] arg = Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, args_len, i);	// NOTE: must evaluate arg; don't try to parse arg_tkn's key / val separately; EX:{{#tag:pre|a|{{#switch:a|a=id}}=c}}
					if (arg.length == 0) continue;	// skip empty atrs
					tmp.Add_byte(Byte_ascii.Space);
					Pfunc_tag_kv_bldr.Add_arg_as_html_atr(arg, tmp);
				}
			}
			tmp.Add_byte(Byte_ascii.Gt);
			if (args_len > 0)	// TODO: trim should not be called on content; WHEN: adding src[] back to tmpl_eval  
				tmp.Add(Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, args_len, 0));
			tmp.Add_byte(Byte_ascii.Lt).Add_byte(Byte_ascii.Slash).Add(tag_name).Add_byte(Byte_ascii.Gt);
			if (tag_is_ref)	// <ref>; add <xtag_end> to handle nested refs; PAGE:en.w:Battle_of_Midway; DATE:2014-06-27
				tmp.Add(Xtag_end_lhs).Add_int_pad_bgn(Byte_ascii.Num_0, 10, tag_idx).Add(Xtag_rhs);
			bfr.Add_bfr_and_clear(tmp);
		}
		finally {tmp.Mkr_rls();}
	}
	public static final int
	  Xtag_len = 27	// <xtag_bgn id='1234567890'/>
	, Xtag_bgn = 14 // <xtag_bgn id='
	, Id_len = 10
	;
	public static final byte[]
	  Xtag_bgn_lhs = Bry_.new_a7("<xtag_bgn id='")
	, Xtag_end_lhs = Bry_.new_a7("<xtag_end id='")
	, Xtag_rhs = Bry_.new_a7("'/>")
	;
	private static int tag__next_id = 0;	// NOTE:must be app-level variable, not page-level, b/c pre-compiled templates can reserve tag #s; PAGE:de.s:Seite:NewtonPrincipien.djvu/465 DATE:2015-02-03
}
class Pfunc_tag_kv_bldr {
	public int Key_bgn() {return key_bgn;} private int key_bgn;
	public int KeyEnd() {return key_end;} private int key_end;
	public Pfunc_tag_kv_bldr Key_rng_(int bgn, int end) {key_bgn = bgn; key_end = end; return this;}
	public int Val_bgn() {return val_bgn;} private int val_bgn;
	public int Val_end() {return val_end;} private int val_end;
	public Pfunc_tag_kv_bldr Val_rng_(int bgn, int end) {val_bgn = bgn; val_end = end; return this;}
	public boolean Valid() {
		return key_bgn != -1 && key_end != -1 && val_bgn != -1 && val_end != -1 && key_bgn <= key_end && val_bgn <= val_end;
	}
	public void Clear() {
		key_bgn = key_end = val_bgn = val_end = -1;
	}
	public static void Add_arg_as_html_atr(byte[] src, Bry_bfr tmp) {
		synchronized (kv_bldr) {
			ParseKeyVal(src, kv_bldr);
			if (kv_bldr.Val_bgn() == -1) return;	// ignore atrs with empty vals: EX:{{#tag:ref||group=}} PAGE:ru.w:Колчак,_Александр_Васильевич DATE:2014-07-03
			if (kv_bldr.Key_bgn() != -1)
				tmp.Add(Bry_.Mid(src, kv_bldr.Key_bgn(), kv_bldr.KeyEnd()));
			if (kv_bldr.Val_bgn() != -1) {
				if (kv_bldr.Key_bgn() != -1) {
					tmp.Add_byte(Byte_ascii.Eq);
				}
				tmp.Add_byte(Byte_ascii.Quote);
				tmp.Add(Bry_.Mid(src, kv_bldr.Val_bgn(), kv_bldr.Val_end()));
				tmp.Add_byte(Byte_ascii.Quote);
			}
			kv_bldr.Clear();
		}
	}
	private static void ParseKeyVal(byte[] src, Pfunc_tag_kv_bldr kv_bldr) {
		kv_bldr.Clear();	// do not forget to clear; DATE:2014-07-20
		int itm_bgn = -1, itm_end = -1, src_len = src.length;
		boolean mode_is_key = true;
		for (int i = 0; i < src_len; i++) {
			byte b = src[i];
			switch (b) {
				case Byte_ascii.Eq:
					if (mode_is_key) {
						mode_is_key = false;
						if (itm_end == -1) itm_end = i;
						kv_bldr.Key_rng_(itm_bgn, itm_end);
						itm_bgn = itm_end = -1;
					}					
					break;
				case Byte_ascii.Quote:
				case Byte_ascii.Apos:	// NOTE: quotes cannot be escaped; regx takes first two quotes; REF:MW:CoreParserFunctions.php|tagObj
					if (itm_bgn == -1)
						itm_bgn = i + 1;
					else if (itm_end == -1)
						itm_end = i;
					break;
				case Byte_ascii.Space: case Byte_ascii.Tab: case Byte_ascii.Nl:// NOTE: do not need to handle ws, b/c argBldr will trim it EX: {{#tag|a| b = c }}; " b " and " c " are automatically trimmed
					break;
				default:
					if		(itm_bgn == -1)	itm_bgn = i;
					break;
			}
		}
		if (itm_end == -1) itm_end = src_len;
		kv_bldr.Val_rng_(itm_bgn, itm_end);
	}
	private static Pfunc_tag_kv_bldr kv_bldr = new Pfunc_tag_kv_bldr();
}
