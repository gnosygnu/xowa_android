package gplx.xowa.specials.search.parsers_old; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*; import gplx.xowa.specials.search.*;
public class Xow_search_parser {
	private Xow_search_parser_ctx parse_ctx = new Xow_search_parser_ctx(); private byte[] src;
	public Xows_db_matcher Parse(byte[] src) {
		this.src = src;
		Xow_search_tkn[] tkns = new Xow_search_scanner().Scan(src);
		return Parse_itm_or(parse_ctx.Init(tkns));
	}
	private Xows_db_matcher Parse_itm_or(Xow_search_parser_ctx parse_ctx) {
		Xows_db_matcher lhs = Parse_itm_and(parse_ctx);
		while (parse_ctx.Cur_tid(Xow_search_tkn.Tid_or)) {
			parse_ctx.Move_next();
			Xows_db_matcher rhs = Parse_itm_and(parse_ctx);
			lhs = new_join(Xows_db_matcher.Tid_or, lhs, rhs);
		}
		return lhs;
	}
	private Xows_db_matcher Parse_itm_and(Xow_search_parser_ctx parse_ctx) {
		Xows_db_matcher lhs = Parse_itm_not(parse_ctx);
		while (parse_ctx.Cur_tid(Xow_search_tkn.Tid_and)) {
			parse_ctx.Move_next();
			Xows_db_matcher rhs = Parse_itm_not(parse_ctx);
			lhs = new_join(Xows_db_matcher.Tid_and, lhs, rhs);
		}
		return lhs;
	}
	private Xows_db_matcher Parse_itm_not(Xow_search_parser_ctx parse_ctx) {
		Xows_db_matcher lhs = Parse_itm_leaf(parse_ctx);
		while (parse_ctx.Cur_tid(Xow_search_tkn.Tid_not)) {
			parse_ctx.Move_next();
			Xows_db_matcher rhs = Parse_itm_leaf(parse_ctx);
			lhs = new_join(Xows_db_matcher.Tid_not, null, rhs);
		}
		return lhs;
	}
	private Xows_db_matcher Parse_itm_leaf(Xow_search_parser_ctx parse_ctx) {
		if (parse_ctx.Cur_tid(Xow_search_tkn.Tid_paren_bgn)) {
			parse_ctx.Move_next();
			Xows_db_matcher lhs = Parse_itm_or(parse_ctx);
			if (parse_ctx.Cur_tid(Xow_search_tkn.Tid_paren_end)) parse_ctx.Move_next();	// skip token
			return lhs;
		}
		else if (parse_ctx.Cur_tid(Xow_search_tkn.Tid_eos))
			return Xows_db_matcher.Null;
		else {
			Xow_search_tkn word_tkn = parse_ctx.Move_next();
			if (word_tkn.Tid() == Xow_search_tkn.Tid_not) {
				word_tkn = parse_ctx.Move_next();
				if (word_tkn == null) return Xows_db_matcher.Null; // occurs in "a -"
				Xows_db_matcher word_itm = new_word(word_tkn, src);
				return new_join(Xows_db_matcher.Tid_not, null, word_itm);
			}
			else
				return new_word(word_tkn, src);
		}
	}
	private static Xows_db_matcher new_word(Xow_search_tkn tkn, byte[] src) {
		int tid = tkn.Tid() == Xow_search_tkn.Tid_word ? Xows_db_matcher.Tid_word : Xows_db_matcher.Tid_word_quote; 
		return new Xows_db_matcher(tid, tkn.Val(src), null, null);
	}
	private static Xows_db_matcher new_join(int tid, Xows_db_matcher lhs, Xows_db_matcher rhs) {return new Xows_db_matcher(tid, null, lhs, rhs);}
}
class Xow_search_parser_ctx {
	private Xow_search_tkn[] ary; private int pos = 0; private int ary_len;
	public Xow_search_parser_ctx Init(Xow_search_tkn[] ary) {
		this.ary = ary;
		this.ary_len = ary.length; 
		this.pos = 0;
		return this;
	}
	public boolean Cur_tid(byte tid) {return pos < ary_len ? tid == ary[pos].Tid(): tid == Xow_search_tkn.Tid_eos;}
	public Xow_search_tkn Move_next() {
		Xow_search_tkn rv = null; 
		if (pos < ary_len) rv = ary[pos++];
		return rv;
	}
}
