package gplx.xowa.drds.powers; import gplx.*; import gplx.xowa.*; import gplx.xowa.drds.*;
public class Xod_power_mgr_ {
	public static Xod_power_mgr Instance = new Xod_power_mgr__shim();
}
class Xod_power_mgr__shim implements Xod_power_mgr {
	// private final    Ordered_hash hash = Ordered_hash_.New();
	public void Wake_lock__get(String name) {
		// if (hash.Has(name)) {hash.Clear(); throw Err_.new_("itm exists", "name", name);}
		// hash.Add(name, name);
	}
	public void Wake_lock__rls(String name) {
		// if (!hash.Has(name)) throw Err_.new_("itm missing", "name", name);
		// hash.Del(name);
	}
}
