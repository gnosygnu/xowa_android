package gplx.xowa.xtns.pfuncs.wikis; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
import gplx.xowa.wikis.metas.*;
public class Pfunc_wiki_stats extends Pf_func_base {
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] argx = Eval_argx(ctx, src, caller, self);
		boolean raw = false;			
		if (argx.length == 1) {
			byte argx_0 = argx[0];
			switch (argx_0) {case Byte_ascii.Ltr_R: case Byte_ascii.Ltr_r: raw = true; break;}
		}
		Xow_wiki_stats stats = ctx.Wiki().Stats();
		int v = 0;
	    switch (id) {
			case Xol_kwd_grp_.Id_num_pages:		v = stats.NumPages(); break;
			case Xol_kwd_grp_.Id_num_articles:	v = stats.NumArticles(); break;
			case Xol_kwd_grp_.Id_num_files:		v = stats.NumFiles(); break;
			case Xol_kwd_grp_.Id_num_edits:		v = stats.NumEdits(); break;
			case Xol_kwd_grp_.Id_num_views:		v = stats.NumViews(); break;
			case Xol_kwd_grp_.Id_num_users:		v = stats.NumUsers(); break;
			case Xol_kwd_grp_.Id_num_users_active: v = stats.NumUsersActive(); break;
			case Xol_kwd_grp_.Id_num_admins:	v = stats.NumAdmins(); break;
			default: throw Err_.new_unhandled(id);
		}
		if (raw)
			bfr.Add_int_variable(v);
		else
			bfr.Add(ctx.Page().Lang().Num_mgr().Format_num(v));
	}
	public Pfunc_wiki_stats(int id) {this.id = id;}
	@Override public int Id() {return id;} private int id;
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_wiki_stats(id).Name_(name);}
	public static final Pfunc_wiki_stats Instance = new Pfunc_wiki_stats(-1);
}
