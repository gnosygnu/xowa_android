package gplx.xowa.files.bins; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public class Xof_bin_wkr_ {
	public static final Xof_bin_wkr[] Ary_empty = new Xof_bin_wkr[0];
	public static final byte Tid_null = Byte_.Max_value_127, Tid_noop = 1, Tid_not_found = 2
	, Tid_fsdb_xowa = 3	, Tid_http_wmf	= 5
	, Tid_fsys_wmf	= 6	, Tid_fsys_xowa = 7
	;
	public static final String 
	  Key_fsdb_wiki = "xowa.fsdb.wiki"	, Key_http_wmf	= "xowa.http.wmf"
	, Key_fsys_wmf	= "xowa.fsys.wmf"	, Key_fsys_xowa = "xowa.fsys.xowa"
	;
	public static byte X_key_to_tid(String key) {
		if		(String_.Eq(key, Key_fsdb_wiki))	return Tid_fsdb_xowa;
		else if	(String_.Eq(key, Key_http_wmf))		return Tid_http_wmf;
		else if	(String_.Eq(key, Key_fsys_wmf))		return Tid_fsys_wmf;
		else if	(String_.Eq(key, Key_fsys_xowa))	return Tid_fsys_xowa;
		else										return Tid_null;
	}
}
