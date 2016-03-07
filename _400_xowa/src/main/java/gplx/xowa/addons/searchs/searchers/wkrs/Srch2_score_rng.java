package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch2_score_rng {
	private int pages_max, score_max, rng_max;
	private int score_unit, request_count;
	private int pass_idx, rng_len, found_total;
	public int Rng_bgn() {return rng_bgn;} private int rng_bgn;
	public int Rng_end() {return rng_end;} private int rng_end;
	public void Init_for_wiki(int pages_max, int score_max) {
		this.pages_max = pages_max; this.score_max = score_max;
		this.rng_max = score_max / 4;
	}
	public void Init_for_search(int request_count, int words_total, int last_word_len) {
		this.pass_idx = found_total = 0;
		this.request_count = request_count;
		this.score_unit = Int_.DivAndRoundUp(request_count, (pages_max / score_max));	// EX: 100 / (16 M / 1 M) -> 7 units to fill 100
//			int adj_words_total = 0;
//			switch (words_total) {
//				case  1: adj_words_total =       0; break;
//				case  2: adj_words_total =      10; break;
//				case  3: adj_words_total =     100; break;
//				case  4: adj_words_total =     200; break;
//				default: adj_words_total =    1000; break;
////				default: adj_words_total =   10000; break;
//			}
		int adj_last_word_len = 0;
		switch (last_word_len) {
			case  1: adj_last_word_len =     0; break;
			case  2: adj_last_word_len =    10; break;
			case  3: adj_last_word_len =   100; break;
			case  4: adj_last_word_len =   200; break;
			case  5: adj_last_word_len =   500; break;
			case  6: adj_last_word_len =   800; break;
			case  7: adj_last_word_len =  1600; break;
			case  8: adj_last_word_len =  3200; break;
			default: adj_last_word_len =  5000; break;
		}
		rng_len = score_unit + (score_unit * (adj_last_word_len));
		rng_bgn = score_max;
		Rng_len_(Bool_.Y);
	}
	public boolean Update(int new_found_total) {
		if (rng_bgn == 0) return true;
		++pass_idx;
		int pass_found = new_found_total - found_total;
		found_total = new_found_total;
		int rng_multiplier = 0;
		if (pass_found == 0) {
			rng_multiplier = 100;
		} else {
			double pass_pct     = Math_.Div_safe_as_double(pass_found, request_count);	// EX: 30 / 100 ->  .3
			double found_pct    = Math_.Div_safe_as_double(found_total, request_count);	// EX: 43 / 100 ->  .57
			double need_pct     = 1 - found_pct;										// EX: 1 - .43  ->  .57
			double pass_needed  = need_pct / pass_pct;									// EX: .57 / .3 -> 1.9
			rng_multiplier		= (int)Math_.Ceil(pass_needed);							// EX: 1.9      -> 2
		}
		int new_rng_len = rng_len * rng_multiplier;
		// if (new_rng_len > rng_max) new_rng_len = rng_max;
		if (new_rng_len > 5000) new_rng_len = 2500;
		rng_len = new_rng_len;
		Rng_len_(Bool_.N);
		return false;
	}
	private void Rng_len_(boolean first) {
		rng_end = rng_bgn + (first ? 1 : 0);	// + 1 to include pages with scores at max; EX: > 999,998 AND < 1,000,001
		rng_bgn = rng_end - rng_len;
		if (rng_bgn < 0) rng_bgn = 0;
	}
}
