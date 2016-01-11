package gplx.xowa.langs.vnts; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_vnt_dir_ {
	public static final int Tid__none = 0, Tid__uni = 1, Tid__bi = 2;
	public static int Parse(byte[] v) {return hash.Get_as_int_or(v, Tid__none);}
	private static final byte[] Bry__none = Bry_.new_a7("disable"), Bry__uni = Bry_.new_a7("unidirectional"), Bry__bi = Bry_.new_a7("bidirectional");
	private static final Hash_adp_bry hash = Hash_adp_bry.cs()
	.Add_bry_int(Bry__none	, Tid__none)
	.Add_bry_int(Bry__uni	, Tid__uni)
	.Add_bry_int(Bry__bi	, Tid__bi);
}
