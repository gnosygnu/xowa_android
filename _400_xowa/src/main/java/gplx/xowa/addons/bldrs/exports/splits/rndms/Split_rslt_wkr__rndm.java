package gplx.xowa.addons.bldrs.exports.splits.rndms; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
import gplx.dbs.*; import gplx.xowa.addons.bldrs.exports.splits.rslts.*;
class Split_rslt_wkr__rndm extends Split_rslt_wkr__objs__base {
	@Override public byte Tid() {return Split_rslt_tid_.Tid__rndm;}
	@Override public String Tbl_name() {return "rslt_rndm";}
	@Override public Dbmeta_fld_itm[] Pkey_flds() {
		return new Dbmeta_fld_itm[] {Dbmeta_fld_itm.new_int("mgr_idx"), Dbmeta_fld_itm.new_int("rng_idx"), Dbmeta_fld_itm.new_int("seq_idx")};
	}
}
