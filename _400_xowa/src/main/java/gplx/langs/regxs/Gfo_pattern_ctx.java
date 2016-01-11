package gplx.langs.regxs; import gplx.*; import gplx.langs.*;
public class Gfo_pattern_ctx {
	public boolean Rslt_pass() {return rslt;} private boolean rslt;
	public void Rslt_fail_() {rslt = false;}
	public boolean Prv_was_wild() {return prv_was_wild;} public void Prv_was_wild_(boolean v) {prv_was_wild = v;} private boolean prv_was_wild;
	private int itm_len;
	public int Itm_idx() {return itm_idx;} public void Itm_idx_(int v) {itm_idx = v;} private int itm_idx;
	public boolean Itm_idx_is_last() {return itm_idx == itm_len - 1;}
	public void Init(int itm_len) {
		this.rslt = true;
		this.itm_len = itm_len;
		this.prv_was_wild = false;
	}
}
