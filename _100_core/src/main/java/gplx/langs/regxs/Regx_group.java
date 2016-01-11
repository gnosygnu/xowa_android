package gplx.langs.regxs; import gplx.*; import gplx.langs.*;
public class Regx_group {
	public Regx_group(boolean rslt, int bgn, int end, String val) {this.rslt = rslt; this.bgn = bgn; this.end = end; this.val = val;}
	public boolean Rslt() {return rslt;} private boolean rslt;
	public int Bgn() {return bgn;} int bgn;
	public int End() {return end;} int end;
	public String Val() {return val;} private String val;
	public static final Regx_group[] Ary_empty = new Regx_group[0];
}
