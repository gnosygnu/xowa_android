package gplx.core.caches; import gplx.*; import gplx.core.*;
import gplx.core.consoles.*; import gplx.core.envs.*;
public class Gfo_cache_mgr {
	private Ordered_hash hash = Ordered_hash_.New_bry();
	private Ordered_hash recent = Ordered_hash_.New_bry();
	public int Max_size() {return max_size;} public Gfo_cache_mgr Max_size_(int v) {max_size = v; return this;} private int max_size;
	public int Reduce_by() {return reduce_by;} public Gfo_cache_mgr Reduce_by_(int v) {reduce_by = v; return this;} private int reduce_by;
	public int Cur_size() {return cur_size;} private int cur_size;
	public int Count() {return hash.Count();}
	public void Clear() {hash.Clear(); recent.Clear(); cur_size = 0;}
	@gplx.Internal protected Object Get_at(int i) {
		Gfo_cache_data rv = (Gfo_cache_data)hash.Get_at(i);
		return rv.Val();
	}
	public Object Get_by_key(byte[] key) {
		Object o = hash.Get_by(key); if (o == null) return null;
		Gfo_cache_data rv = (Gfo_cache_data)o;
		rv.Timestamp_update();
		Object recent_itm = recent.Get_by(key);
		if (recent_itm == null) recent.Add(key, rv);
		return rv.Val();
	}
	public void Del(byte[] key) {
		Object o = hash.Get_by(key); if (o == null) return;
		Gfo_cache_data itm = (Gfo_cache_data)o;
		cur_size -= itm.Size();
		hash.Del(itm.Key());
		itm.Rls();
	}
	public void Add_replace(byte[] key, Rls_able val, int size) {
//			Del(key);
//			Add(key, val, size);
		Object o = hash.Get_by(key);
		if (o == null)
			Add(key, val, size);
		else {
			Gfo_cache_data itm = (Gfo_cache_data)o;
			cur_size -= itm.Size();
			cur_size += size;
			itm.Replace(val, size);
		}
	}
	public void Add(byte[] key, Rls_able val, int size) {
//			if (cur_size + size > 600000000) ReduceCache();
		cur_size += size;
//			++cur_size;
		Gfo_cache_data itm = new Gfo_cache_data(key, val, size);
		hash.Add(key, itm);
	}
	public void Reduce_recent() {
//			Console_adp__sys.Instance.WriteLine("reducing");
		int len = recent.Count();
		for (int i = 0; i < len; i++) {
			Gfo_cache_data itm = (Gfo_cache_data)recent.Get_at(i);
			itm.Rls();	// releases root
		}
		recent.Clear();
	}
	public void Reduce_cache() {
		Console_adp__sys.Instance.Write_str_w_nl("compacting:");			
//			hash.Sort();
//			int len = hash.Count();
//			List_adp deleted = List_adp_.new_();
//			int deleted_size = 0, deleted_count = 0;
//			for (int i = 0; i < len; i++) {
//				Gfo_cache_data itm = (Gfo_cache_data)hash.Get_at(i);
//				int new_deleted_size = deleted_size + 1;//itm.Size();
//				if (new_deleted_size > 4000 && deleted_count > 0) break;
//				deleted.Add(itm);
//				deleted_count++;
//				deleted_size = new_deleted_size;
//			}
//			len = deleted.Count();
//			for (int i = 0; i < len; i++) {
//				Gfo_cache_data itm = (Gfo_cache_data)deleted.Get_at(i);
//				cur_size --;
//				hash.Del(bry_ref.Val_(itm.Key()));
//				itm.Rls();
//			}
//			deleted.Clear();

		int len = hash.Count();
		for (int i = 0; i < len; i++) {
			Gfo_cache_data itm = (Gfo_cache_data)hash.Get_at(i);
//				hash.Del(bry_ref.Val_(itm.Key()));
			itm.Rls();
		}
	}
}
class Gfo_cache_data implements gplx.CompareAble, Rls_able {
	public Gfo_cache_data(byte[] key, Rls_able val, int size) {this.key = key; this.val = val; this.size = size; this.timestamp = Env_.TickCount();}
	public byte[] Key() {return key;} private byte[] key;
	public Rls_able Val() {return val;} private Rls_able val;
	public int Size() {return size;} private int size;
	public void Replace(Rls_able val, int size) {this.val = val; this.size = size;}
	public long Timestamp() {return timestamp;} public void Timestamp_update() {timestamp = Env_.TickCount();} private long timestamp;
	public int compareTo(Object obj) {
		Gfo_cache_data comp = (Gfo_cache_data)obj;
		return Long_.Compare(timestamp, comp.timestamp);
	}
	public void Rls() {
		val.Rls();
//			val = null;
//			key = null;
	}
}
