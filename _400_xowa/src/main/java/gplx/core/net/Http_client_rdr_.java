package gplx.core.net; import gplx.*; import gplx.core.*;
public class Http_client_rdr_ {
	public static Http_client_rdr new_stream()	{return new Http_client_rdr__stream();}
	public static Http_client_rdr new_mem()		{return new Http_client_rdr__mem();}
}
class Http_client_rdr__mem implements Http_client_rdr {
	private String[] ary; private int ary_len; private int idx;
	public void Stream_(Object o) {
		this.ary = (String[])o;
		this.ary_len = ary.length;
		this.idx = 0;
	}
	public String Read_line() {
		return idx == ary_len ? null : ary[idx++];
	}
	public byte[] Read_line_as_bry() {return Bry_.new_u8(Read_line());}
	public void Rls() {}
}
