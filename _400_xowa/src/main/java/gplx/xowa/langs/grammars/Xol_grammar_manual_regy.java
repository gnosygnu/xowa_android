package gplx.xowa.langs.grammars; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public class Xol_grammar_manual_regy {
	private Hash_adp_bry[] ary = new Hash_adp_bry[Xol_grammar_.Tid__max];
	public byte[] Itms_get(byte type_tid, byte[] word) {
		Hash_adp_bry hash = ary[type_tid]; if (hash == null) return null;
		return (byte[])hash.Get_by_bry(word);
	}
	public Xol_grammar_manual_regy Itms_add(byte type_tid, String orig, String repl) {
		Hash_adp_bry hash = ary[type_tid];
		if (hash == null) {
			hash = Hash_adp_bry.ci_a7();	// ASCII:currently only being used for Wikiuutiset; DATE:2014-07-07
			ary[type_tid] = hash;
		}
		hash.Add_str_obj(orig, Bry_.new_a7(repl));
		return this;
	}
}
