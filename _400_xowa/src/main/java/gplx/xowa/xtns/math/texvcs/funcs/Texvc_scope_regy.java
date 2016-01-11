package gplx.xowa.xtns.math.texvcs.funcs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
import gplx.xowa.xtns.math.texvcs.tkns.*;
public class Texvc_scope_regy {
	private final Texvc_scope_itm[] ary = new Texvc_scope_itm[Texvc_scope_itm_.Id_len];
	private final Hash_adp_bry hash = Hash_adp_bry.cs();
	public Texvc_scope_itm Get_at(int id) {return ary[id];}
	public Texvc_scope_itm Get_by_mid(byte[] src, int bgn, int end) {return (Texvc_scope_itm)hash.Get_by_mid(src, bgn, end);}
	private void Add(Texvc_scope_itm itm) {
		ary[itm.Id()] = itm;
		hash.Add(itm.Key(), itm);
	}
	public static Texvc_scope_regy new_() {
		Texvc_scope_regy rv = new Texvc_scope_regy();
rv.Add(Make(Texvc_scope_itm_.Id__matrix, "matrix"));
rv.Add(Make(Texvc_scope_itm_.Id__pmatrix, "pmatrix"));
rv.Add(Make(Texvc_scope_itm_.Id__bmatrix, "bmatrix"));
rv.Add(Make(Texvc_scope_itm_.Id__Bmatrix, "Bmatrix"));
rv.Add(Make(Texvc_scope_itm_.Id__vmatrix, "vmatrix"));
rv.Add(Make(Texvc_scope_itm_.Id__Vmatrix, "Vmatrix"));
rv.Add(Make(Texvc_scope_itm_.Id__array, "array"));
rv.Add(Make(Texvc_scope_itm_.Id__align, "align"));
rv.Add(Make(Texvc_scope_itm_.Id__alignat, "alignat"));
rv.Add(Make(Texvc_scope_itm_.Id__smallmatrix, "smallmatrix"));
rv.Add(Make(Texvc_scope_itm_.Id__cases, "cases"));
		return rv;
	}
	private static Texvc_scope_itm Make(int id, String key) {return new Texvc_scope_itm(id, Bry_.new_a7(key));}	// NOTE: TEX func names are ASCII
}
