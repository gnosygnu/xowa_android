package gplx.xowa.addons.bldrs.exports.splits.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
import gplx.dbs.*;
import gplx.xowa.addons.bldrs.exports.splits.rslts.*;
class Split_rslt_wkr__org extends Split_rslt_wkr__objs__base {
	@Override public byte Tid() {return Split_rslt_tid_.Tid__fsdb_org;}
	@Override public String Tbl_name() {return "rslt_fsdb_org";}
	@Override public Dbmeta_fld_itm[] Pkey_flds() {
		return new Dbmeta_fld_itm[] {Dbmeta_fld_itm.new_str("orig_ttl", 255), Dbmeta_fld_itm.new_byte("orig_repo")};
	}
}
