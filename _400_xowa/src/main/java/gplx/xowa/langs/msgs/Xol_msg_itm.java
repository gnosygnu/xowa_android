package gplx.xowa.langs.msgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
import gplx.core.brys.fmtrs.*;
public class Xol_msg_itm {
	public Xol_msg_itm(int id, byte[] key) {this.id = id; this.key = key;}
	public int		Id()				{return id;} private final    int id;
	public byte[]	Key()				{return key;} private final    byte[] key;
	public byte[]	Val()				{return val;} private byte[] val;
	public int		Src()				{return src;} private int src;
	public boolean	Src_is_missing()	{return src == Src_missing;}
	public boolean	Has_fmt_arg()		{return has_fmt_arg;} private boolean has_fmt_arg;
	public boolean		Has_tmpl_txt()		{return has_tmpl_txt;} private boolean has_tmpl_txt;
	public boolean		Dirty()				{return dirty;} private boolean dirty;	// BLDR:
	
	public Xol_msg_itm Src_(int v) {src = v; return this;}
	public Xol_msg_itm Dirty_(boolean v) {dirty = v; return this;}

	public void Atrs_set(byte[] val, boolean has_fmt_arg, boolean has_tmpl_txt) {
		this.val = val; this.has_fmt_arg = has_fmt_arg; this.has_tmpl_txt = has_tmpl_txt;
	}
	public byte[] Fmt(Bry_bfr bfr, Bry_fmtr fmtr, Object... args) {
		fmtr.Fmt_(val);
		fmtr.Bld_bfr_many(bfr, args);
		return bfr.To_bry_and_clear();
	}
	public byte[] Fmt_tmp(Bry_bfr bfr, Bry_fmtr fmtr, byte[] tmp_val, Object... args) {
		fmtr.Fmt_(tmp_val);
		fmtr.Bld_bfr_many(bfr, args);
		return bfr.To_bry_and_clear();
	}
	public static final    int Src_null = 0, Src_lang = 1, Src_wiki = 2, Src_missing = 3;
}
