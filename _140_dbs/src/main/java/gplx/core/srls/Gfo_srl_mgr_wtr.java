package gplx.core.srls; import gplx.*; import gplx.core.*;
public interface Gfo_srl_mgr_wtr {
	int Uid_next__as_int();
	void Itm_bgn(String key);
	void Itm_end();
	void Set_bool	(String key, boolean val);
	void Set_int	(String key, int val);
	void Set_str	(String key, String val);
}
