package gplx.core.net; import gplx.*; import gplx.core.*;
//#{imports
import java.io.IOException;
import java.net.*;
//#}
public class Socket_adp__base implements Socket_adp {
	//#{members
	private final Socket socket;
	public Socket_adp__base(Socket socket) {this.socket = socket;}
	//#}
	public String Ip_address() {
		//#{Ip_address
		return socket.getRemoteSocketAddress().toString();
		//#}
	}
	public Object Get_input_stream() {
		//#{Get_input_stream
		try {return socket.getInputStream();}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Get_input_stream failed");}
		//#}
	}
	public Object Get_output_stream() {
		//#{Get_output_stream
		try {return socket.getOutputStream();}
		catch (IOException e) {throw Err_.new_exc(e, "net", "Get_output_stream failed");}
		//#}
	}
	public void Rls() {
		//#{Rls
		try {socket.close();} 
		catch (IOException e) {throw Err_.new_exc(e, "net", "Rls failed");}
		//#}
	}
}
