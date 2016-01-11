package gplx.xowa.parsers; import gplx.*; import gplx.xowa.*;
public interface Xop_ctx_wkr {
	void Ctor_ctx(Xop_ctx ctx);
	void Page_bgn(Xop_ctx ctx, Xop_root_tkn root);
	void Page_end(Xop_ctx ctx, Xop_root_tkn root, byte[] src, int src_len);
}
