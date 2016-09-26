package gplx.xowa.wikis.data.tbls; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.data.*;
import gplx.core.primitives.*; import gplx.xowa.addons.wikis.ctgs.*;
public class Xowd_category_itm {
	public int Id() {return id;} private int id;
	public Int_obj_val Id_val() {if (id_val == null) id_val = new Int_obj_val(id); return id_val;} Int_obj_val id_val;
	public int File_idx() {return file_idx;} private int file_idx;
	public boolean Hidden() {return hidden;} private boolean hidden;
	public int Count_all()		{return count_subcs + count_files + count_pages;}
	public int Count_subcs()	{return count_subcs;} private int count_subcs;
	public int Count_files()	{return count_files;} private int count_files;
	public int Count_pages()	{return count_pages;} private int count_pages;
	public int Count_by_tid(byte tid) {
		switch (tid) {
			case Xoa_ctg_mgr.Tid__subc: return count_subcs;
			case Xoa_ctg_mgr.Tid__page: return count_pages;
			case Xoa_ctg_mgr.Tid__file: return count_files;
			default: throw Err_.new_unhandled(tid);
		}		
	} 
	public static Xowd_category_itm load_(int id, int file_idx, boolean hidden, int count_subcs, int count_files, int count_pages) {
		Xowd_category_itm rv = new Xowd_category_itm();
		rv.id = id; rv.file_idx = file_idx; rv.hidden = hidden;
		rv.count_subcs = count_subcs;  rv.count_files = count_files; rv.count_pages = count_pages;
		return rv;
	}
	public static final    Xowd_category_itm Null = new Xowd_category_itm(); Xowd_category_itm() {}
}
