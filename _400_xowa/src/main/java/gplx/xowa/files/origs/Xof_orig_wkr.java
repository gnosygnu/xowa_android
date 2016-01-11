package gplx.xowa.files.origs; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.xowa.files.fsdb.*;
public interface Xof_orig_wkr {
	byte			Tid();
	Xof_orig_itm	Find_as_itm(byte[] ttl, int list_idx, int list_len);
	void			Find_by_list(Ordered_hash rv, List_adp itms);
	boolean			Add_orig(byte repo, byte[] page, int ext_id, int w, int h, byte[] redirect);
	void			Db_txn_save();
	void			Db_rls();
}
