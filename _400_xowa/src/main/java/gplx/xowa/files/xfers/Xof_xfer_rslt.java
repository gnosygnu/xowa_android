package gplx.xowa.files.xfers; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public class Xof_xfer_rslt {
	public boolean Pass() {return pass;} private boolean pass = true;
	public String Err_msg() {return err_msg;} private String err_msg = String_.Empty;
	public String Src() {return src;} private String src;
	public Io_url Trg() {return trg;} public Xof_xfer_rslt Trg_(Io_url v) {trg = v; return this;}  Io_url trg;
	public void Atrs_src_trg_(String src, Io_url trg) {this.src = src; this.trg = trg;}		
	public boolean Fail(String msg) {
		pass = false;
		err_msg = msg;
		return false;
	}
	public void Clear() {pass = true; err_msg = src = String_.Empty; trg = Io_url_.Empty;}
}
