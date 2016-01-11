package gplx.core.intls; import gplx.*; import gplx.core.*;
public interface Gfo_case_mgr {
	byte Tid();
	Gfo_case_itm Get_or_null(byte bgn_byte, byte[] src, int bgn, int end);
}
