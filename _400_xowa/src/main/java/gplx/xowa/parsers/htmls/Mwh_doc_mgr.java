package gplx.xowa.parsers.htmls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
class Mwh_doc_mgr {
	private final int data_max_orig;				
	public Mwh_doc_mgr(int max) {
		this.data_max_orig = max * Idx__mult;
		this.Max_(max);
	}
	public int Len() {return itm_len;} private int itm_len;
	public int[] Data_ary() {return data_ary;} private int[] data_ary; private int data_max;
	private void Max_(int len) {
		this.data_max = len * Idx__mult;
		this.data_ary = new int[data_max];
		this.itm_len = 0;
	}
	public void Clear() {
		if (data_max == data_max_orig)
			itm_len = 0;
		else
			Max_(data_max_orig / Idx__mult);
	}
	public int Add(int dom_tid, int src_bgn, int src_end) {
		int data_idx = itm_len * Idx__mult;
		if (data_idx == data_max) {
			int new_data_max = data_max == 0 ? Idx__mult : data_max * 2;
			int[] new_data_ary = new int[new_data_max];
			Int_.Ary_copy_to(data_ary, data_max, data_ary);
			this.data_ary = new_data_ary;
			this.data_max = new_data_max;
		}
		int dom_uid = itm_len;
		data_ary[data_idx + Idx_dom_uid] = dom_uid;
		data_ary[data_idx + Idx_dom_tid] = dom_tid;
		data_ary[data_idx + Idx_src_bgn] = src_bgn;
		data_ary[data_idx + Idx_src_end] = src_end;
		++itm_len;
		return dom_uid;
	}
	public static final int
	  Idx_dom_uid			=  0
	, Idx_dom_tid			=  1
	, Idx_src_bgn			=  2
	, Idx_src_end			=  3
	, Idx__mult				=  4
	;
}
