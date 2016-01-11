package gplx.gfui; import gplx.*;
public class IptMouseBtn implements IptArg {//_20110213
	@gplx.Internal protected IptMouseBtn(int val, String key) {this.val = val; this.key = key;}
	public String Key()					{return key;} private String key;
	@gplx.Internal protected int Val()					{return val;} int val;
	public boolean Eq(IptArg comp)			{return String_.Eq(key, comp.Key());}
}
