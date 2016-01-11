package gplx.gfui; import gplx.*;
public class GfuiElemBase_ {
	public static GfuiElemBase as_(Object obj) {return obj instanceof GfuiElemBase ? (GfuiElemBase)obj : null;}
	public static GfuiElemBase cast(Object obj) {try {return (GfuiElemBase)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfuiElemBase.class, obj);}}
}
