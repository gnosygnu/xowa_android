package gplx.core.srls; import gplx.*; import gplx.core.*;
public interface Gfo_srl_itm {
	Gfo_srl_itm		Make_new(Gfo_srl_ctx ctx);
	void			Load(Gfo_srl_ctx ctx, Gfo_srl_itm owner, Gfo_srl_mgr_rdr rdr);
}
