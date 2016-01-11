package gplx.gfui; import gplx.*;
public class IptCfg_ {
	public static final IptCfg Null = IptCfg_null.Instance;
	public static IptCfg new_(String key) {return IptCfgRegy.Instance.GetOrNew(key);}
}
class IptCfg_null implements IptCfg {
	public String CfgKey() {return "<<NULL KEY>>";}
	public IptCfgItm GetOrDefaultArgs(String bndKey, GfoMsg m, IptArg[] argAry) {return IptCfgItm.new_().Key_(bndKey).Ipt_(List_adp_.many_((Object[])argAry)).Msg_(m);}
	public void Owners_add(String key, IptBndsOwner owner) {}
	public void Owners_del(String key) {}
	public Object NewByKey(Object o) {return this;}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return GfoInvkAble_.Rv_unhandled;}
	public static final IptCfg_null Instance = new IptCfg_null(); IptCfg_null() {}
}
