package gplx.xowa.xtns.pfuncs.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
public class Pfunc_filepath_itm implements Rls_able {
	public Pfunc_filepath_itm(boolean exists, byte[] page_url) {this.exists = exists; this.page_url = page_url;}
	public boolean Exists() {return exists;} private final    boolean exists;
	public byte[] Page_url() {return page_url;} private final    byte[] page_url;
	public void Rls() {}
}
