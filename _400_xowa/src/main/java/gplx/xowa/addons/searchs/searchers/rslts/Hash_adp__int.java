package gplx.xowa.addons.searchs.searchers.rslts; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.searchs.*; import gplx.xowa.addons.searchs.searchers.*;
import gplx.core.primitives.*;
class Hash_adp__int {
	private final Hash_adp hash = Hash_adp_.new_();
	private final Int_obj_ref tmp_key = Int_obj_ref.neg1_();
	public void Clear()						{hash.Clear();}
	public Object Get_by(int key)			{return hash.Get_by(tmp_key.Val_(key));}
	public void Add(int key, Object obj)	{hash.Add(Int_obj_ref.new_(key), obj);}
	public void Add_if_dupe_use_1st(int key, Object obj)	{hash.Add_if_dupe_use_1st(Int_obj_ref.new_(key), obj);}
}
