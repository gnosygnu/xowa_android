package gplx.xowa.xtns; import gplx.*; import gplx.xowa.*;
public interface Xox_mgr extends Gfo_invk {
	byte[]		Xtn_key();
	void		Xtn_ctor_by_app(Xoae_app app);
	void		Xtn_ctor_by_wiki(Xowe_wiki wiki);
	void		Xtn_init_by_app(Xoae_app app);
	void		Xtn_init_by_wiki(Xowe_wiki wiki);
	Xox_mgr		Xtn_clone_new();
}
