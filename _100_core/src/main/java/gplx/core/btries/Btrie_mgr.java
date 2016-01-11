package gplx.core.btries; import gplx.*; import gplx.core.*;
public interface Btrie_mgr {
	int Match_pos();
	Object Match_bgn(byte[] src, int bgn_pos, int end_pos);
	Btrie_mgr Add_obj(String key, Object val);
	Btrie_mgr Add_obj(byte[] key, Object val);
}
