package gplx.core.stores; import gplx.*; import gplx.core.*;
public class DbMaprMgr {
	public DbMaprArg[] RootIndexFlds() {return rootIndexFlds;} public DbMaprMgr RootIndexFlds_(DbMaprArg... val) {rootIndexFlds = val; return this;} DbMaprArg[] rootIndexFlds;
	public DbMaprItm Root() {return root;} public DbMaprMgr Root_(DbMaprItm v) {root = v; return this;} DbMaprItm root;
	public List_adp OwnerStack() {return ownerStack;} List_adp ownerStack = List_adp_.New();
	public Ordered_hash ContextVars() {return contextVars;} Ordered_hash contextVars = Ordered_hash_.New();
	public List_adp MaprStack() {return maprStack;} List_adp maprStack = List_adp_.New();
	public void EnvStack_add(DbMaprItm mapr, SrlObj gobj) {
		for (Object argObj : mapr.ContextFlds()) {
			DbMaprArg arg = (DbMaprArg)argObj;
			Object contextVal = Gfo_invk_.Invk_by_key((Gfo_invk)gobj, arg.ObjProp());
			this.ContextVars().Add_if_dupe_use_nth(arg.DbFld(), contextVal);
		}
		this.OwnerStack().Add(gobj);
		this.MaprStack().Add(mapr);
	}
	public void EnvStack_del(DbMaprItm mapr, SrlObj gobj) {
		for (Object argObj : mapr.ContextFlds()) {
			DbMaprArg arg = (DbMaprArg)argObj;
			this.ContextVars().Del(arg.DbFld());
		}
		List_adp_.Del_at_last(this.OwnerStack());
		List_adp_.Del_at_last(this.MaprStack());
	}
	public void Clear() {
		ownerStack.Clear();
		contextVars.Clear();
		maprStack.Clear();
	}
	public static DbMaprMgr new_() {return new DbMaprMgr();} DbMaprMgr() {}
}
