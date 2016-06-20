package gplx.xowa.apps.servers.tcp; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
public class Xosrv_socket_wtr {
	public String Host() {return host;} private String host = "localhost";
	public int Port() {return port;} private int port;
	private Socket_wtr wtr; private Bry_bfr msg_bfr = Bry_bfr_.Reset(4 * Io_mgr.Len_kb);
	public void Init(String host, int port) {this.host = host; this.port = port; wtr = new Socket_wtr().Ctor(host, port);}
	public void Write(Xosrv_msg msg) {
		wtr.Open();
		msg.Print(msg_bfr);
		byte[] msg_bry = msg_bfr.To_bry_and_clear();
		wtr.Write(msg_bry);
		wtr.Close();
	}
	public void Rls() {
		wtr.Rls();
	}
}
