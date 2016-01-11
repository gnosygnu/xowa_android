package gplx.gfui; import gplx.*;
public class IptEvtDataKeyHeld {//_20101217
	public char KeyChar() {return c;} char c;
	public boolean Handled() {return handled;} public void Handled_set(boolean v) {handled = v;} private boolean handled;

	public static IptEvtDataKeyHeld as_(Object obj) {return obj instanceof IptEvtDataKeyHeld ? (IptEvtDataKeyHeld)obj : null;}
	public static IptEvtDataKeyHeld cast(Object obj) {try {return (IptEvtDataKeyHeld)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, IptEvtDataKeyHeld.class, obj);}}
	@gplx.Internal protected static final IptEvtDataKeyHeld Null = char_((char)0);
	@gplx.Internal protected static IptEvtDataKeyHeld char_(char c) {
		IptEvtDataKeyHeld rv = new IptEvtDataKeyHeld();
		rv.c = c;
		return rv;
	}
}
