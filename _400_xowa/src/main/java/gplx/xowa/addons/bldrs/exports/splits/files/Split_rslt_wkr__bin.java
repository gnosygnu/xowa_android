package gplx.xowa.addons.bldrs.exports.splits.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
import gplx.xowa.addons.bldrs.exports.splits.rslts.*;
class Split_rslt_wkr__bin extends Split_rslt_wkr__int__base {
	@Override public byte Tid() {return Split_rslt_tid_.Tid__fsdb_bin;}
	@Override public String Tbl_name() {return "rslt_fsdb_bin";}
	@Override public String Pkey_name() {return "bin_owner_id";}
}
