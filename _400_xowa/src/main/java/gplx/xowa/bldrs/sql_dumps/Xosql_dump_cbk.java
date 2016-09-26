package gplx.xowa.bldrs.sql_dumps; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.strings.*;
public interface Xosql_dump_cbk {
	void On_fld_done(int fld_idx, byte[] src, int val_bgn, int val_end);
}
