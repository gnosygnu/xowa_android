package gplx.xowa.xtns.imaps.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.imaps.*;
public class Imap_err {
	public Imap_err(int itm_idx, String err_msg) {this.itm_idx = itm_idx; this.err_msg = err_msg;}
	public int		Itm_idx() {return itm_idx;} private final    int itm_idx;
	public String	Err_msg() {return err_msg;} private final    String err_msg;
}
