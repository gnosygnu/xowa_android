package gplx.gfui; import gplx.*;
public class IptEvtDataMouse {//_20101217
	public IptMouseBtn		Button() {return button;} IptMouseBtn button;
	public IptMouseWheel	Wheel() {return wheel;} IptMouseWheel wheel;
	public PointAdp			Pos() {return location;} PointAdp location;

	public static IptEvtDataMouse as_(Object obj) {return obj instanceof IptEvtDataMouse ? (IptEvtDataMouse)obj : null;}
	public static IptEvtDataMouse cast(Object obj) {try {return (IptEvtDataMouse)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, IptEvtDataMouse.class, obj);}}
	@gplx.Internal protected static final IptEvtDataMouse Null = IptEvtDataMouse.new_(IptMouseBtn_.None, IptMouseWheel_.None, 0, 0);
	public static IptEvtDataMouse new_(IptMouseBtn button, IptMouseWheel wheel, int x, int y) {
		IptEvtDataMouse rv = new IptEvtDataMouse();
		rv.button = button;
		rv.wheel = wheel;
		rv.location = PointAdp_.new_(x, y);
		return rv;
	}
}
