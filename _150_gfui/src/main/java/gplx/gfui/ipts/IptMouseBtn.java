package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
public class IptMouseBtn implements IptArg {//_20110213
	public IptMouseBtn(int val, String key) {this.val = val; this.key = key;}
	public String Key()					{return key;} private String key;
	public int Val()					{return val;} int val;
	public boolean Eq(IptArg comp)			{return String_.Eq(key, comp.Key());}
}
