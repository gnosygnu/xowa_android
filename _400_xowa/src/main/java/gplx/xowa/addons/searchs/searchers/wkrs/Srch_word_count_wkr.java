package gplx.xowa.addons.searchs.searchers.wkrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.dbs.*; import gplx.dbs.percentiles.*;
import gplx.xowa.addons.searchs.dbs.*; import gplx.xowa.addons.searchs.searchers.crts.*;
class Srch_word_count_wkr extends Percentile_select_base {
	private Srch_word_tbl word_tbl;
	private Srch_crt_node sub;
	private Srch_db_cfg db_cfg;
	private int total_link_count;
	private int rows_read;
	private boolean score_too_low;
	public int Get_top_10(Srch_ctx ctx, Srch_word_tbl word_tbl, Srch_crt_node sub) {
		super.cxl = ctx.Cxl;
		this.db_cfg = ctx.Addon.Db_mgr().Cfg();
		super.rng = new Percentile_rng().Init(db_cfg.Word_count(), db_cfg.Link_count_score_max());
		super.rng.Select_init(10, 0);
		super.rng_log = new Percentile_rng_log(db_cfg.Link_count_score_max());

		rng_log.Init(sub.raw, 10, 0);
		this.word_tbl = word_tbl;
		this.sub = sub;
		this.total_link_count = 0;
		this.rows_read = 0;
		this.score_too_low = false;
		this.Select();
		if		(score_too_low)		return Srch_db_cfg_.Link_count_score_cutoff;
		else if	(rows_read == 1)	return total_link_count * 2;
		else						return total_link_count;
	}
	@Override protected Db_rdr Rdr__init(Db_attach_mgr attach_mgr) {
		return word_tbl.conn.Exec_rdr(Fmt__main.Bld_many_to_str_auto_bfr(rng.Score_bgn(), rng.Score_end(), sub.raw_rng_bgn, sub.raw_rng_end));
	}
	@Override protected boolean Row__read(Db_rdr rdr) {
		if (!rdr.Move_next()) return false;
		int cur_link_count = rdr.Read_int(word_tbl.fld_link_count);
		total_link_count += cur_link_count;
		++rows_read;
		return true;
	}
	@Override protected boolean Select__finished() {
		if (rng.Score_bgn() <= db_cfg.Link_count_score_cutoff()) score_too_low = true;
		return rows_read > 0 || score_too_low;
	}
	@Override protected void Rdr__done(boolean last_rdr) {
		if (last_rdr)
			gplx.core.consoles.Console_adp__sys.Instance.Write_str(rng_log.To_str_and_clear());
	}
	private static Bry_fmt
	  Fmt__main = Bry_fmt.Auto(String_.Concat_lines_nl_skip_last
	( "SELECT  w.link_count"
	, "FROM    search_word w INDEXED BY search_word__link_count_score__word_text"
	, "WHERE   w.link_count_score >= ~{score_min}"
	, "AND     w.link_count_score <  ~{score_max}"
	, "AND     w.word_text >= '~{rng_bgn}'"
	, "AND     w.word_text <  '~{rng_end}'"		
	, "LIMIT 1"
	));
}
