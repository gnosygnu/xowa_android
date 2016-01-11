package gplx.xowa.htmls.heads; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.langs.jsons.*;
import gplx.xowa.apps.servers.http.*;
public class Xoh_head_itm__server extends Xoh_head_itm__base {
	@Override public byte[] Key() {return Xoh_head_itm_.Key__server;}
	@Override public int Flags() {return Flag__js_tail_script;}
	private Http_data__client client_data;
	public Xoh_head_itm__server Init_by_http(Http_data__client client_data) {this.client_data = client_data; return this;}
	@Override public void Write_js_tail_script(Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xoh_head_wtr wtr) {
		wtr.Write_js_init_global(Client_key);
		wtr.Write_js_alias_var	(Client_alias, Client_key);
		wtr.Write_js_alias_kv	(Client_alias, Key__server_host	, client_data.Server_host());
		wtr.Write_js_alias_kv	(Client_alias, Key__ip_address	, client_data.Ip_address());
	}
	private static final byte[] Client_key = Bry_.new_a7("xowa.client"), Client_alias = Bry_.new_a7("x_c");
	private static final byte[] Key__server_host = Bry_.new_a7("server_host"), Key__ip_address = Bry_.new_a7("ip_address");
}
