package gplx.xowa.drds.powers; import gplx.*; import gplx.xowa.*; import gplx.xowa.drds.*;
public interface Xod_power_mgr {
	void Wake_lock__get(String name);
	void Wake_lock__rls(String name);
}
