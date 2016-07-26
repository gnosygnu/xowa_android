package gplx.xowa.xtns.cites; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
public class Ref_itm_lst {
	private final    Hash_adp hash = Hash_adp_bry.cs(); 
	private final    List_adp list = List_adp_.New();
	private int idx_major_last = 0;
	// private byte[] grp_name; 
	public Ref_itm_lst(byte[] grp_name) {} //this.grp_name = grp_name;
	public int Itms_len() {return list.Count();}
	public Ref_nde Itms_get_at(int i) {return (Ref_nde)list.Get_at(i);}
	public void Itms_add(byte[] itm_name, byte[] follow, Ref_nde itm) {
		if (itm_name == Bry_.Empty) {
			if (follow == Bry_.Empty) {
				itm.Idx_major_(idx_major_last++);
				list.Add(itm);
			}
			else {
				Object o = hash.Get_by(follow);
				if (o == null) {// fail
				}
				else {
					Ref_nde head = (Ref_nde)o;
					head.Related_add(itm, Ref_nde.Idx_minor_follow);
					itm.Idx_major_(head.Idx_major());
				}

			}
		}
		else {
			Object o = hash.Get_by(itm_name);
			if (o == null) {
				hash.Add(itm_name, itm);
				itm.Idx_major_(idx_major_last++);
				list.Add(itm);
			}
			else {
				Ref_nde head = (Ref_nde)o;
				head.Related_add(itm, head.Related_len());
				itm.Idx_major_(head.Idx_major());
				itm.Idx_minor_(head.Related_len());
			}
		}
	}
	public void Itms_clear() {
		hash.Clear();
		list.Clear();
		idx_major_last = 0;
	}
}
