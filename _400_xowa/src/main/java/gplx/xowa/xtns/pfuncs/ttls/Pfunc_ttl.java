package gplx.xowa.xtns.pfuncs.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_ttl extends Pf_func_base {
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] val_dat_ary = Eval_argx_or_null(ctx, src, caller, self, this.Name()); 
		if (val_dat_ary == Bry_.Empty) return; // if argx is empty, return EX: {{PAGENAME:}}; DATE:2013-02-20
		Xoa_ttl ttl = val_dat_ary == null ? ctx.Page().Ttl() : Xoa_ttl.Parse(ctx.Wiki(), val_dat_ary);
		if (ttl == null) return;
	    switch (id) {
			case Xol_kwd_grp_.Id_ttl_page_txt: bfr.Add(ttl.Page_txt()); break;
			case Xol_kwd_grp_.Id_ttl_page_url: bfr.Add(ttl.Page_url()); break;
			case Xol_kwd_grp_.Id_ttl_full_txt: bfr.Add(ttl.Full_txt_w_ttl_case()); break;
			case Xol_kwd_grp_.Id_ttl_full_url: bfr.Add(ttl.Full_url()); break;
			case Xol_kwd_grp_.Id_ttl_leaf_txt: bfr.Add(ttl.Leaf_txt()); break;
			case Xol_kwd_grp_.Id_ttl_leaf_url: bfr.Add(ttl.Leaf_url()); break;
			case Xol_kwd_grp_.Id_ttl_base_txt: bfr.Add(ttl.Base_txt()); break;
			case Xol_kwd_grp_.Id_ttl_base_url: bfr.Add(ttl.Base_url()); break;
			case Xol_kwd_grp_.Id_ttl_subj_txt: bfr.Add(ttl.Subj_txt()); break;
			case Xol_kwd_grp_.Id_ttl_subj_url: bfr.Add(ttl.Subj_url()); break;
			case Xol_kwd_grp_.Id_ttl_talk_txt: bfr.Add(ttl.Talk_txt()); break;
			case Xol_kwd_grp_.Id_ttl_talk_url: bfr.Add(ttl.Talk_url()); break;
			case Xol_kwd_grp_.Id_ns_num: bfr.Add_int_variable(ttl.Ns().Id()); break;
			case Xol_kwd_grp_.Id_ns_txt: bfr.Add(ttl.Ns().Name_ui()); break;
			case Xol_kwd_grp_.Id_ns_url: bfr.Add(ttl.Ns().Name_enc()); break;
			case Xol_kwd_grp_.Id_ns_subj_txt: bfr.Add(ctx.Wiki().Ns_mgr().Ords_get_at(ttl.Ns().Ord_subj_id()).Name_ui()); break;
			case Xol_kwd_grp_.Id_ns_subj_url: bfr.Add(ctx.Wiki().Ns_mgr().Ords_get_at(ttl.Ns().Ord_subj_id()).Name_enc()); break;
			case Xol_kwd_grp_.Id_ns_talk_txt: bfr.Add(ctx.Wiki().Ns_mgr().Ords_get_at(ttl.Ns().Ord_talk_id()).Name_ui()); break;
			case Xol_kwd_grp_.Id_ns_talk_url: bfr.Add(ctx.Wiki().Ns_mgr().Ords_get_at(ttl.Ns().Ord_talk_id()).Name_enc()); break;
		}
	}
	public Pfunc_ttl(int id) {this.id = id;}
	@Override public int Id() {return id;} private int id;
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_ttl(id).Name_(name);}
	public static final    Pfunc_ttl Instance = new Pfunc_ttl(-1);
}
