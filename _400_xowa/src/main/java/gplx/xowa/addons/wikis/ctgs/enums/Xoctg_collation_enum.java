package gplx.xowa.addons.wikis.ctgs.enums; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.ctgs.*;
import gplx.core.btries.*;
public class Xoctg_collation_enum {
	private final    Btrie_rv trv = new Btrie_rv();
	private final    Btrie_slim_mgr trie = Btrie_slim_mgr.cs()
		.Add_str_byte("uppercase"	, Tid__uppercase)
		.Add_str_byte("uca"			, Tid__uca);
	public byte To_tid_or_fail(byte[] raw) {
		byte tid = trie.Match_byte_or(trv, raw, 0, raw.length, Byte_.Max_value_127);
		if (tid == Byte_.Max_value_127) throw Err_.new_unhandled_default(raw);
		return tid;
	}
	public static final byte Tid__uppercase = 1, Tid__uca = 3;
}
