package gplx.core.brys; import gplx.*; import gplx.core.*;
public class Bry_bldr {
	public byte[] Val() {return val;} private byte[] val;
	public Bry_bldr New_256() {return New(256);}
	public Bry_bldr New(int len) {val = new byte[len]; return this;}
	public Bry_bldr Set_rng_ws(byte v)					{return Set_many(v, Byte_ascii.Space, Byte_ascii.Tab, Byte_ascii.Nl, Byte_ascii.Cr);}
	public Bry_bldr Set_rng_xml_identifier(byte v)		{return Set_rng_alpha_lc(v).Set_rng_alpha_uc(v).Set_rng_num(v).Set_many(v, Byte_ascii.Underline, Byte_ascii.Dash);}
	public Bry_bldr Set_rng_alpha(byte v)				{return Set_rng_alpha_lc(v).Set_rng_alpha_uc(v);}
	public Bry_bldr Set_rng_alpha_lc(byte v)			{return Set_rng(v, Byte_ascii.Ltr_a, Byte_ascii.Ltr_z);}
	public Bry_bldr Set_rng_alpha_uc(byte v)			{return Set_rng(v, Byte_ascii.Ltr_A, Byte_ascii.Ltr_Z);}
	public Bry_bldr Set_rng_num(byte v)					{return Set_rng(v, Byte_ascii.Num_0, Byte_ascii.Num_9);}
	public Bry_bldr Set_rng(byte v, int bgn, int end) {
		for (int i = bgn; i <= end; i++)
			val[i] = v;
		return this;
	}
	public Bry_bldr Set_many(byte v, int... ary) {
		int len = ary.length; 
		for (int i = 0; i < len; i++)
			val[ary[i]] = v;
		return this;
	}
}
