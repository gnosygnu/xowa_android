package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.xowa.addons.searchs.parsers.*;
public class Srch_crt_parser {
	private final Srch_parser_ctx ctx = new Srch_parser_ctx(); private byte[] src;
	private final Srch_crt_visitor__flatten flatten = new Srch_crt_visitor__flatten();
	private int uid_next;
	private final Srch_text_parser parser;
	public Srch_crt_parser(Srch_text_parser parser) {this.parser = parser;}
	public Srch_crt_node Parse(byte[] src) {
		this.src = src;
		uid_next = 0;
		Srch_crt_tkn[] tkns = new Srch_crt_scanner(parser).Scan(src);
		return Flatten(Parse_itm_or(ctx.Init(tkns)));
	}
	private Srch_crt_node Flatten(Srch_crt_node root) {
		Srch_crt_node rv = root;
		Srch_crt_node[] subs = flatten.Flatten(root);
		if (subs != null) rv = new Srch_crt_node(root.uid, root.tid, true, subs, null);
		return rv;
	}
	private Srch_crt_node Parse_itm_or(Srch_parser_ctx ctx) {
		Srch_crt_node lhs = Parse_itm_and(ctx);
		while (ctx.Cur_tid(Srch_crt_tkn.Tid__or)) {
			ctx.Move_next();
			Srch_crt_node rhs = Parse_itm_and(ctx);
			lhs = New_join(Srch_crt_node_.Tid__or, ++uid_next, lhs, rhs);
		}
		return lhs;
	}
	private Srch_crt_node Parse_itm_and(Srch_parser_ctx ctx) {
		Srch_crt_node lhs = Parse_itm_not(ctx);
		while (ctx.Cur_tid(Srch_crt_tkn.Tid__and)) {
			ctx.Move_next();
			Srch_crt_node rhs = Parse_itm_not(ctx);
			lhs = New_join(Srch_crt_node_.Tid__and, ++uid_next, lhs, rhs);
		}
		return lhs;
	}
	private Srch_crt_node Parse_itm_not(Srch_parser_ctx ctx) {
		Srch_crt_node lhs = Parse_itm_leaf(ctx);
		while (ctx.Cur_tid(Srch_crt_tkn.Tid__not)) {
			ctx.Move_next();
			Srch_crt_node rhs = Parse_itm_leaf(ctx);
			lhs = New_join(Srch_crt_node_.Tid__not, ++uid_next, rhs);
		}
		return lhs;
	}
	private Srch_crt_node Parse_itm_leaf(Srch_parser_ctx ctx) {
		if (ctx.Cur_tid(Srch_crt_tkn.Tid__paren_bgn)) {
			ctx.Move_next();
			Srch_crt_node lhs = Parse_itm_or(ctx);
			if (ctx.Cur_tid(Srch_crt_tkn.Tid__paren_end)) ctx.Move_next();	// skip token
			return lhs;
		}
		else if (ctx.Cur_tid(Srch_crt_tkn.Tid__eos))
			return Srch_crt_node.Invalid;
		else {
			Srch_crt_tkn word_tkn = ctx.Move_next();
			if (word_tkn.tid == Srch_crt_tkn.Tid__not) {
				word_tkn = ctx.Move_next();
				if (word_tkn == null) return Srch_crt_node.Invalid; // occurs in "a -"
				Srch_crt_node word_itm = New_word(word_tkn, ++uid_next, src);
				return New_join(Srch_crt_node_.Tid__not, ++uid_next, word_itm);
			}
			else
				return New_word(word_tkn, ++uid_next, src);
		}
	}
	private static Srch_crt_node New_join(int tid, int uid, Srch_crt_node... ary) {return new Srch_crt_node(uid, tid, false, ary, null);}
	private static Srch_crt_node New_word(Srch_crt_tkn tkn, int uid, byte[] src) {
		int tid = tkn.tid == Srch_crt_tkn.Tid__word ? Srch_crt_node_.Tid__word : Srch_crt_node_.Tid__word_quote; 
		return new Srch_crt_node(uid, tid, false, Srch_crt_node_.Ary_empty, tkn.val);
	}
}
class Srch_parser_ctx {
	private Srch_crt_tkn[] ary; private int pos = 0; private int ary_len;
	public Srch_parser_ctx Init(Srch_crt_tkn[] ary) {
		this.ary = ary;
		this.ary_len = ary.length; 
		this.pos = 0;
		return this;
	}
	public boolean Cur_tid(byte tid) {return pos < ary_len ? tid == ary[pos].tid : tid == Srch_crt_tkn.Tid__eos;}
	public Srch_crt_tkn Move_next() {
		Srch_crt_tkn rv = null; 
		if (pos < ary_len) rv = ary[pos++];
		return rv;
	}
}
