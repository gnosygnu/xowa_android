package gplx.xowa.xtns.scribunto.errs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.scribunto.*;
public interface Gfo_val extends CompareAble {
	boolean Eq(Gfo_val rhs);
	boolean Match_1(Gfo_comp_op_1 op, Gfo_val comp);
}
class Gfo_val_mok implements Gfo_val {
	public String s = "";
	public boolean Eq(Gfo_val rhs) {return true;}
	public int compareTo(Object obj) {return 0;}
	public boolean Match_1(Gfo_comp_op_1 op, Gfo_val comp_obj) {
		Gfo_val_mok comp = (Gfo_val_mok)comp_obj;
		return op.Comp_str(s, comp.s);
	}
}
