package gplx.langs.jsons; import gplx.*; import gplx.langs.*;
public interface Json_grp extends Json_itm {
	void Src_end_(int v);
	int Len();
	Json_itm Get_at(int i);
	Json_nde Get_as_nde(int i);
	void Add(Json_itm itm);
}
class Json_grp_ {
	public static final    Json_grp[] Ary_empty = new Json_grp[0];  
	public static void Print_nl(Bry_bfr bfr) {								// \n\n can be caused by nested groups (EX: "[[]]"); only print 1
		if (bfr.Bfr()[bfr.Len() - 1] != Byte_ascii.Nl)
			bfr.Add_byte_nl();
	}
	public static void Print_indent(Bry_bfr bfr, int depth) {
		if (depth > 0) bfr.Add_byte_repeat(Byte_ascii.Space, depth * 2);	// indent
	}
}
