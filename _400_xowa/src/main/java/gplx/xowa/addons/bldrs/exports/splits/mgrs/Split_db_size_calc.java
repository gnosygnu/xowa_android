package gplx.xowa.addons.bldrs.exports.splits.mgrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.exports.*; import gplx.xowa.addons.bldrs.exports.splits.*;
public class Split_db_size_calc {
	private final    long size_max;
	private long size_cur;		
	public Split_db_size_calc(long size_max, int idx) {
		this.size_max = size_max;
		this.idx = idx;
	}
	public int Idx() {return idx;} private int idx;
	public int Size_cur_add_(int v) {
		long size_new = size_cur + v;
		if (size_new > size_max) {
			++idx;
			size_cur = 0;
		}
		else
			size_cur = size_new;
		return idx;
	}
}
