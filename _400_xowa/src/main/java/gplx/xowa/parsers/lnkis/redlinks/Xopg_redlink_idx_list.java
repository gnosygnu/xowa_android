package gplx.xowa.parsers.lnkis.redlinks; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*; import gplx.xowa.parsers.lnkis.*;
import gplx.core.primitives.*;
public class Xopg_redlink_idx_list {
	private final Int_list list = new Int_list();
	public int Len() {return list.Len();}
	public int Max() {return max;} private int max;
	public int Get_at(int i) {return list.Get_at(i);}
	public void Clear() {
		list.Clear();
		max = 0;
	}
	public void Add(int i) {
		list.Add(i);
		if (i > max) max = i;
	}
}
