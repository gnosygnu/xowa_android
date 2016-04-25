package gplx.xowa.addons.servers.https; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.servers.*;
public class Http_send_msg_cmd implements gplx.xowa.htmls.bridges.Bridge_cmd_itm {
	public String Exec(gplx.langs.jsons.Json_nde data) {
		gplx.langs.jsons.Json_nde jnde = (gplx.langs.jsons.Json_nde)data.Get_as_itm_or_null(Bry_.new_a7("msg"));
		Http_long_poll_cmd.Instance.Send_msg(jnde.Print_as_json());
		return "{}";
	}

	public byte[] Key() {return BRIDGE_KEY;}
	public static final    byte[] BRIDGE_KEY = Bry_.new_a7("send_msg");
}
