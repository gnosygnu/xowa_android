package gplx.xowa.addons.wikis.pages.randoms.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*; import gplx.xowa.addons.wikis.pages.randoms.*;
public class Rndm_rng_itm {
	public Rndm_rng_itm(int mgr_idx, int rng_idx, int seq_bgn, int seq_end) {
		this.mgr_idx = mgr_idx; this.rng_idx = rng_idx;
		this.seq_bgn = seq_bgn; this.seq_end = seq_end;
	}
	public int Mgr_idx() {return mgr_idx;} private final    int mgr_idx;
	public int Rng_idx() {return rng_idx;} private final    int rng_idx;
	public int Seq_bgn() {return seq_bgn;} private final    int seq_bgn;
	public int Seq_end() {return seq_end;} private final    int seq_end;

	public static Rndm_rng_itm Noop() {return new Rndm_rng_itm(-1, -1, 0, 0);}
}
