package gplx.core.srls; import gplx.*; import gplx.core.*;
public interface Gfo_srl_mgr_rdr {
	void Itm_bgn(String key);
	void Itm_end();
	boolean			Get_bool	(String key);
	int				Get_int		(String key);
	String			Get_str		(String key);
	Object			Get_subs	(Gfo_srl_ctx ctx, Gfo_srl_itm owner, Gfo_srl_itm proto, Dbmeta_dat_mgr crt_mgr);
}
