package gplx.xowa.addons.servers.https; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.servers.*;
import gplx.xowa.guis.cbks.*; import gplx.core.gfobjs.*;
public class Xog_cbk_wkr__http implements Xog_cbk_wkr {
	public Object Send_json(Xog_cbk_trg trg, String func, Gfobj_nde data) {return null;}
	public void Send_prog(String head) {
		Http_long_poll_cmd.Instance.Send_msg(head);
	}
	public static final    Xog_cbk_wkr__http Instance = new Xog_cbk_wkr__http(); Xog_cbk_wkr__http() {}
}
