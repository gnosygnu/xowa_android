package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
public class Srch_crt_node_ {
	public static final int 
	  Tid__word			= 0		// EX: 'A'
	, Tid__and			= 1		// EX: 'A B'
	, Tid__or			= 2		// EX: 'A OR B'
	, Tid__not			= 3		// EX: '-A'
	, Tid__word_quote	= 4		// EX: '"A B"'
	, Tid__invalid		= 5		// EX: 'A OR'; incomplete or otherwise invalid
	;
	public static final int
	  Where__eq			= 0		// EX: 'ab'			-> "word_text = 'ab'"
	, Where__rng		= 1		// EX: 'ab*'		-> "word_text >= 'ab' AND word_text < 'ac'"
	, Where__like		= 2		// EX: 'a*b', '*a*b'-> "word_text LIKE 'a%b%'"
	;
	public static Srch_crt_node[] Ary_empty = new Srch_crt_node[0];
}
