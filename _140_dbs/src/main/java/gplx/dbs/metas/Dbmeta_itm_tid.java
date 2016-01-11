package gplx.dbs.metas; import gplx.*; import gplx.dbs.*;
public class Dbmeta_itm_tid {
	public static final int Tid_unknown = 0, Tid_table = 1, Tid_index = 2;
	public static final String Key_table = "table", Key_index = "index";
	public static int Xto_int(String s) {
		s = String_.Lower(s);
		if		(String_.Eq(s, Key_table))	return Tid_table;
		else if (String_.Eq(s, Key_index))	return Tid_index;
		else								return Tid_unknown;
	}
}
