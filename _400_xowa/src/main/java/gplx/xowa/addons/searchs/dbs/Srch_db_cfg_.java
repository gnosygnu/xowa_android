package gplx.xowa.addons.searchs.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*;
import gplx.dbs.cfgs.*;
public class Srch_db_cfg_ {
	public static Srch_db_cfg New(Db_cfg_tbl cfg_tbl, long page_count) { // NOTE: dflt values are for old search dbs
		int version_id				= cfg_tbl.Assert_int("xowa.search.cfg", "version_id", Srch_db_upgrade.Version__unknown);
		int word_count				= cfg_tbl.Assert_int("xowa.search.cfg", "word_count", 0);
		int link_count_score_max	= cfg_tbl.Assert_int("xowa.search.cfg", "link_count_score_max", 0);
		int link_count_score_cutoff	= cfg_tbl.Assert_int("xowa.search.cfg", "link_count_score_cutoff", 0);
		int link_score_max			= cfg_tbl.Assert_int("xowa.search.cfg", "link_score_max", 0);
		return new Srch_db_cfg(version_id, page_count, word_count, link_count_score_max, link_count_score_cutoff, link_score_max);
	}
	public static void Update_link(Db_cfg_tbl cfg_tbl, Srch_db_cfg cfg, int link_score_max) {
		cfg.Update_link(link_score_max);
		cfg_tbl.Update_int("xowa.search.cfg", "link_score_max", link_score_max);
	}
	public static void Update_word(Db_cfg_tbl cfg_tbl, Srch_db_cfg cfg, int word_count, int link_count_score_max, int link_count_score_cutoff) {
		cfg.Update_word(word_count, link_count_score_max, link_count_score_cutoff);
		cfg_tbl.Update_int("xowa.search.cfg", "version_id", Srch_db_upgrade.Version__link_score);
		cfg_tbl.Update_int("xowa.search.cfg", "word_count", word_count);
		cfg_tbl.Update_int("xowa.search.cfg", "link_count_score_max", link_count_score_max);
		cfg_tbl.Update_int("xowa.search.cfg", "link_count_score_cutoff", link_count_score_cutoff);
	}
	public static final int Link_count_score_cutoff = 300;
}