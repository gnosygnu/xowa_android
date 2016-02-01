package gplx.xowa.apps.servers.tcp; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
import gplx.core.ios.*;
public class Xosrv_socket_rdr implements GfoInvkAble {
	private Socket_rdr rdr = new Socket_rdr();
	public int Port() {return port;} private int port;
	public void Init(Xosrv_server server, int port) {this.server = server; this.port = port;} private Xosrv_server server;
	public void Start() {
		rdr = new Socket_rdr();
		try {
			rdr.Ctor(port);
			while (true) {
				rdr.Open();
				IoStream rdr_stream = rdr.Rdr_stream();
				Xosrv_msg_rdr msg_rdr = new Xosrv_msg_rdr(new byte[24], rdr_stream);
				Xosrv_msg msg = msg_rdr.Read();
				if (msg == Xosrv_msg.Exit) continue;
				server.Msg_rcvd(msg);
				rdr.Close();
			}
		}	catch (Exception e) {Err_.Noop(e);}
		finally {rdr.Rls();}
	}
	public void Rls() {
		rdr.Rls();
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_start))	this.Start();
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	public static final String Invk_start = "start";
}