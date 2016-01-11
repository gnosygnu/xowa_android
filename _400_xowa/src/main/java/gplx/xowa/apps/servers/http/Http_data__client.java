package gplx.xowa.apps.servers.http; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
import gplx.langs.jsons.*;
public class Http_data__client {
	public Http_data__client(byte[] server_host, String ip_address_str) {
		this.server_host = server_host;
		this.ip_address = Bry_.new_a7(ip_address_str);
	}
	public byte[] Server_host() {return server_host;} private final byte[] server_host;
	public byte[] Ip_address() {return ip_address;} private final byte[] ip_address;
}
