package gplx.xowa.bldrs; import gplx.*; import gplx.xowa.*;
public interface Xob_cmd extends GfoInvkAble {
	String Cmd_key();
	void Cmd_init(Xob_bldr bldr);
	void Cmd_bgn(Xob_bldr bldr);
	void Cmd_run();
	void Cmd_end();
	void Cmd_term();
}
