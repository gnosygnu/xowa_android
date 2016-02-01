package gplx.xowa.wikis.data; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
class Xowd_db_file_hash {
	private final Ordered_hash hash = Ordered_hash_.New();
	public int Count_total() {return count_total;} private int count_total;
	public void Clear() {hash.Clear(); count_total = 0;}
	public void Del(Xowd_db_file file) {
		Ordered_hash tids = (Ordered_hash)hash.Get_by(file.Tid());
		if (tids == null) throw Err_.new_wo_type("unknown file.tid", "url", file.Url());
		if (!tids.Has(file.Id())) throw Err_.new_wo_type("unknown file.id", "url", file.Url());
		tids.Del(file.Id());
		--count_total;
	}
	public void Add_or_new(Xowd_db_file file) {
		byte tid = file.Tid();
		Ordered_hash tids = (Ordered_hash)hash.Get_by(tid);
		if (tids == null) {
			tids = Ordered_hash_.New();
			hash.Add(tid, tids);
		}
		tids.Add(file.Id(), file);
		++count_total;
	}
	public int Count_of_tid(byte tid) {
		Ordered_hash tids = (Ordered_hash)hash.Get_by(tid);
		return tids == null ? 0 : tids.Count();
	}
}