package gplx.core.threads; import gplx.*; import gplx.core.*;
public class Gfo_thread_mgr {
	private final    Ordered_hash hash = Ordered_hash_.New();
	public Gfo_thread_grp Get_by_or_new(String k) {
		Gfo_thread_grp rv = (Gfo_thread_grp)hash.Get_by(k);
		if (rv == null) {
			rv = new Gfo_thread_grp(k);
			hash.Add(k, rv);
		}
		return rv;
	}
	public void Stop_all() {
		int len = hash.Len();
		for (int i = 0; i < len; ++i) {
			Gfo_thread_grp grp = (Gfo_thread_grp)hash.Get_at(i);
			grp.Stop_all();
		}
		hash.Clear();
	}
}