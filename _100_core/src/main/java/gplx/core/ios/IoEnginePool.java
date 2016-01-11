package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoEnginePool {
	private final Hash_adp hash = Hash_adp_.new_();
	public void Add_if_dupe_use_nth(IoEngine engine) {
		hash.Del(engine.Key());
		hash.Add(engine.Key(), engine);
	}
	public IoEngine Get_by(String key) {
		IoEngine rv = (IoEngine)hash.Get_by(key); 
		return rv == null ? IoEngine_.Mem : rv; // rv == null when url is null or empty; return Mem which should be a noop; DATE:2013-06-04
	}
	public static final IoEnginePool Instance = new IoEnginePool();
	IoEnginePool() {
		this.Add_if_dupe_use_nth(IoEngine_.Sys);
		this.Add_if_dupe_use_nth(IoEngine_.Mem);
	}
}