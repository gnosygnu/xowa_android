package gplx.xowa.bldrs.servers; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.xowa.bldrs.setups.maints.*;
import gplx.xowa.bldrs.servers.jobs.*;
public class Xob_core_server {
	private Xob_wmf_mgr wmf_mgr;
	public Xob_core_server(Xoae_app app) {
		wmf_mgr = new Xob_wmf_mgr(app);
	}
	public void Server_init() {
		// load cmds		bld_all
		// load cses		wiki_updated,commons_updated,wikidata_update
		// load jobs		bld_all,en.wikipedia.org
	}
	public void Server_bgn() {
		while (true) {
			if (   wmf_mgr.Sync()		// new wmf_stats
//					|| jar_mgr.Updated()	// new jar
			) {
//					RunJobs();
			}
		}
		/*
			check wikimedia
			if (change)
				check jobs
				run jobs
		*/
	}
	public void Server_end() {
	}
}
