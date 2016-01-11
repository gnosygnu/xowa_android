package gplx.xowa.xtns.geodata; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Geo_coordinates_func extends Pf_func_base {
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {}	// NOOP: MW uses to save coordinates for API retrieval
	@Override public int Id() {return Xol_kwd_grp_.Id_xtn_geodata_coordinates;}
	@Override public Pf_func New(int id, byte[] name) {return new Geo_coordinates_func().Name_(name);}
	public static final Geo_coordinates_func Instance = new Geo_coordinates_func(); Geo_coordinates_func() {}
}
