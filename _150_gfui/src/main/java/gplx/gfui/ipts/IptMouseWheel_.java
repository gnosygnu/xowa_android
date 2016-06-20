package gplx.gfui.ipts; import gplx.*; import gplx.gfui.*;
public class IptMouseWheel_ {//_20101217
	public static final    IptMouseWheel
		  None	= new IptMouseWheel("wheel.none")
		, Up	= new IptMouseWheel("wheel.up")
		, Down	= new IptMouseWheel("wheel.down");
	public static IptMouseWheel parse(String raw) {
		if		(String_.Eq(raw, None.Key()))	return None;
		else if	(String_.Eq(raw, Up.Key()))		return Up;
		else if	(String_.Eq(raw, Down.Key()))	return Down;
		else throw Err_.new_parse_type(IptMouseWheel.class, raw);
	}
	public static IptMouseWheel api_(Object obj) {
		int delta = Int_.cast(obj);
		if		(delta > 0)		return Up;
		else if (delta < 0)		return Down;
		else					return None;
	}
}
