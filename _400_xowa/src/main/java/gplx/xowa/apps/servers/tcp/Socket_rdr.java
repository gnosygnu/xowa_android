package gplx.xowa.apps.servers.tcp; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*;
public class Socket_rdr {
	//#{members
	private java.net.ServerSocket server;
	private java.net.Socket client;
	private java.io.InputStream stream;
	//#}
	public IoStream Rdr_stream() {return rdr_stream;} private IoStream rdr_stream = null;
	public int Port() {return port;} private int port;	
	public Socket_rdr Ctor(int port) {this.port = port; return this;}
	public Socket_rdr Open() {
		try {
		//#{Open
//			this.Rls();
			if (server == null) {
				server = new java.net.ServerSocket(port);
				server.setReuseAddress(true);
			}
			client = server.accept();
			client.setSoTimeout(10000);
			stream = client.getInputStream();			
			rdr_stream = new IoStream_stream_rdr().UnderRdr_(stream);
		//#}
			return this;
		}	catch (Exception e) {throw Err_.new_exc(e, "net", "failed to open socket", "port", port);}
	}
	public void Close() {
		try {
		//#{Close
//			if (server != null) server.close();
			if (client != null) client.close();
			if (stream != null) stream.close();
		//#}
		} 	catch (Exception e) {throw Err_.new_exc(e, "net", "failed to close socket", "port", port);}
	}
	public void Rls() {
		try {
		//#{Rls
			if (server != null) server.close();
			if (client != null) client.close();
			if (stream != null) stream.close();
		//#}
		} 	catch (Exception e) {throw Err_.new_exc(e, "net", "failed to rls socket", "port", port);}
	}
}
