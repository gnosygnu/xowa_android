package gplx.gfui; import gplx.*;
public class IptMouseMove implements IptArg {//_20110213
	public String Key()					{return key;} private String key = "move.any";
	public boolean Eq(IptArg comp)			{return String_.Eq(this.Key(), comp.Key());}
	public static final IptMouseMove AnyDirection = new IptMouseMove(); IptMouseMove() {}
}
