package gplx.gfui; import gplx.*;
public class IptEvtDataKey {//_20101217
	public IptKey Key() {return key;} IptKey key;
	public boolean Handled() {return handled;} public void Handled_set(boolean v) {handled = v;} private boolean handled;

	public static IptEvtDataKey as_(Object obj) {return obj instanceof IptEvtDataKey ? (IptEvtDataKey)obj : null;}
	public static IptEvtDataKey cast(Object obj) {try {return (IptEvtDataKey)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, IptEvtDataKey.class, obj);}}
	@gplx.Internal protected static final IptEvtDataKey Null = new_(IptKey_.None);
	@gplx.Internal protected static IptEvtDataKey test_(IptKey keyArg) {return new_(keyArg);}
	@gplx.Internal protected static IptEvtDataKey int_(int val) {
		IptKey keyArg = IptKey_.api_(val);
		return new_(keyArg);
	}
	public static IptEvtDataKey new_(IptKey key) {
		IptEvtDataKey rv = new IptEvtDataKey();
		rv.key = key;
		return rv;
	}
}
