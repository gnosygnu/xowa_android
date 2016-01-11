package gplx.xowa.apps.fmtrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*;
import gplx.core.brys.fmtrs.*;
public class Xoa_fmtr_itm implements GfoInvkAble {
	public Xoa_fmtr_itm(Xoae_app app) {this.app = app;} private Xoae_app app;
	public String Src() {return src;} public Xoa_fmtr_itm Src_(String v) {this.src = v; return this;} private String src;
	public byte[] Fmt() {return fmt;} public Xoa_fmtr_itm Fmt_(byte[] v) {this.fmt = v; return this;} private byte[] fmt;
	public Object Sorter() {
		GfoInvkAble src_invk = (GfoInvkAble)app.Gfs_mgr().Run_str(src);
		return GfoInvkAble_.InvkCmd(src_invk, Invk_sorter);
	}
	public String Run() {
		GfoInvkAble src_invk = (GfoInvkAble)app.Gfs_mgr().Run_str(src);
		int len = Int_.cast(GfoInvkAble_.InvkCmd(src_invk, Invk_len));
		Bry_bfr bfr = Bry_bfr.new_();
		Bfmtr_eval_invk eval_mgr = new Bfmtr_eval_invk(app);
		Bry_fmtr fmtr = Bry_fmtr.new_bry_(fmt).Eval_mgr_(eval_mgr);  
		for (int i = 0; i < len; i++) {
			GfoInvkAble itm_invk = (GfoInvkAble)GfoInvkAble_.InvkCmd_val(src_invk, Invk_get_at, i);
			eval_mgr.Invk_(itm_invk);
			fmtr.Bld_bfr(bfr, Bry_.Ary_empty);
		}
		return bfr.To_str_and_clear();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_src))					return src;
		else if	(ctx.Match(k, Invk_src_)) 					src = m.ReadStr("v"); 
		else if	(ctx.Match(k, Invk_fmt)) 					return String_.new_u8(fmt);
		else if	(ctx.Match(k, Invk_fmt_)) 					fmt = m.ReadBry("v"); 
		else if	(ctx.Match(k, Invk_sorter)) 				return this.Sorter();
		else if	(ctx.Match(k, Invk_run)) 					return Run(); 
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}
	private static final String Invk_src = "src", Invk_src_ = "src_", Invk_fmt = "fmt", Invk_fmt_ = "fmt_"		
	, Invk_run = "run"
	;
	public static final String Invk_get_at = "get_at", Invk_len = "len"
	, Invk_sorter = "sorter"
	;
}
class Bfmtr_eval_invk implements Bry_fmtr_eval_mgr {
	public Bfmtr_eval_invk(Xoae_app app) {this.app = app;} private Xoae_app app;
	public Bfmtr_eval_invk Invk_(GfoInvkAble invk) {this.invk = invk; return this;} private GfoInvkAble invk;
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} private boolean enabled = true;
	public byte[] Eval(byte[] cmd) {
		Object rslt = app.Gfs_mgr().Run_str_for(invk, String_.new_u8(cmd));
		return Bry_.new_u8(Object_.Xto_str_strict_or_null_mark(rslt));
	}
}
