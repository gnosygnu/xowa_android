package gplx.dbs; import gplx.*;
public class Db_tbl_ {
	public static void Create_tbl(Db_tbl... ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i)
			ary[i].Create_tbl();
	}
	public static void Rls(Db_tbl... ary) {
		int len = ary.length;
		for (int i = 0; i < len; ++i)
			ary[i].Rls();
	}
}
