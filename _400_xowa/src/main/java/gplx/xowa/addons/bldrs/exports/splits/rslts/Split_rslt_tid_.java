package gplx.xowa.addons.bldrs.exports.splits.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
public class Split_rslt_tid_ {
	public static final int Tid_max = 9;
	public static final int Tid__page = 0, Tid__html = 1, Tid__srch_word = 2, Tid__srch_link = 3, Tid__fsdb_bin = 4, Tid__fsdb_fil = 5, Tid__fsdb_thm = 6, Tid__fsdb_org = 7, Tid__rndm = 8;
	public static String To_str(int tid) {
		switch (tid) {
			case Tid__page:			return "page";
			default:				throw Err_.new_unhandled_default(tid);
		}
	}
}
