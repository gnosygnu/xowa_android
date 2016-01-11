package gplx.xowa.parsers.apos; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_apos_tkn extends Xop_tkn_itm_base {
	public Xop_apos_tkn(int bgn, int end, int apos_len, int apos_tid, int apos_cmd, int apos_lit) {
		this.apos_len = apos_len; this.apos_tid = apos_tid; this.apos_cmd = apos_cmd; this.apos_lit = apos_lit;
		this.Tkn_ini_pos(false, bgn, end);
	}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_apos;}
	public int Apos_len() {return apos_len;} private int apos_len;
	public int Apos_lit() {return apos_lit;} public Xop_apos_tkn Apos_lit_(int v) {apos_lit = v; return this;} private int apos_lit;
	public int Apos_tid() {return apos_tid;} public Xop_apos_tkn Apos_tid_(int v) {apos_tid = v; return this;} private int apos_tid;
	public int Apos_cmd() {return apos_cmd;} public Xop_apos_tkn Apos_cmd_(int v) {apos_cmd = v; return this;} private int apos_cmd;
}
