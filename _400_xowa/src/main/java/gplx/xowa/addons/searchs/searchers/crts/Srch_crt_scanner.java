package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.core.primitives.*; import gplx.core.btries.*;
import gplx.xowa.addons.searchs.parsers.*;
class Srch_crt_scanner {
	private final List_adp tkns = List_adp_.new_(); private byte[] src; private int src_len, pos, txt_bgn;
	private final Ordered_hash tmp_list = Ordered_hash_.New(); private final Bry_bfr tmp_bfr = Bry_bfr.new_();
	//private final Srch_text_parser parser;
	public Srch_crt_scanner(Srch_text_parser parser) {}//this.parser = parser;}
	public Srch_crt_tkn[] Scan(byte[] src) {
		this.src = src; this.src_len = src.length;
		tkns.Clear(); pos = 0; txt_bgn = -1; 
		while (pos < src_len) {
			byte cur_b = src[pos];
			Object cur_obj = trie.Match_bgn_w_byte(cur_b, src, pos, src_len);
			if (cur_obj == null) {					// text character
				if (txt_bgn == -1) txt_bgn = pos;	// 1st character not set; set it
				++pos;
			}	
			else {									// AND, OR, (, ), -, \s, "
				int pos_end = trie.Match_pos();
				byte cur_tid = ((Byte_obj_val)cur_obj).Val(); 
				if (Cur_join_is_word(cur_tid, pos_end)) continue;	// ignore words containing "and", "or"; EX: "random"; "for"
				if (txt_bgn != -1) {				// pending word; create
					Tkns_add_word(Srch_crt_tkn.Tid__word, txt_bgn, pos);
					txt_bgn = -1;
				}
				switch (cur_tid) {
					case Srch_crt_tkn.Tid__space:	// discard spaces
						pos = Bry_find_.Find_fwd_while(src, pos, src_len, Byte_ascii.Space);
						break;
					case Srch_crt_tkn.Tid__quote:	// find end quote and add as word
						int quote_bgn = pos + 1;
						int quote_end = Bry_find_.Find_fwd(src, Byte_ascii.Quote, quote_bgn, src_len);
						if (quote_end == Bry_find_.Not_found) throw Err_.new_wo_type("could not find end quote", "src", String_.new_u8(src));
						Tkns_add_word(Srch_crt_tkn.Tid__word_quoted, quote_bgn, quote_end);
						pos = quote_end + 1;		// +1 to place after quote
						break;
					case Srch_crt_tkn.Tid__not: 
						Tkns_add_word(Srch_crt_tkn.Tid__not, pos, pos_end);
						pos = pos_end;
						break;
					case Srch_crt_tkn.Tid__paren_bgn: case Srch_crt_tkn.Tid__paren_end:
					case Srch_crt_tkn.Tid__and: case Srch_crt_tkn.Tid__or:
						tkns.Add(new_tkn(cur_tid, src, pos, pos_end));
						pos = pos_end;
						break;
					default: throw Err_.new_unhandled(cur_tid);
				}
			}
		}
		if (txt_bgn != -1) {	// pending word; create
			Tkns_add_word(Srch_crt_tkn.Tid__word, txt_bgn, pos);
			txt_bgn = -1;
		}
		return (Srch_crt_tkn[])tkns.To_ary_and_clear(Srch_crt_tkn.class);
	}
	private boolean Cur_join_is_word(byte cur_tid, int pos_end) {	// extra logic to handle and / or occuring in unquoted strings; EX: "random"; "for"
		switch (cur_tid) {
			default: return false;	// only look at AND, OR, -
			case Srch_crt_tkn.Tid__and: case Srch_crt_tkn.Tid__or: case Srch_crt_tkn.Tid__not:
				break;
		}
		boolean join_is_word = true;
		if (txt_bgn == -1) {		// no pending word;
			if (cur_tid == Srch_crt_tkn.Tid__not) return false;	// NOT is only operator if no pending tkn; EX: -abc -> NOT abc; a-b -> a-b
			byte nxt_b = pos_end < src_len ? src[pos_end] : Byte_ascii.Null;
			Object nxt_obj = trie.Match_bgn_w_byte(nxt_b, src, pos_end, src_len);
			if (nxt_obj == null)	// next tkn is text; join must be word
				join_is_word = true;
			else {					// next tkn is tkn
				byte nxt_tid = ((Byte_obj_val)nxt_obj).Val(); 
				switch (nxt_tid) {
					case Srch_crt_tkn.Tid__space: case Srch_crt_tkn.Tid__quote:
					case Srch_crt_tkn.Tid__paren_bgn: case Srch_crt_tkn.Tid__paren_end:
						join_is_word = false;	// next tkn is sym; and/or is not word; EX: a AND ; a AND"b"; a AND(b)
						break;
					case Srch_crt_tkn.Tid__not: case Srch_crt_tkn.Tid__and: case Srch_crt_tkn.Tid__or:
						join_is_word = true;	// next tkn is and or not; and/or is word; EX: andor; oror; or-abc;
						break;
					default: throw Err_.new_unhandled(cur_tid);
				}
			}
		}
		else {									// pending word; cur join must be word; EX: "grand": "and" invoked and "gr" pending
			join_is_word = true;
		}
		if (join_is_word) {
			if (txt_bgn == -1) txt_bgn = pos;	// 1st character not set; set it
			pos = pos_end;
			return true;
		}
		if (txt_bgn != -1) {
			Tkns_add_word(Srch_crt_tkn.Tid__word, txt_bgn, pos); // create word
			txt_bgn = -1;
		}
		return false;
	}
//		private byte[] Parse_to_words() {
//			parser.Parse(;
//		}
	private void Tkns_add_word(byte tid, int src_bgn, int src_end) {
		if (tkns.Count() > 0) {								// at least 1 tkn; check for "auto-and"
			Srch_crt_tkn last_tkn = (Srch_crt_tkn)tkns.Get_at_last();
			if (last_tkn.tid == Srch_crt_tkn.Tid__word)	// previous tkn is word; auto "AND" words; EX: A B -> A AND B
				tkns.Add(new Srch_crt_tkn(Srch_crt_tkn.Tid__and, Bry_and));
		}
		if (tid == Srch_crt_tkn.Tid__word) {				// if word has symbol, convert to quoted; EX: a-b should become "a-b"; otherwise searcher would search for a single word a-b
			byte[] cur_word = Bry_.Mid(src, src_bgn, src_end);
			byte[][] words = gplx.xowa.bldrs.cmds.texts.tdbs.Srch_bldr_wkr_base.Split_ttl_into_words(null, tmp_list, tmp_bfr, cur_word);
			int words_len = words.length;
			if (	words_len == 1					// only one word
				&&	!Bry_.Eq(words[0], cur_word)	// split word not same as raw
				&&	Bry_find_.Find_fwd(cur_word, Byte_ascii.Star) == -1	// no asterisk
				) {
				tkns.Add(new Srch_crt_tkn(tid, words[0]));
				return;
			}
			if (words.length > 1)					// multiple words; add as quoted-term; EX: "a-b"
				tid = Srch_crt_tkn.Tid__word_quoted;
		}
		tkns.Add(new_tkn(tid, src, src_bgn, src_end));
	}
	private Srch_crt_tkn new_tkn(byte tid, byte[] src, int val_bgn, int val_end) {return new Srch_crt_tkn(tid, Bry_.Mid(src, val_bgn, val_end));}
	private static final byte[] Bry_and = Bry_.new_a7("AND");
	private static final Btrie_slim_mgr trie = Btrie_slim_mgr.ci_a7()// NOTE:ci.ascii:OR / AND only
	.Add_str_byte(" "	, Srch_crt_tkn.Tid__space)
	.Add_str_byte("\""	, Srch_crt_tkn.Tid__quote)
	.Add_str_byte("-"	, Srch_crt_tkn.Tid__not)
	.Add_str_byte("("	, Srch_crt_tkn.Tid__paren_bgn)
	.Add_str_byte(")"	, Srch_crt_tkn.Tid__paren_end)
	.Add_str_byte("or"	, Srch_crt_tkn.Tid__or)
	.Add_str_byte("and"	, Srch_crt_tkn.Tid__and);
}
