package gplx.xowa.xtns.wbases.pfuncs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.core.envs.*;
import gplx.core.primitives.*;
import gplx.xowa.parsers.logs.*; import gplx.xowa.xtns.pfuncs.*; import gplx.xowa.xtns.wbases.core.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Wdata_pf_property extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_property;}
	@Override public Pf_func New(int id, byte[] name) {return new Wdata_pf_property().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {// EX: {{#property:p123|}}
		synchronized (this) { // LOCK: must synchronized b/c bfr will later be set as member variable; DATE:2016-07-06
			// init
			byte[] pid_ttl = Eval_argx(ctx, src, caller, self);
			Xop_log_property_wkr property_wkr = ctx.Xtn__wikidata__property_wkr();
			long log_time_bgn = 0;
			if (property_wkr != null) {
				log_time_bgn = System_.Ticks();
				if (!property_wkr.Eval_bgn(ctx.Page(), pid_ttl)) return;
			}
			Xoae_app app = ctx.App();
			Xowe_wiki wiki = ctx.Wiki();
			Xoa_ttl ttl = ctx.Page().Ttl();
			Wdata_wiki_mgr wdata_mgr = app.Wiki_mgr().Wdata_mgr(); if (!wdata_mgr.Enabled()) return;

			// get pid_int; EX: {{#property:p123}} -> 123
			int pid_int = Wdata_pf_property.Parse_pid(app.Utl_num_parser(), pid_ttl); // parse "p123" to "123"
			if (pid_int == Wdata_wiki_mgr.Pid_null)	{ // pid_ttl is name; EX: {{#property:road_map}}
				pid_int = wdata_mgr.Pid_mgr.Get_or_null(wiki.Wdata_wiki_lang(), pid_ttl);
				if (pid_int == Wdata_wiki_mgr.Pid_null) {
					Print_self(app.Usr_dlg(), bfr, src, self, "prop_not_found", "prop id not found: ~{0} ~{1} ~{2}", wiki.Domain_str(), ttl.Page_db_as_str(), pid_ttl); 
					return;
				}
			}

			// get doc from args; EX:{{#property:p123}} -> "current_page"; EX:{{#property:p123|of=Earth}} -> "Q2"; {{#property:p123|q=q2}} -> "Q2"; {{#property:p123|from=p321}} -> "Property:P321"
			Wdata_pf_property_data doc_data = Wdata_pf_property_data.Parse(ctx, src, caller, self, this);
			Wdata_doc doc = Wdata_pf_property_.Get_doc(wdata_mgr, wiki, ttl, doc_data);
			if (doc == null) return; // NOTE: some pages will not have a qid; EX: "Some_unknown_page" will not have a qid in wikidata; if no qid, then all {{#property:p###}} will have no prop_val

			// get val based on pid and doc; EX: {{#property:p123|of=Earth}} -> doc=Q2; pid=123 -> "value of p123 in Q2"
			Wbase_claim_grp claim_grp = doc.Claim_list_get(pid_int);
			if (claim_grp == null) return;// NOTE: some props may not exist; EX: "Some_known_page" has a qid of 123 but does not have pid 345 required by {{#property:P345|q=123}}
			wdata_mgr.Resolve_to_bfr(bfr, claim_grp, wiki.Wdata_wiki_lang()); // NOTE: was ctx.Page().Lang().Key_bry(), but fails in simplewiki; DATE:2013-12-02
			if (property_wkr != null) property_wkr.Eval_end(ctx.Page(), pid_ttl, log_time_bgn);
		}
	}
	public static int Parse_pid(Gfo_number_parser num_parser, byte[] bry) {
		int bry_len = bry.length;
		if (bry_len < 2) return Wdata_wiki_mgr.Pid_null;	// must have at least 2 chars; p#
		byte b_0 = bry[0];
		if (b_0 != Byte_ascii.Ltr_p && b_0 != Byte_ascii.Ltr_P)	return Wdata_wiki_mgr.Pid_null;
		num_parser.Parse(bry, 1, bry_len);
		return num_parser.Has_err() ? Wdata_wiki_mgr.Pid_null : num_parser.Rv_as_int();
	}
	public static void Print_self(Gfo_usr_dlg usr_dlg, Bry_bfr bfr, byte[] src, Xot_invk self, String warn_cls, String warn_fmt, Object... args) {
		bfr.Add_mid(src, self.Src_bgn(), self.Src_end());
		usr_dlg.Warn_many(GRP_KEY, warn_cls, warn_fmt, args);
	}
	public static void Print_empty(Gfo_usr_dlg usr_dlg, String warn_cls, String warn_fmt, Object... args) {
		usr_dlg.Warn_many(GRP_KEY, warn_cls, warn_fmt, args);
	}
	private static final    String GRP_KEY = "xowa.xtns.wdata.property";
}
class Wdata_pf_property_ {
	public static Wdata_doc Get_doc(Wdata_wiki_mgr wdata_mgr, Xowe_wiki wiki, Xoa_ttl ttl, Wdata_pf_property_data data) {
		if		(Bry_.Len_gt_0(data.Q))		return wdata_mgr.Doc_mgr.Get_by_bry_or_null(data.Q);
		else if	(Bry_.Len_gt_0(data.From))	return wdata_mgr.Doc_mgr.Get_by_xid_or_null(data.From);	// NOTE: by_xid b/c Module passes just "p1" not "Property:P1"
		else if (Bry_.Len_gt_0(data.Of)) {
			Xoa_ttl of_ttl = Xoa_ttl.Parse(wiki, data.Of); if (of_ttl == null) return null;
			byte[] qid = wdata_mgr.Qid_mgr.Get_or_null(wiki, of_ttl); if (qid == null) return null;	// NOTE: for now, use wiki.Lang_key(), not page.Lang()
			return wdata_mgr.Doc_mgr.Get_by_bry_or_null(qid);
		}
		else							return wdata_mgr.Doc_mgr.Get_by_ttl_or_null(wiki, ttl);
	}
}
