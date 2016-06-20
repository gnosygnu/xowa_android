package gplx.xowa.bldrs.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
public interface Xob_cmd extends Gfo_invk {
	String		Cmd_key();
	Xob_cmd		Cmd_clone(Xob_bldr bldr, Xowe_wiki wiki);
	void		Cmd_init(Xob_bldr bldr);
	void		Cmd_bgn(Xob_bldr bldr);
	void		Cmd_run();
	void		Cmd_end();
	void		Cmd_term();
}
