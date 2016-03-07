package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.langs.regxs.*;
public class Srch2_crt_node {
	private final Gfo_pattern raw_pattern;
	public Srch2_crt_node(int uid, int tid, byte[] raw, Srch2_crt_node lhs, Srch2_crt_node rhs) {
		this.uid = uid; this.tid = tid; this.raw = raw; this.lhs = lhs; this.rhs = rhs;
		if (raw != null) {
			int raw_len = raw.length;
			int wildcard_pos = Bry_find_.Find_fwd(raw, Byte_ascii.Star, 0, raw_len);
			if		(wildcard_pos == Bry_find_.Not_found)	raw_where_tid = Srch2_crt_node.Where_tid__eq;
			else if (wildcard_pos == raw_len - 1)			raw_where_tid = Srch2_crt_node.Where_tid__rng;
			else											raw_where_tid = Srch2_crt_node.Where_tid__like;
		}
		else
			raw_where_tid = Where_tid__eq;
		if (raw_where_tid == Srch2_crt_node.Where_tid__rng) {
			raw_rng_bgn = Bry_.Mid(raw, 0, raw.length - 1);
			raw_rng_end = gplx.core.intls.Utf8_.Increment_char_at_last_pos(raw_rng_bgn);
		}
		else
			raw_rng_bgn = raw_rng_end = Bry_.Empty;
		this.raw_has_wildcard = raw_where_tid != Where_tid__eq;
		this.raw_pattern = raw_has_wildcard ? new Gfo_pattern(raw) : null;
	}
	public final int uid;
	public final int tid;
	public final byte[] raw;
	public final byte[] raw_rng_bgn, raw_rng_end;
	public final boolean raw_has_wildcard;
	public final int raw_where_tid;
	public final Srch2_crt_node lhs;
	public final Srch2_crt_node rhs;
	public Object raw_as_quoted_phrase;
	public void Accept_visitor(Srch2_crt_node_visitor visitor) {visitor.Visit(this);}

	public boolean Matches(gplx.xowa.addons.searchs.parsers.Srch2_text_parser text_parser, gplx.xowa.langs.cases.Xol_case_mgr case_mgr, byte[] ttl) {
		byte[] ttl_lower = case_mgr.Case_build_lower(Xoa_ttl.Replace_unders(ttl));
		byte[][] ttl_words = null;
		if (text_parser == null)
			ttl_words = Bry_split_.Split(ttl_lower, Byte_ascii.Space, Bool_.Y);
		else
			ttl_words = text_parser.Parse_to_bry_ary(ttl);
		return Matches(ttl_lower, ttl_words);
	}
	private boolean Matches(byte[] ttl_lower, byte[][] ttl_words) {
		switch (tid) {
			case Srch2_crt_node.Tid_word: {
				int len = ttl_words.length;
				for (int i = 0; i < len; ++i) {
					byte[] word = ttl_words[i];
					if (raw_pattern == null) {
						if (Bry_.Eq(word, raw)) return true;
					}
					else {
						if (raw_pattern.Match(word)) return true;
					}
				}
				return false;
			}
			case Srch2_crt_node.Tid_word_quote:	// note that raw does not have quotes; EX: "B*" -> B*
				return Bry_find_.Find_fwd(ttl_lower, raw) != Bry_find_.Not_found;
			case Srch2_crt_node.Tid_not:
				return !rhs.Matches(ttl_lower, ttl_words);
			case Srch2_crt_node.Tid_or:
				return lhs.Matches(ttl_lower, ttl_words) || rhs.Matches(ttl_lower, ttl_words);
			case Srch2_crt_node.Tid_and:
				return lhs.Matches(ttl_lower, ttl_words) && rhs.Matches(ttl_lower, ttl_words);
			case Srch2_crt_node.Tid_invalid: return false;
			default: throw Err_.new_unhandled(tid);
		}
	}

	public static final Srch2_crt_node Invalid = new Srch2_crt_node(-1, Srch2_crt_node.Tid_invalid, null, null, null);
	public static final int 
	  Tid_word			= 0		// EX: 'A'
	, Tid_and			= 1		// EX: 'A B'
	, Tid_or			= 2		// EX: 'A OR B'
	, Tid_not			= 3		// EX: '-A'
	, Tid_word_quote	= 4		// EX: '"A B"'
	, Tid_invalid		= 5		// EX: 'A OR'; incomplete or otherwise invalid
	;
	public static final int
	  Where_tid__eq			= 0		// EX: 'ab'			-> "word_text = 'ab'"
	, Where_tid__rng		= 1		// EX: 'ab*'		-> "word_text >= 'ab' AND word_text < 'ac'"
	, Where_tid__like		= 2		// EX: 'a*b', '*a*b'-> "word_text LIKE 'a%b%'"
	;
}
