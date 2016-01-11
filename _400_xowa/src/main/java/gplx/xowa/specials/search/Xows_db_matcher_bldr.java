package gplx.xowa.specials.search; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*;
import gplx.xowa.wikis.data.tbls.*;
class Xows_db_matcher_bldr {
	public Xows_db_word[] Gather_words_for_db(Cancelable cxl, Xows_db_matcher matcher, Xowd_search_word_tbl word_tbl) {
		List_adp rv = List_adp_.new_();
		Gather_words_for_db(cxl, matcher, rv, word_tbl);
		rv.Sort_by(Xows_db_word_sorter.Page_count_dsc);
		return (Xows_db_word[])rv.To_ary(Xows_db_word.class);
	}
	private void Gather_words_for_db(Cancelable cxl, Xows_db_matcher matcher, List_adp rv, Xowd_search_word_tbl word_tbl) {
		switch (matcher.Tid()) {
			case Xows_db_matcher.Tid_word:
				byte[] word_text = matcher.Raw();
				if (Bry_.Has(word_text, Byte_ascii.Star))
					Load_word_many(cxl, rv, word_text, word_tbl);
				else
					Load_word_one(rv, word_text, word_tbl);
				break;
			case Xows_db_matcher.Tid_word_quote:
				List_adp tmp = List_adp_.new_();
				byte[][] words = Bry_split_.Split(matcher.Raw(), Byte_ascii.Space, Bool_.Y);
				int words_len = words.length;
				for (int i = 0; i < words_len; ++i) {
					byte[] word = words[i];
					Load_word_one(tmp, word, word_tbl);
				}
				if (tmp.Count() == 0) return;	// no words in db; EX: "xyz cba"
				tmp.Sort_by(Xows_db_word_sorter.Page_count_dsc);
				rv.Add(tmp.Get_at(0));			// add lowest page_count word to db; EX: search for "earth and" should search for "earth" only, but match for "earth and"
				break;
			case Xows_db_matcher.Tid_and:
				List_adp lhs_tmp = List_adp_.new_(), rhs_tmp = List_adp_.new_();
				Gather_words_for_db(cxl, matcher.Lhs(), lhs_tmp, word_tbl);
				Gather_words_for_db(cxl, matcher.Rhs(), rhs_tmp, word_tbl);
				List_adp_.Add_list(rv, Calc_lowest(lhs_tmp, rhs_tmp)); // calc side with lowest count; add to rv;
				break;
			case Xows_db_matcher.Tid_or:
				Gather_words_for_db(cxl, matcher.Lhs(), rv, word_tbl);
				Gather_words_for_db(cxl, matcher.Rhs(), rv, word_tbl);
				break;
			case Xows_db_matcher.Tid_not:		break;			// never add "NOT" to db_search
			case Xows_db_matcher.Tid_null:		break;			// should not happen
			default:							throw Err_.new_unhandled(matcher.Tid());
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
			Xows_db_word word = (Xows_db_word)list.Get_at(i);
			rv += word.Page_count();
		}
		return rv;
	}
	private void Load_word_one(List_adp rv, byte[] word_text, Xowd_search_word_tbl word_tbl) {
		Xoa_app_.Usr_dlg().Prog_many("", "", "search:word by text; word=~{0}", word_text);
		Xowd_search_word_row row = word_tbl.Select_by_or_null(word_text); if (row == Xowd_search_word_row.Null) return;
		Xows_db_word word = new Xows_db_word(row.Id(), row.Text(), row.Page_count());
		rv.Add(word);
	}
	private void Load_word_many(Cancelable cxl, List_adp rv, byte[] word_text, Xowd_search_word_tbl word_tbl) {
		Xoa_app_.Usr_dlg().Prog_many("", "", "search:word by wildcard; word=~{0}", word_text);
		Xowd_search_word_row[] rows = word_tbl.Select_in(cxl, word_text);
		int len = rows.length;
		for (int i = 0; i < len; ++i) {
			Xowd_search_word_row row = rows[i];
			rv.Add(new Xows_db_word(row.Id(), row.Text(), row.Page_count()));
		}
	}
}
