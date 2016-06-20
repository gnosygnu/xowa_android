package gplx.xowa.addons.bldrs.exports.merges; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*;
import gplx.dbs.*; import gplx.xowa.addons.bldrs.exports.utls.*;
interface Merge2_wkr {
	Split_tbl	Tbl();
	void		Merge_data(Merge_ctx ctx, Merge_prog_wkr prog_wkr);
}
