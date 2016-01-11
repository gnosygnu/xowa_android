package gplx.core.net; import gplx.*; import gplx.core.*;
public class Local_host_ {
	public static String Ip_address() {
		try {return java.net.InetAddress.getLocalHost().getHostAddress();}	//#<>"127.0.0.1"~java.net.InetAddress.getLocalHost().getHostAddress()
		catch (Exception e) {throw Err_.new_exc(e, "net", "ip_address failed");}
	}
}
