package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
public class IptMouseWheel implements IptArg {//_20110213
	public String Key()					{return key;} private String key;
	public boolean Eq(IptArg comp)			{return String_.Eq(key, comp.Key());}
	public IptMouseWheel(String key)	{this.key = key;}
}
