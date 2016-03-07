package gplx.xowa.addons.searchs.searchers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.xowa.addons.searchs.searchers.crts.*;
public class Srch2_qry {
	public Srch2_qry(Srch_ns_mgr ns_mgr, byte[] raw, int request_bgn, int request_end) {
		this.Ns_mgr = ns_mgr;
		this.Raw = raw;
		this.Request_bgn = request_bgn; this.Request_end = request_end; this.Request_len = request_end - request_bgn;
	}
	public final Srch_ns_mgr			Ns_mgr;
	public final byte[]				Raw;
	public final int					Request_bgn;
	public final int					Request_end;
	public final int					Request_len;
	public Srch2_crt_node				Crt;
}
