package gplx.xowa.addons.bldrs.centrals.tasks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.core.gfobjs.*;
public abstract class Xobc_task_regy__base {
	private final    Ordered_hash hash = Ordered_hash_.New();
	protected final    Xobc_task_mgr task_mgr;
	public Xobc_task_regy__base(Xobc_task_mgr task_mgr, String name) {this.task_mgr = task_mgr; this.name = name;}
	public String					Name()					{return name;} private final    String name;
	public int						Len()					{return hash.Len();}
	public void						Add(Xobc_task_itm t)	{hash.Add(t.Task_id(), t);}
	public void						Clear()					{hash.Clear();}
	public Xobc_task_itm			Get_at(int i)			{return (Xobc_task_itm)hash.Get_at(i);}
	public Xobc_task_itm			Get_by(int i)			{return (Xobc_task_itm)hash.Get_by(i);}
	public void						Del_by(int i)			{hash.Del(i);}

	public void Save_to(Gfobj_ary ary) {
		int len = hash.Len();
		Gfobj_nde[] sub_ndes = new Gfobj_nde[len];
		ary.Ary_(sub_ndes);
		for (int i = 0; i < len; ++i) {
			Gfobj_nde sub_nde = sub_ndes[i] = Gfobj_nde.New();
			Xobc_task_itm sub_task = (Xobc_task_itm)hash.Get_at(i);
			sub_task.Save_to(sub_nde);
		}
	}
}
