package gplx.langs.dsvs; import gplx.*; import gplx.langs.*;
public interface Dsv_fld_parser {
	void Init(byte fld_dlm, byte row_dlm);
	int Parse(Dsv_tbl_parser tbl_parser, Dsv_wkr_base mgr, byte[] src, int pos, int src_len, int fld_idx, int fld_bgn);
}
