package gplx.xowa.addons.searchs.v1s; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.addons.searchs.dbs.*;
import gplx.xowa.addons.searchs.searchers.crts.*;
class Xows_db_matcher_bldr {
	public Srch1_word_row[] Gather_words_from_db(Cancelable cxl, Srch2_crt_node matcher, Srch_word_tbl word_tbl) {
		List_adp rv = List_adp_.new_();
		Gather_words_from_db(cxl, matcher, rv, word_tbl);
		rv.Sort_by(Srch1_word_row_sorter.Page_count_dsc);
		return (Srch1_word_row[])rv.To_ary(Srch1_word_row.class);
	}
	private void Gather_words_from_db(Cancelable cxl, Srch2_crt_node matcher, List_adp rv, Srch_word_tbl word_tbl) {
		switch (matcher.tid) {
			case Srch2_crt_node.Tid_word:
				byte[] word_text = matcher.raw;
				if (Bry_.Has(word_text, Byte_ascii.Star))
					Load_word_many(cxl, rv, word_text, word_tbl);
				else
					Load_word_one(rv, word_text, word_tbl);
				break;
			case Srch2_crt_node.Tid_word_quote:
				List_adp tmp = List_adp_.new_();
				byte[][] words = Bry_split_.Split(matcher.raw, Byte_ascii.Space, Bool_.Y);
				int words_len = words.length;
				for (int i = 0; i < words_len; ++i) {
					byte[] word = words[i];
					Load_word_one(tmp, word, word_tbl);
				}
				if (tmp.Count() == 0) return;	// no words in db; EX: "xyz cba"
				tmp.Sort_by(Srch1_word_row_sorter.Page_count_dsc);
				rv.Add(tmp.Get_at(0));			// add lowest page_count word to db; EX: search for "earth and" should search for "earth" only, but match for "earth and"
				break;
			case Srch2_crt_node.Tid_and:
				List_adp lhs_tmp = List_adp_.new_(), rhs_tmp = List_adp_.new_();
				Gather_words_from_db(cxl, matcher.lhs, lhs_tmp, word_tbl);
				Gather_words_from_db(cxl, matcher.rhs, rhs_tmp, word_tbl);
				List_adp_.Add_list(rv, Calc_lowest(lhs_tmp, rhs_tmp)); // calc side with lowest count; add to rv;
				break;
			case Srch2_crt_node.Tid_or:
				Gather_words_from_db(cxl, matcher.lhs, rv, word_tbl);
				Gather_words_from_db(cxl, matcher.rhs, rv, word_tbl);
				break;
			case Srch2_crt_node.Tid_not:			break;			// never add "NOT" to db_search
			case Srch2_crt_node.Tid_invalid:		break;			// should not happen
			default:							throw Err_.new_unhandled(matcher.tid);
		}
	}
	private List_adp Calc_lowest(List_adp lhs, List_adp rhs) {
		int lhs_count = Calc(lhs);
		int rhs_count = Calc(rhs);
		// never return 0 as lowest count; note that NOT will return 0;
		if		(lhs_count == 0)	return rhs;
		else if (rhs_count == 0)	return lhs;
		else						return lhs_count < rhs_count ? lhs : rhs;
	}
	private int Calc(List_adp list) {
		int rv = 0;
		int len = list.Count();
		for (int i = 0; i < len; ++i) {
			Srch1_word_row word = (Srch1_word_row)list.Get_at(i);
			rv += word.page_count;
		}
		return rv;
	}
	private void Load_word_one(List_adp rv, byte[] word_text, Srch_word_tbl word_tbl) {
		Xoa_app_.Usr_dlg().Prog_many("", "", "search:word by text; word=~{0}", word_text);
		Srch_word_row row = word_tbl.Select_by_or_null(word_text); if (row == Srch_word_row.Null) return;
		Srch1_word_row word = new Srch1_word_row(row.Id, row.Text, row.Page_count);
		rv.Add(word);
	}
	private void Load_word_many(Cancelable cxl, List_adp rv, byte[] word_text, Srch_word_tbl word_tbl) {
		Xoa_app_.Usr_dlg().Prog_many("", "", "search:word by wildcard; word=~{0}", word_text);
		Srch_word_row[] rows = word_tbl.Select_like(cxl, word_text);
		int len = rows.length;
		for (int i = 0; i < len; ++i) {
			Srch_word_row row = rows[i];
			rv.Add(new Srch_word_row(row.Id, row.Text, row.Page_count, 0));
		}
	}
}
