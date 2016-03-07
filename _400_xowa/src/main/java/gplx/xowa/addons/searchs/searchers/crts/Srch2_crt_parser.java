package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.addons.searchs.parsers.*;
public class Srch2_crt_parser {
	private final Srch_parser_ctx parse_ctx = new Srch_parser_ctx(); private byte[] src;
	private int uid_next;
	private final Srch2_text_parser parser;
	public Srch2_crt_parser(Srch2_text_parser parser) {this.parser = parser;}
	public Srch2_crt_node Parse(byte[] src) {
		this.src = src;
		uid_next = 0;
		Srch2_crt_tkn[] tkns = new Srch2_crt_scanner(parser).Scan(src);
		return Parse_itm_or(parse_ctx.Init(tkns));
	}
	private Srch2_crt_node Parse_itm_or(Srch_parser_ctx parse_ctx) {
		Srch2_crt_node lhs = Parse_itm_and(parse_ctx);
		while (parse_ctx.Cur_tid(Srch2_crt_tkn.Tid_or)) {
			parse_ctx.Move_next();
			Srch2_crt_node rhs = Parse_itm_and(parse_ctx);
			lhs = New_join(Srch2_crt_node.Tid_or, ++uid_next, lhs, rhs);
		}
		return lhs;
	}
	private Srch2_crt_node Parse_itm_and(Srch_parser_ctx parse_ctx) {
		Srch2_crt_node lhs = Parse_itm_not(parse_ctx);
		while (parse_ctx.Cur_tid(Srch2_crt_tkn.Tid_and)) {
			parse_ctx.Move_next();
			Srch2_crt_node rhs = Parse_itm_not(parse_ctx);
			lhs = New_join(Srch2_crt_node.Tid_and, ++uid_next, lhs, rhs);
		}
		return lhs;
	}
	private Srch2_crt_node Parse_itm_not(Srch_parser_ctx parse_ctx) {
		Srch2_crt_node lhs = Parse_itm_leaf(parse_ctx);
		while (parse_ctx.Cur_tid(Srch2_crt_tkn.Tid_not)) {
			parse_ctx.Move_next();
			Srch2_crt_node rhs = Parse_itm_leaf(parse_ctx);
			lhs = New_join(Srch2_crt_node.Tid_not, ++uid_next, null, rhs);
		}
		return lhs;
	}
	private Srch2_crt_node Parse_itm_leaf(Srch_parser_ctx parse_ctx) {
		if (parse_ctx.Cur_tid(Srch2_crt_tkn.Tid_paren_bgn)) {
			parse_ctx.Move_next();
			Srch2_crt_node lhs = Parse_itm_or(parse_ctx);
			if (parse_ctx.Cur_tid(Srch2_crt_tkn.Tid_paren_end)) parse_ctx.Move_next();	// skip token
			return lhs;
		}
		else if (parse_ctx.Cur_tid(Srch2_crt_tkn.Tid_eos))
			return Srch2_crt_node.Invalid;
		else {
			Srch2_crt_tkn word_tkn = parse_ctx.Move_next();
			if (word_tkn.tid == Srch2_crt_tkn.Tid_not) {
				word_tkn = parse_ctx.Move_next();
				if (word_tkn == null) return Srch2_crt_node.Invalid; // occurs in "a -"
				Srch2_crt_node word_itm = New_word(word_tkn, ++uid_next, src);
				return New_join(Srch2_crt_node.Tid_not, ++uid_next, null, word_itm);
			}
			else
				return New_word(word_tkn, ++uid_next, src);
		}
	}
	private static Srch2_crt_node New_word(Srch2_crt_tkn tkn, int uid, byte[] src) {
		int tid = tkn.tid == Srch2_crt_tkn.Tid_word ? Srch2_crt_node.Tid_word : Srch2_crt_node.Tid_word_quote; 
		return new Srch2_crt_node(uid, tid, tkn.val, null, null);
	}
	private static Srch2_crt_node New_join(int tid, int uid, Srch2_crt_node lhs, Srch2_crt_node rhs) {return new Srch2_crt_node(uid, tid, null, lhs, rhs);}
}
class Srch_parser_ctx {
	private Srch2_crt_tkn[] ary; private int pos = 0; private int ary_len;
	public Srch_parser_ctx Init(Srch2_crt_tkn[] ary) {
		this.ary = ary;
		this.ary_len = ary.length; 
		this.pos = 0;
		return this;
	}
	public boolean Cur_tid(byte tid) {return pos < ary_len ? tid == ary[pos].tid : tid == Srch2_crt_tkn.Tid_eos;}
	public Srch2_crt_tkn Move_next() {
		Srch2_crt_tkn rv = null; 
		if (pos < ary_len) rv = ary[pos++];
		return rv;
	}
}
