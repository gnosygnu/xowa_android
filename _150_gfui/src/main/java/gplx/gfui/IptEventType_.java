package gplx.gfui; import gplx.*;
import gplx.core.bits.*; import gplx.core.primitives.*;
public class IptEventType_ {//_20101217
	static EnmMgr enmMgr = EnmMgr.new_().BitRngEnd_(128);
	public static final IptEventType 
		  None			= new_(   0, "none")
		, KeyDown		= new_(   1, "keyDown")
		, KeyUp			= new_(   2, "keyUp")
		, KeyPress		= new_(   4, "keyPress")
		, MouseDown		= new_(   8, "mouseDown")
		, MouseUp		= new_(  16, "mouseUp")
		, MouseMove		= new_(  32, "mouseMove")
		, MouseWheel	= new_(  64, "mouseWheel")
		, MousePress	= new_( 128, "mousePress");
	public static IptEventType add_(IptEventType... ary) {
		if (ary.length == 0) return IptEventType_.None;
		int newVal = ary[0].Val();
		for (int i = 1; i < ary.length; i++)
			newVal = Bitmask_.Flip_int(true, newVal, ary[i].Val());
		return getOrNew_(newVal);
	}
	static IptEventType getOrNew_(int v) {
		IptEventType rv = (IptEventType)enmMgr.Get(v);
		return (rv == null) ? new_(v, enmMgr.GetStr(v)) : rv;
	}
	static IptEventType new_(int val, String name) {
		IptEventType rv = new IptEventType(val, name);
		enmMgr.RegObj(val, name, rv);
		return rv;
	}
	@gplx.Internal protected static boolean Has(IptEventType val, IptEventType find) {
		if (find == IptEventType_.None && val != IptEventType_.None) return false; // check .None manually b/c 0 is identity when BitShifting
		return Bitmask_.Has_int(val.Val(), find.Val());
	}
	public static IptEventType default_(IptArg[] args) {
		IptEventType rv = IptEventType_.None;
		for (IptArg arg : args)
			rv = rv.Add(IptArg_.EventType_default(arg));
		return rv;
	}
}
