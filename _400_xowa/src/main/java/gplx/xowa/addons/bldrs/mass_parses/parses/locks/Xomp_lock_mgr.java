package gplx.xowa.addons.bldrs.mass_parses.parses.locks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.mass_parses.*; import gplx.xowa.addons.bldrs.mass_parses.parses.*;
public interface Xomp_lock_mgr {
	void Remake();
	int Uid_prv__get(String machine_name);
	void Uid_prv__rls(String machine_name, int uid_prv);
}