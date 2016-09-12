package gplx.core.lists.hashs; import gplx.*; import gplx.core.*; import gplx.core.lists.*;
import gplx.core.primitives.*;
public class Hash_adp__int {
	private final    Hash_adp hash = Hash_adp_.New();
	private final    Int_obj_ref tmp_key = Int_obj_ref.New_neg1();
	public void Clear()								{hash.Clear();}
	public int Len()								{return hash.Count();}
	public Object Get_by(int key)					{return hash.Get_by_or_fail(tmp_key.Val_(key));}
	public Object Get_by_or_null(int key)			{return hash.Get_by(tmp_key.Val_(key));}
	public void Add(int key, Object obj)			{hash.Add(Int_obj_ref.New(key), obj);}
	public void Add(Int_obj_ref key, Object obj)	{hash.Add(key, obj);}
	public void Add_if_dupe_use_1st(int key, Object obj)			{hash.Add_if_dupe_use_1st(Int_obj_ref.New(key), obj);}
	public void Add_if_dupe_use_nth(Int_obj_ref key, Object obj)	{hash.Add_if_dupe_use_nth(key, obj);}
}
