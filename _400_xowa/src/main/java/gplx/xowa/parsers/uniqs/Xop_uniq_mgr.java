package gplx.xowa.parsers.uniqs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
import gplx.core.btries.*;
public class Xop_uniq_mgr {	// REF.MW:/parser/StripState.php
	private final    Btrie_slim_mgr general_trie = Btrie_slim_mgr.cs(); private final    Btrie_rv trv = new Btrie_rv();
	private final    Bry_bfr key_bfr = Bry_bfr_.New_w_size(32);
	private int idx = -1;
	public void Clear() {idx = -1; general_trie.Clear();}
	public byte[] Add(byte[] val) {		// "<b>" -> "\u007fUNIQ-item-1--QINU\u007f"
		byte[] key = key_bfr	
			.Add(Bry__uniq__add__bgn)
			.Add_int_variable(++idx)
			.Add(Bry__uniq__add__end).To_bry_and_clear();
		general_trie.Add_bry_bry(key, val);
		return key;
	}
	public byte[] Get(byte[] key) {return (byte[])general_trie.Match_exact(key, 0, key.length);}
	public byte[] Parse(byte[] src) {return Parse(key_bfr, general_trie, src);}
	private byte[] Parse(Bry_bfr bfr, Btrie_slim_mgr trie, byte[] src) {
		int src_len = src.length;
		int pos = 0;
		int mark_bgn = 0;
		boolean dirty = false;
		while (true) {
			boolean is_last = pos == src_len;				
			byte b = is_last ? Byte_ascii.Null : src[pos];
			Object o = trie.Match_at_w_b0(trv, b, src, pos, src_len);
			if (o == null)
				++pos;
			else {
				byte[] val = (byte[])o;
				int new_pos = trv.Pos();	// NOTE: since trie is reused, must capture pos here
				val = Parse(Bry_bfr_.New(), trie, val);
//					val = gplx.xowa.parsers.xndes.Xop_xnde_tkn.Hack_ctx.Wiki().Parser_mgr().Main().Parse_text_to_html(gplx.xowa.parsers.xndes.Xop_xnde_tkn.Hack_ctx, val);	// CHART
				bfr.Add_mid(src, mark_bgn, pos);
				bfr.Add(val);
				dirty = true;
				pos = mark_bgn = new_pos;
			}
			if (is_last) {
				if (dirty)
					bfr.Add_mid(src, mark_bgn, src_len);
				break;
			}
		}
		return dirty ? bfr.To_bry_and_clear() : src;
	}
	public byte[] Uniq_bry_new() {
		return Bry_.Add
		( Bry__uniq__bgn			// "\x7fUNIQ" where "\x7f" is (byte)127
		, Random_bry_new(16));		// random hexdecimal String
	}
	public void Random_int_ary_(int... v) {random_int_ary = v;} private int[] random_int_ary;	// TEST:
	public byte[] Random_bry_new(int len) {
		Bry_bfr key_bfr = Bry_bfr_.New();
		RandomAdp random_gen = RandomAdp_.new_();
		for (int i = 0; i < len; i += 7) {
			int rand = random_int_ary == null ? random_gen.Next(Int_.Max_value) : random_int_ary[i / 7];
			String rand_str = Int_.To_str_hex(Bool_.N, Bool_.Y, rand & 0xfffffff);	// limits value to 268435455
			key_bfr.Add_str_a7(rand_str);
		}
		byte[] rv = key_bfr.To_bry(0, len);
		key_bfr.Clear();
		return rv;
	}
	private final    static byte[] 
	  Bry__uniq__bgn		= Bry_.new_a7("\u007fUNIQ")
	// , Bry__uniq__end		= Bry_.new_a7("-QINU\u007f")
	, Bry__uniq__add__bgn	= Bry_.new_a7("\u007fUNIQ-item-")
	, Bry__uniq__add__end	= Bry_.new_a7("--QINU\u007f")
	;
}
