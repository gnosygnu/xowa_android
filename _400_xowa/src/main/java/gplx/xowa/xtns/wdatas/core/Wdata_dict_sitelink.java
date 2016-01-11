package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
public class Wdata_dict_sitelink {
	public static final byte
	  Tid_site									= 0
	, Tid_title									= 1
	, Tid_badges								= 2
	;
	public static final String
	  Str_site									= "site"
	, Str_title									= "title"
	, Str_badges								= "badges"
	;
	public static byte[] 
	  Bry_site									= Bry_.new_a7(Str_site)
	, Bry_title									= Bry_.new_a7(Str_title)
	, Bry_badges								= Bry_.new_a7(Str_badges)
	;
	public static final Hash_adp_bry Dict = Hash_adp_bry.cs()
	.Add_bry_byte(Bry_site						, Tid_site)
	.Add_bry_byte(Bry_title						, Tid_title)
	.Add_bry_byte(Bry_badges					, Tid_badges)
	;
}
