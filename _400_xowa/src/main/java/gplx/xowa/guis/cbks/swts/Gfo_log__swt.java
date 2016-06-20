package gplx.xowa.guis.cbks.swts; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*; import gplx.xowa.guis.cbks.*;
import gplx.core.logs.*; import gplx.core.gfobjs.*; import gplx.xowa.guis.cbks.*;
public class Gfo_log__swt extends Gfo_log__file {	//#*inherit
	private final    Xog_cbk_mgr cbk_mgr;
	public Gfo_log__swt(Xog_cbk_mgr cbk_mgr, Io_url url, Gfo_log_itm_wtr fmtr) {super(url, fmtr);this.cbk_mgr = cbk_mgr;}
	@Override public void Exec(byte type, long time, long elapsed, String msg, Object[] args) {
		if (type == Gfo_log_itm.Type__prog) return;
		super.Exec(type, time, elapsed, msg, args);
		Gfobj_nde nde = Gfobj_nde.New().Add_str("msg", msg);
		int args_len = args.length;
		for (int i = 0; i < args_len; i += 2) {
			String key = Object_.Xto_str_strict_or_null_mark(args[i]);
			Object val = i + 1 < args_len ? args[i + 1] : "<<NULL>>";
			nde.Add_str(key, Object_.Xto_str_strict_or_null_mark(val));
		}
		cbk_mgr.Send_json(Xog_cbk_trg.Any, "xo.log.add__recv", nde);
	}
}
