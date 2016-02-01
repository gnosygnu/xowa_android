package gplx.xowa.files; import gplx.*; import gplx.xowa.*;
import gplx.core.primitives.*;
public class Xofv_repo_mgr {
	private final Hash_adp_bry key_regy = Hash_adp_bry.cs();
	private final Hash_adp tid_regy = Hash_adp_.new_(); private final Byte_obj_ref tid_key = Byte_obj_ref.zero_();
	public Xofv_repo_mgr Add(Xofv_repo_itm itm) {
		key_regy.Add(itm.Key(), itm);
		tid_regy.Add(Byte_obj_ref.new_(itm.Tid()), itm);
		return this;
	}
	public Xofv_repo_itm Get_by_key(byte[] key) {
		return (Xofv_repo_itm)key_regy.Get_by(key);
	}
	public Xofv_repo_itm Get_by_tid(byte tid) {
		return (Xofv_repo_itm)tid_regy.Get_by(tid_key.Val_(tid));
	}
}