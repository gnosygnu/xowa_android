package gplx.gfui; import gplx.*;
public class IptMouseWheel implements IptArg {//_20110213
	public String Key()					{return key;} private String key;
	public boolean Eq(IptArg comp)			{return String_.Eq(key, comp.Key());}
	@gplx.Internal protected IptMouseWheel(String key)	{this.key = key;}
}
