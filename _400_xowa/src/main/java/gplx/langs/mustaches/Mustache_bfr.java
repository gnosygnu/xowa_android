package gplx.langs.mustaches; import gplx.*; import gplx.langs.*;
public class Mustache_bfr {
	private final    Bry_bfr bfr;
	public Mustache_bfr(Bry_bfr bfr) {this.bfr = bfr;}
	public Bry_bfr Bfr() {return bfr;}
	public Mustache_bfr Escape_(boolean v) {escape = v; return this;} private boolean escape;
	public void Add_int			(int v)		{bfr.Add_int_variable(v);}
	public void Add_double		(double v)	{bfr.Add_double(v);}
	public void Add_str_u8		(String v)	{bfr.Add_str_u8(v);}
	public void Add_mid			(byte[] src, int bgn, int end) {bfr.Add_mid(src, bgn, end);}
	public void Add_bry			(byte[] v) {
		if (v == null) return;	// allow items to have null props
		if (escape)
			gplx.langs.htmls.Gfh_utl.Escape_html_to_bfr(bfr, v, 0, v.length, Bool_.Y, Bool_.Y, Bool_.Y, Bool_.Y, Bool_.Y);
		else
			bfr.Add(v);
	}
	public byte[] To_bry_and_clear() {return bfr.To_bry_and_clear();}
	public String To_str_and_clear() {return bfr.To_str_and_clear();}
	public static Mustache_bfr New()				{return new Mustache_bfr(Bry_bfr.new_());}
	public static Mustache_bfr New_bfr(Bry_bfr v)	{return new Mustache_bfr(v);}
}
