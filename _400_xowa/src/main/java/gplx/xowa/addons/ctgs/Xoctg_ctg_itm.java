package gplx.xowa.addons.ctgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*;
public class Xoctg_ctg_itm {
	public Xoctg_ctg_itm(byte[] ttl_wo_ns, int pages, int subcs, int files) {
		this.Ttl_wo_ns = ttl_wo_ns;
		this.Pages = pages;
		this.Subcs = subcs;
		this.Files = files;
		this.All = pages + subcs  + files;
	}
	public final byte[] Ttl_wo_ns;
	public final int Pages;
	public final int Subcs;
	public final int Files;
	public final int All;
}
