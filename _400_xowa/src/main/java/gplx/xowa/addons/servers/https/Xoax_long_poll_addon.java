package gplx.xowa.addons.servers.https; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.servers.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.htmls.bridges.*;
public class Xoax_long_poll_addon implements Xoax_addon_itm, Xoax_addon_itm__json {
	public Bridge_cmd_itm[] Json_cmds() {
		return new Bridge_cmd_itm[]
		{ Http_long_poll_cmd.Instance
		, new Http_send_msg_cmd()
		};
	}

	public String Addon__key() {return "xowa.servers.https.long_poll";}
}
