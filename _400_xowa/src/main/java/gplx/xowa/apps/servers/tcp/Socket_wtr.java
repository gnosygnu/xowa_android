package gplx.xowa.apps.servers.tcp; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
public class Socket_wtr {
	private String host;
	private int port;
	//#{members
	private java.net.Socket socket;
	private java.io.OutputStream stream;
	//#}
	public Socket_wtr Ctor(String host, int port) {this.host = host; this.port = port; return this;}
	public Socket_wtr Open() {
		try {
		//#{Open
			this.Rls();
			socket = new java.net.Socket(host, port);
			socket.setSoTimeout(10000);
			stream = socket.getOutputStream();
		//#}
			return this;
		}	catch (Exception e) {throw Err_.new_exc(e, "net", "failed to open socket", "host", host, "port", port);}
	}
	public void Write(byte[] bry) {
		try {
		//#{Write
			stream.write(bry, 0, bry.length);
		//#}
		}	catch (Exception e) {throw Err_.new_exc(e, "net", "failed to write stream", "host", host, "port", port);}
	}
	public void Close() {
		try {
		//#{Close
			if (stream != null) stream.close();
			if (socket != null) socket.close();
		//#}
		} 	catch (Exception e) {throw Err_.new_exc(e, "net", "failed to close socket", "host", host, "port", port);}
	}
	public void Rls() {
		try {
		//#{Rls
			if (stream != null) stream.close();
			if (socket != null) socket.close();
		//#}
		} 	catch (Exception e) {throw Err_.new_exc(e, "net", "failed to release socket", "host", host, "port", port);}
	}
}
