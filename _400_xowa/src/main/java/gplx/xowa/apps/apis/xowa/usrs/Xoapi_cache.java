package gplx.xowa.apps.apis.xowa.usrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.core.ios.*; import gplx.xowa.files.caches.*;
public class Xoapi_cache implements Gfo_invk {
	private Xou_cache_mgr cache_mgr;
	public void Init_by_app(Xoa_app app) {this.cache_mgr = app.User().User_db_mgr().Cache_mgr();}
	private String Info() {
		cache_mgr.Page_bgn();
		Bry_bfr bfr = Bry_bfr_.New_w_size(255);
		Keyval[] ary = cache_mgr.Info();
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			Keyval kv = ary[i];
			bfr.Add_str_a7(kv.Key()).Add_str_a7(": ").Add_str_u8(kv.Val_to_str_or_empty()).Add_byte_nl();
		}
		return bfr.To_str_and_clear();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_fsys_size_min)) 						return cache_mgr.Fsys_size_min() / Io_mgr.Len_mb;
		else if	(ctx.Match(k, Invk_fsys_size_min_)) 					cache_mgr.Fsys_size_min_(Io_size_.To_long_by_msg_mb(m, cache_mgr.Fsys_size_min()));
		else if	(ctx.Match(k, Invk_fsys_size_max)) 						return cache_mgr.Fsys_size_max() / Io_mgr.Len_mb;
		else if	(ctx.Match(k, Invk_fsys_size_max_)) 					cache_mgr.Fsys_size_max_(Io_size_.To_long_by_msg_mb(m, cache_mgr.Fsys_size_max()));
		else if	(ctx.Match(k, Invk_info)) 								return Info();
		else if	(ctx.Match(k, Invk_reduce_to_min)) 						cache_mgr.Reduce(cache_mgr.Fsys_size_min());
		else if	(ctx.Match(k, Invk_reduce_to_zero)) 					cache_mgr.Reduce(0);
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String 
	  Invk_fsys_size_min		= "fsys_size_min"		, Invk_fsys_size_min_		= "fsys_size_min_"
	, Invk_fsys_size_max		= "fsys_size_max"		, Invk_fsys_size_max_		= "fsys_size_max_"
	, Invk_info					= "info"
	, Invk_reduce_to_min		= "reduce_to_min"		, Invk_reduce_to_zero		= "reduce_to_zero"
	;
}
