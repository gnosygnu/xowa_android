package gplx.xowa.bldrs.servers; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.xowa.bldrs.setups.maints.*;
public class Xob_wmf_mgr {
	private Xoa_maint_mgr maint_mgr;
	public Xob_wmf_mgr(Xoae_app app) {
		maint_mgr = new Xoa_maint_mgr(app);
	}
	public int Sync_interval() {return sync_interval;} public Xob_wmf_mgr Sync_interval_(int v) {sync_interval = v; return this;} private int sync_interval = 60 * 24; // in minutes
	public DateAdp Sync_previous() {return sync_previous;} public Xob_wmf_mgr Sync_previous_(DateAdp v) {sync_previous = v; return this;} private DateAdp sync_previous = DateAdp_.MinValue;
	public boolean Sync() {
		maint_mgr.Wmf_status_update();
		/*
		download dumps
		calculate times
		*/
		return false;
	}
}
