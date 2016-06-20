package gplx.xowa.apps.servers.http; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.servers.*;
import gplx.core.primitives.*;
public class Http_server_wkr_pool {
	private final    Ordered_hash hash = Ordered_hash_.New(); private final    Int_obj_ref hash_key = Int_obj_ref.New_neg1();
	public boolean Enabled() {return max != 0;}
	public int Max() {return max;} private int max;
	public int Timeout() {return timeout;} private int timeout;
	public int Len() {return len;} private int len;
	public boolean Full() {return len >= max;}
	public void Init(int max, int timeout) {this.max = max; this.timeout = timeout;}
	public void Add(int uid) {
		if (max == 0) return;	// disabled
		synchronized (hash) {
			Int_obj_ref wkr_key = Int_obj_ref.New(uid);
			hash.Add(wkr_key, wkr_key);
			++len;
		}
	}
	public void Del(int uid) {
		if (max == 0) return;	// disabled
		synchronized (hash) {
			hash.Del(hash_key.Val_(uid));
			--len;
		}
	}
}
