package gplx.gfui; import gplx.*;
import gplx.core.bits.*;
public class IptKey implements IptArg {//_20101217
	@gplx.Internal protected IptKey(int val, String key) {this.val = val; this.key = key;}
	public String Key()					{return key;} private final String key;
	public int Val()					{return val;} private final int val;
	public boolean Eq(IptArg comp)			{return String_.Eq(key, comp.Key());}
	public String XtoUiStr()			{return IptKeyStrMgr.Instance.To_str(this);}
	public IptKey Add(IptKey comp)		{return IptKey_.add_(this, comp);}
	public boolean Mod_shift()				{return Bitmask_.Has_int(val, IptKey_.Shift.Val());}
	public boolean Mod_ctrl()				{return Bitmask_.Has_int(val, IptKey_.Ctrl.Val());}
	public boolean Mod_alt()				{return Bitmask_.Has_int(val, IptKey_.Alt.Val());}
}
