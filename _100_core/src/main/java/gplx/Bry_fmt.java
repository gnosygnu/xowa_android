package gplx;
import gplx.core.brys.*; import gplx.core.brys.fmts.*;	
public class Bry_fmt {
	private byte[] src;
	private Bry_fmt_itm[] itms; private int itms_len;
	private Bfr_fmt_arg[] args = Bfr_fmt_arg.Ary_empty;
	private byte[][] keys = Bry_.Ary_empty;
	private Object[] vals = null;
	private boolean dirty;
	public Bry_fmt(byte[] src, byte[][] keys, Bfr_fmt_arg[] args) {
		dirty = true;
		this.src = src; this.keys = keys; this.args = args;
	}
	public Bry_fmt Fmt_(String v)						{dirty = true; src = Bry_.new_u8(v); return this;}
	public Bry_fmt Args_(Bfr_fmt_arg... v)		{dirty = true; args = v; return this;}
	public Bry_fmt Keys_(String... v)				{return Keys_(Bry_.Ary(v));}
	public Bry_fmt Keys_(byte[]... v)				{dirty = true; keys = v; return this;}
	public Bry_fmt Vals_(Object... v)				{vals = v; return this;}
	public String Bld_many_to_str_auto_bfr(Object... vals_ary) {
		Bry_bfr bfr = Bry_bfr_.Get();
		try {return Bld_many_to_str(bfr, vals_ary);} 
		finally {bfr.Mkr_rls();}
	}
	public String Bld_many_to_str(Bry_bfr bfr, Object... vals_ary) {
		Bld_many(bfr, vals_ary);
		return bfr.To_str_and_clear();
	}
	public void Bld_many(Bry_bfr bfr, Object... vals_ary) {
		if (dirty) Compile();
		int vals_len = vals_ary.length;
		for (int i = 0; i < itms_len; ++i) {
			Bry_fmt_itm itm = itms[i];
			switch (itm.Tid) {
				case Bry_fmt_itm.Tid__txt: bfr.Add_mid(src, itm.Src_bgn, itm.Src_end); break;
				case Bry_fmt_itm.Tid__arg: itm.Arg.Bfr_arg__add(bfr);break;
				case Bry_fmt_itm.Tid__key: 
					int idx = itm.Key_idx;
					if (idx > -1 && idx < vals_len)
						bfr.Add_obj(vals_ary[idx]);
					else
						bfr.Add_mid(src, itm.Src_bgn, itm.Src_end);
					break;
				default: throw Err_.new_unhandled(itm.Tid);
			}
		}
	}
	public String To_str() {return Bld_many_to_str_auto_bfr(vals);}
	private void Compile() {
		dirty = false;
		this.itms = Bry_fmt_parser_.Parse(Byte_ascii.Tilde, Byte_ascii.Curly_bgn, Byte_ascii.Curly_end, args, keys, src);
		this.itms_len = itms.length;
	}
	public static Bry_fmt New(String fmt, String... keys) {return new Bry_fmt(Bry_.new_u8(fmt), Bry_.Ary(keys), Bfr_fmt_arg.Ary_empty);}
	public static Bry_fmt New(byte[] fmt, String... keys) {return new Bry_fmt(fmt				, Bry_.Ary(keys), Bfr_fmt_arg.Ary_empty);}
	public static String Make_str(String fmt_str, Object... vals) {return Auto(fmt_str).Vals_(vals).To_str();}
	public static Bry_fmt Auto(String fmt_str) {
		byte[] fmt_bry = Bry_.new_u8(fmt_str);
		byte[][] keys_bry = Bry_fmt_parser_.Parse_keys(fmt_bry);
		return new Bry_fmt(fmt_bry, keys_bry, Bfr_fmt_arg.Ary_empty);
	}
}