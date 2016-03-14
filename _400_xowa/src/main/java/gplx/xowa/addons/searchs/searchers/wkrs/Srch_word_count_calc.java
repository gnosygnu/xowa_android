package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*;
import gplx.xowa.addons.searchs.searchers.crts.*; import gplx.xowa.addons.searchs.dbs.*;
class Srch_word_count_calc {
	private static final Srch_word_count_wkr word_count_wkr = new Srch_word_count_wkr();
	public static Srch_crt_node Find_main(Srch_ctx ctx) {
		Srch_crt_node rv = ctx.Search_crt;
		if (rv.flat && rv.tid == Srch_crt_node_.Tid__and) {
			Srch_word_tbl word_tbl = ctx.Tbl__word;
			int count_min = Int_.Max_value;
			Srch_crt_node[] subs = rv.subs;					
			int subs_len = subs.length;
			for (int i = 0; i < subs_len; ++i) {
				Srch_crt_node sub = subs[i];
				int sub_count = Get_count(ctx, word_tbl, sub);
				if (sub_count < count_min) {
					count_min = sub_count;
					rv = sub;
				}
			}
		}
		return rv;
	}
	private static int Get_count(Srch_ctx ctx, Srch_word_tbl word_tbl, Srch_crt_node sub) {
		int cached_count = ctx.Cache__word_counts.Get_as_int_or(sub.raw, Int_.Min_value);
		if (cached_count != Int_.Min_value) return cached_count;
		int rv = Int_.Max_value;
		if (sub.raw_where_tid == Srch_crt_node_.Where__eq) {
			Srch_word_row word = word_tbl.Select_by_or_empty(sub.raw);
			if (word != Srch_word_row.Empty) rv = word.Link_count;
		}
		else {
			rv = word_count_wkr.Get_top_10(ctx, word_tbl, sub);
		}
		ctx.Cache__word_counts.Add_bry_int(sub.raw, rv);
		return rv;
	}
}
