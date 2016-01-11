package gplx.xowa.specials.xowa.file_browsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.specials.*; import gplx.xowa.specials.xowa.*;
public class Xosp_fbrow_rslt {
	public Xosp_fbrow_rslt(byte[] html_head, byte[] html_body) {this.html_head = html_head; this.html_body = html_body;}
	public byte[] Html_head() {return html_head;} private final byte[] html_head;
	public byte[] Html_body() {return html_body;} private final byte[] html_body;
	public static Xosp_fbrow_rslt err_(String msg) {return new Xosp_fbrow_rslt(Bry_.Empty, Bry_.new_u8(msg));}
}
