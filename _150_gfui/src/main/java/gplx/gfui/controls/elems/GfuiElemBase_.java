package gplx.gfui.controls.elems; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
public class GfuiElemBase_ {
	public static GfuiElemBase as_(Object obj) {return obj instanceof GfuiElemBase ? (GfuiElemBase)obj : null;}
	public static GfuiElemBase cast(Object obj) {try {return (GfuiElemBase)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfuiElemBase.class, obj);}}
}
