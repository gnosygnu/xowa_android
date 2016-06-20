package gplx.xowa.addons.bldrs.centrals.cmds; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.core.progs.*; import gplx.core.gfobjs.*;
public interface Xobc_cmd_itm extends Gfo_prog_ui, Gfo_invk {
	int			Task_id();
	int			Step_id();
	int			Cmd_id();
	String		Cmd_type();			// "xowa.core.http_download"
	String		Cmd_name();			// "download"
	boolean		Cmd_suspendable();	// "true"
	String		Cmd_uid();			// for thread_pool_mgr: "0:0:0"
	void		Cmd_cleanup();
	String		Cmd_fallback();
	void		Cmd_clear();

	Gfobj_nde	Save_to(Gfobj_nde nde);
	void		Load_checkpoint();
}
