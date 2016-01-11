package gplx.langs.regxs; import gplx.*; import gplx.langs.*;
public class Regx_match {
	public Regx_match(boolean rslt, int find_bgn, int find_end, Regx_group[] groups) {this.rslt = rslt; this.find_bgn = find_bgn; this.find_end = find_end; this.groups = groups;}
	public boolean Rslt() {return rslt;} private boolean rslt;
	public boolean Rslt_none() {return !rslt;}	// NOTE: was "|| find_end - find_bgn == 0"; DATE:2013-04-11; DATE:2014-09-02
	public int Find_bgn() {return find_bgn;} int find_bgn;
	public int Find_end() {return find_end;} int find_end;
	public int Find_len() {return find_end - find_bgn;}
	public Regx_group[] Groups() {return groups;} Regx_group[] groups = Regx_group.Ary_empty;
	public static final Regx_match[] Ary_empty = new Regx_match[0];
}
