package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
import gplx.core.strings.*;
public interface IptArg {//_20110213
	String Key();
	boolean Eq(IptArg comp);	// NOTE: this relies on unique key across all IptArgs; EX: .Key() cannot be just "left"; would have issues with key.left and mouse.left
}
class IptKeyChain implements IptArg {//_20110213
	public String Key()						{return key;} private String key;
	public boolean Eq(IptArg comp)				{return String_.Eq(key, comp.Key());}
	public IptArg[] Chained()				{return chained;} IptArg[] chained;
	@gplx.Internal protected IptKeyChain(IptArg[] ary) {
		chained = ary;
		String_bldr sb = String_bldr_.new_();
		for (int i = 0; i < ary.length; i++) {
			IptArg itm = ary[i];
			sb.Add_spr_unless_first(itm.Key(), ",", i);
		}
		key = sb.To_str();
	}
	public static IptKeyChain parse(String raw) {
		String[] itms = String_.Split(raw, ",");
		IptArg[] rv = new IptArg[itms.length];
		for (int i = 0; i < rv.length; i++)
			rv[i] = IptArg_.parse(String_.Trim(itms[i]));
		return new IptKeyChain(rv);
	}
}
