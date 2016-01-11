package gplx.xowa.wikis.ttls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
import gplx.xowa.wikis.nss.*;
public interface Xow_ttl_parser {
	Xoa_ttl Ttl_parse(byte[] ttl);
	Xoa_ttl Ttl_parse(byte[] src, int src_bgn, int src_end);
	Xoa_ttl Ttl_parse(int ns_id, byte[] ttl);
	Xow_ns_mgr Ns_mgr();
}
