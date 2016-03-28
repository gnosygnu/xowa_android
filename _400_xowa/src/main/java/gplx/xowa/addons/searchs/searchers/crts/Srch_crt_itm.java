package gplx.xowa.addons.searchs.searchers.crts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.langs.regxs.*;
public class Srch_crt_itm {
	public Srch_crt_itm(int idx, int tid, Srch_crt_itm[] subs, byte[] raw, Srch_crt_sql raw_data) {
		this.Idx = idx; this.Tid = tid; this.Subs = subs;
		this.Raw = raw;
		this.Sql_data = raw_data;
	}
	public final    int					Idx;		// itm index; EX: "a b" -> a:0 b:1
	public final    int					Tid;
	public final    byte[]				Raw;
	public final    Srch_crt_itm[]		Subs;
	public final    Srch_crt_sql		Sql_data;
	public void Accept_visitor(Srch_crt_visitor visitor) {visitor.Visit(this);}

	public static final int 
	  Tid__word			= 0		// EX: 'A'
	, Tid__and			= 1		// EX: 'A B'
	, Tid__or			= 2		// EX: 'A OR B'
	, Tid__not			= 3		// EX: '-A'
	, Tid__word_quote	= 4		// EX: '"A B"'
	, Tid__invalid		= 5		// EX: 'A OR'; incomplete or otherwise invalid
	;
	public static Srch_crt_itm[] Ary_empty = new Srch_crt_itm[0];
	public static final    Srch_crt_itm Invalid = new Srch_crt_itm(-1, Srch_crt_itm.Tid__invalid, Srch_crt_itm.Ary_empty, null, null);
	public static Srch_crt_itm New_join(int tid, int idx, Srch_crt_itm... ary) {return new Srch_crt_itm(idx, tid, ary, null, Srch_crt_sql.New_or_null(null, Byte_ascii.Null));}
	public static Srch_crt_itm New_word(byte wildcard_byte, Srch_crt_tkn tkn, int idx, byte[] src) {
		int tid = tkn.Tid == Srch_crt_tkn.Tid__word_w_quote ? Srch_crt_itm.Tid__word_quote : Srch_crt_itm.Tid__word;
		return new Srch_crt_itm(idx, tid, Srch_crt_itm.Ary_empty, tkn.Val, Srch_crt_sql.New_or_null(src, wildcard_byte));
	}
}
