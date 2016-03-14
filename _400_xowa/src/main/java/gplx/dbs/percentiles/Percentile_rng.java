package gplx.dbs.percentiles; import gplx.*; import gplx.dbs.*;
public class Percentile_rng {
	private long total_max; private int total_needed, total_found;
	private int score_max, score_len, score_len_max;
	private long prv_time;
	public int Score_bgn()		{return score_bgn;} private int score_bgn;
	public int Score_end()		{return score_end;} private int score_end;
	public int Rdr_found()		{return rdr_found;} private int rdr_found;
	public int Total_found()	{return total_found;}
	public int Elapsed()		{return elapsed;}	private int elapsed;
	public Percentile_rng Init(long total_max, int score_max) {
		this.total_max = total_max;
		this.score_max = score_max;
		this.score_len_max = score_max / 10;	// limit to 10%
		return this;
	}
	public void Select_init(int total_needed, int score_len_adj) {
		this.total_needed = total_needed;
		this.total_found = 0;
		this.prv_time = gplx.core.envs.Env_.TickCount();
		int score_unit = Calc_score_unit(total_needed, total_max, score_max);
		score_len = score_unit + (score_unit * score_len_adj);
		score_bgn = score_max;
		Rng_len_(Bool_.Y);
	}
	public boolean Rdr_done(int rdr_found) {
		if (score_bgn == 0) return true;
		this.rdr_found    = rdr_found;
		this.total_found += rdr_found;

		// calc rng_multiplier based on rdr_found and total_needed; EX: 100=total_needed; 10=rdr_found; 40=total_found -> 6=rng_multiplier; (100 - 40 / 10)
		int rng_multiplier = 1;
		if (rdr_found == 0) {
			rng_multiplier = 4;
		} else {
			int total_remaining = total_needed - total_found;
			rng_multiplier = total_remaining == 0 ? 1 : Math_.Ceil_as_int(total_remaining / rdr_found);
		}

		// calc new score_len
		int new_score_len = score_len * rng_multiplier;
		if		(new_score_len < 1)				new_score_len = score_len;
		else if (new_score_len > score_len_max) new_score_len = score_len_max;
		score_len = new_score_len;
		Rng_len_(Bool_.N);

		// update times
		long new_time = gplx.core.envs.Env_.TickCount();
		this.elapsed = Int_.Subtract_long(new_time, prv_time);
		prv_time = new_time;
		return false;
	}
	private void Rng_len_(boolean first) {
		score_end = score_bgn + (first ? 1 : 0);	// + 1 to include rows with scores at max; EX: > 999,998 AND < 1,000,001
		score_bgn = score_end - score_len;
		if (score_bgn < 0) score_bgn = 0;			// make sure score is not negative
	}
	@gplx.Internal protected static int Calc_score_unit(int total_needed, long total_max, int score_max) {// TEST:
		return (int)Math_.Ceil(Math_.Div_safe_as_double(total_needed, Math_.Div_safe_as_double(total_max, score_max)));	// EX: 100 needed / (16 M / 1 M) -> 7 units to fill 100
	}
}
