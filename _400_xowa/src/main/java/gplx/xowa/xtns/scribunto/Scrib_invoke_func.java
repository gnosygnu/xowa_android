package gplx.xowa.xtns.scribunto; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.brys.fmtrs.*; import gplx.core.envs.*;
import gplx.langs.htmls.*;
import gplx.xowa.langs.kwds.*; import gplx.xowa.langs.msgs.*;
import gplx.xowa.wikis.nss.*;
import gplx.xowa.htmls.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.logs.*; import gplx.xowa.parsers.tmpls.*;
import gplx.xowa.xtns.pfuncs.*;
public class Scrib_invoke_func extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_invoke;}
	@Override public Pf_func New(int id, byte[] name) {return new Scrib_invoke_func().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {// {{#invoke:mod_name|prc_name|prc_args...}}
		Xowe_wiki wiki = ctx.Wiki();
		byte[] mod_name = Eval_argx(ctx, src, caller, self);
		if (Bry_.Len_eq_0(mod_name)) {Error(bfr, wiki.Msg_mgr(), Err_mod_missing); return;}		// EX: "{{#invoke:}}"
		int args_len = self.Args_len();
		byte[] fnc_name = Pf_func_.Eval_arg_or(ctx, src, caller, self, args_len, 0, null);
		Xop_log_invoke_wkr invoke_wkr = ctx.Xtn__scribunto__invoke_wkr();
		long log_time_bgn = 0;
		if (invoke_wkr != null) {
			log_time_bgn = Env_.TickCount();
			if (!invoke_wkr.Eval_bgn(ctx.Page(), mod_name, fnc_name)) return;
		}
		Scrib_core core = wiki.Parser_mgr().Scrib().Core();
		if (core == null) {
			synchronized (this) {
				core = wiki.Parser_mgr().Scrib().Core_init(ctx);
				core.Init();
				core.When_page_changed(ctx.Page());
			}
		}
		byte[] mod_raw = null;
		Scrib_lua_mod mod = core.Mods_get(mod_name);
		if (mod == null) {
			Xow_ns module_ns = wiki.Ns_mgr().Ids_get_or_null(Xow_ns_.Tid__module);
			Xoa_ttl mod_ttl = Xoa_ttl.Parse(wiki, Bry_.Add(module_ns.Name_db_w_colon(), mod_name));
			mod_raw = wiki.Cache_mgr().Page_cache().Get_or_load_as_src(mod_ttl);
			if (mod_raw == null) {Error(bfr, wiki.Msg_mgr(), Err_mod_missing); return;} // EX: "{{#invoke:missing_mod}}"
		}
		else
			mod_raw = mod.Text_bry();
		if (!core.Enabled()) {bfr.Add_mid(src, self.Src_bgn(), self.Src_end()); return;}
		try {
			core.Invoke(wiki, ctx, src, caller, self, bfr, mod_name, mod_raw, fnc_name);
			if (invoke_wkr != null)
				invoke_wkr.Eval_end(ctx.Page(), mod_name, fnc_name, log_time_bgn);
		}
		catch (Throwable e) {//#<>Exception~Throwable
			Err err = Err_.cast_or_make(e);
			Error(bfr, wiki.Msg_mgr(), err);
			bfr.Add(Gfh_tag_.Comm_bgn).Add_str_u8(err.To_str__full()).Add(Gfh_tag_.Comm_end);
			Scrib_err_filter_mgr err_filter_mgr = invoke_wkr == null ? null : invoke_wkr.Err_filter_mgr();
			if (	err_filter_mgr == null																				// no err_filter_mgr defined;
				||	err_filter_mgr.Count_eq_0(	)																		// err_filter_mgr exists, but no definitions
				||	!err_filter_mgr.Match(String_.new_u8(mod_name), String_.new_u8(fnc_name), err.To_str__msg_only()))	// NOTE: must be To_str__msg_only; err_filter_mgr has defintion and it doesn't match current; print warn; DATE:2015-07-24
				ctx.App().Usr_dlg().Warn_many("", "", "invoke failed: ~{0} ~{1} ~{2}", ctx.Page().Ttl().Raw(), String_.new_u8(src, self.Src_bgn(), self.Src_end()), err.To_str__log());
			wiki.Parser_mgr().Scrib().Terminate_when_page_changes_y_();	// NOTE: terminate core when page changes; not terminating now, else page with many errors will be very slow due to multiple remakes of core; PAGE:th.d:all; DATE:2014-10-03
		}
	}
	public static void Error(Bry_bfr bfr, Xow_msg_mgr msg_mgr, Err err) {Error(bfr, msg_mgr, Err_.cast_or_make(err).To_str__top_wo_args());}// NOTE: must use "short" error message to show in wikitext; DATE:2015-07-27
	public static void Error(Bry_bfr bfr, Xow_msg_mgr msg_mgr, String error) {
		byte[] script_error_msg = msg_mgr.Val_by_id(Xol_msg_itm_.Id_scribunto_parser_error);
		error_fmtr.Bld_bfr_many(bfr, script_error_msg, error);
	}
	private static final    Bry_fmtr error_fmtr = Bry_fmtr.new_("<strong class=\"error\"><span class=\"scribunto-error\" id=\"mw-scribunto-error-0\">~{0}: ~{1}</span></strong>");	// NOTE: must be "error" not 'error'; iferror checks for quote not apos; DATE:2015-09-17
	public static final String Err_mod_missing = "No such module";
}
