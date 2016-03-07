package gplx.xowa.addons.searchs.searchers.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.addons.searchs.dbs.*;
public class Srch2_word_cache {
	private final Ordered_hash hash = Ordered_hash_.New_bry();
	public void Clear() {hash.Clear();}
	public void Add(byte[] key, Srch2_word_row row) {hash.Add(key, row);}
	public Srch2_word_row Get_word_or_load(Srch2_ctx ctx, byte[] word_text) {
		Srch2_word_row rv = (Srch2_word_row)hash.Get_by(word_text); if (rv != null) return rv;
		return Get_word_or_make(ctx, word_text, ctx.Tbl__word.Select_by_or_null(word_text));
	}
	public Srch2_word_row Get_word_or_make(Srch2_ctx ctx, byte[] word_text, Srch_word_row row) {
		Srch2_word_row rv = (row == Srch_word_row.Null) 
			? Srch2_word_row.Not_found
			: new Srch2_word_row(row.Id, word_text, row.Page_count, row.Score_max);
		hash.Add(word_text, rv);
		return rv;
	}
	public int Get_id_or_load(Srch2_ctx ctx, byte[] word_text) {
		Srch2_word_row word = Get_word_or_load(ctx, word_text);
		return word == Srch2_word_row.Not_found ? -1 : word.Id;
	}
}
