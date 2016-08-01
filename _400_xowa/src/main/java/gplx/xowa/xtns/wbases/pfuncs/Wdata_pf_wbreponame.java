package gplx.xowa.xtns.wbases.pfuncs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*;
import gplx.langs.jsons.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Wdata_pf_wbreponame extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_wbreponame;}
	@Override public Pf_func New(int id, byte[] name) {return new Wdata_pf_wbreponame().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		bfr.Add(Reponame);	// NOTE: MW has logic to look for message named "wbreponame", and returning it if it exists; only applies to non-WMF Wikidatas; DATE:2014-09-07
	}	private static final    byte[] Reponame = Bry_.new_a7("Wikidata");
}
