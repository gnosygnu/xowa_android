package gplx.xowa.xtns.pagebanners; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Pb_xtn extends Xox_mgr_base implements GfoInvkAble {
	@Override public boolean Enabled_default() {return true;}
	@Override public byte[] Xtn_key() {return Xtn_key_static;} public static final byte[] Xtn_key_static = Bry_.new_a7("graph");
	@Override public Xox_mgr Clone_new() {return new Pb_xtn();}
	@Override public void Xtn_init_by_wiki(Xowe_wiki wiki) {}
	public void Xtn_init_assert(Xowe_wiki wiki) {}
}
