package gplx.core.net; import gplx.*; import gplx.core.*;
//#{imports
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
//#}
public class Server_socket_adp__base implements Server_socket_adp {
	//#{members
	private ServerSocket server_socket;
	//#}
	public Server_socket_adp Ctor(int port) {
		//#{Init
		try {
			this.server_socket = new ServerSocket();
			server_socket.setReuseAddress(true);			
			server_socket.bind(new InetSocketAddress(port));
		}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Get_input_stream failed");}
		return this;
		//#}
	}
	public Socket_adp Accept() {
		//#{Accept
		Socket client_socket = null;
		try {client_socket = server_socket.accept();}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Get_input_stream failed");}
		return new Socket_adp__base(client_socket);
		//#}
	}
	public void Rls() {
		//#{Rls
		try {server_socket.close();} 
		catch (IOException e) {throw Err_.new_exc(e, "net", "Rls failed");}
		//#}
	}
}
