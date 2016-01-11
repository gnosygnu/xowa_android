package gplx.core.net; import gplx.*; import gplx.core.*;
public interface Http_server_wtr {
	void Write_str_w_nl(String s);
}
class Http_server_wtr__noop implements Http_server_wtr {
	public void Write_str_w_nl(String s) {}
}
