package gplx.dbs.percentiles; import gplx.*; import gplx.dbs.*;
public abstract class Percentile_select_base {	// SELECT * FROM x ORDER BY y LIMIT 10;
	protected Cancelable cxl;
	protected Percentile_rng rng;
	protected final Db_attach_mgr attach_mgr = new Db_attach_mgr();
	protected Percentile_rng_log rng_log;
	protected void Select() {
		Db_rdr rdr = null;
		try {
			int rdr_found = 0;
			while (true) {
				if (cxl.Canceled()) return;
				if (rdr == null) {
					rdr = Rdr__init(attach_mgr);	// EXPENSIVE
					rdr_found = 0;
					if (cxl.Canceled()) return;
				}
				if (!Row__read(rdr)) {				// EXPENSIVE
					if (cxl.Canceled()) return;
					rdr = Rdr__term(rdr, attach_mgr);
					boolean last_rdr = rng.Rdr_done(rdr_found) || Select__finished();
					rng_log.Log(rng.Score_bgn(), rng.Score_end(), rng.Rdr_found(), rng.Total_found(), rng.Elapsed());
					Rdr__done(last_rdr);
					if (last_rdr)
						break;
					else
						continue;	// resume from top; will create new rdrd
				}
				if (Row__eval()) ++rdr_found;
			}
		}
		finally {
			rdr = Rdr__term(rdr, attach_mgr);
		}
	}
	protected abstract Db_rdr		Rdr__init(Db_attach_mgr attach_mgr);
	@gplx.Virtual protected void			Rdr__done(boolean last_rdr) {}
	@gplx.Virtual protected Db_rdr		Rdr__term(Db_rdr rdr, Db_attach_mgr attach_mgr) {
		if (rdr != null) rdr.Rls();
		attach_mgr.Detach();
		return null;
	}
	@gplx.Virtual protected boolean			Row__read(Db_rdr rdr) {return true;}
	@gplx.Virtual protected boolean			Row__eval() {return true;}	// NOTE: return true by default; DEPENDENCY: Srch_word_count_wkr
	@gplx.Virtual protected boolean			Select__finished() {return false;}
}
