package gplx.core.gfobjs; import gplx.*; import gplx.core.*;
public class Gfobj_ary implements Gfobj_grp { // NOTE: items in array can vary in types; EX:['a', 1, false]
	public Gfobj_ary(Object[] ary) {this.ary = ary;}
	public byte				Grp_tid() {return Gfobj_grp_.Grp_tid__ary;}
	public int				Len() {return ary.length;}
	public Object			Get_at(int i) {return ary[i];}
	public Object[]			Ary_obj() {return ary;} private Object[] ary;
	public Gfobj_ary		Ary_(Object[] v) {this.ary = v; return this;}
	public Gfobj_nde		New_nde_at(int i) {
		Gfobj_nde rv = Gfobj_nde.New();
		ary[i] = rv;
		return rv;
	}
}
