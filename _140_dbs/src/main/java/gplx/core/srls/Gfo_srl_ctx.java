package gplx.core.srls; import gplx.*; import gplx.core.*;
public interface Gfo_srl_ctx {
	Gfo_srl_mgr_wtr Wtr_bgn(String key);
	Gfo_srl_mgr_rdr Rdr_bgn(String key);
	Dbmeta_dat_mgr Rdr_subs(String key);
}
