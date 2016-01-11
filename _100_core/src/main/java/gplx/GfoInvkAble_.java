package gplx;
import gplx.core.primitives.*;
public class GfoInvkAble_ {
	public static GfoInvkAble as_(Object obj) {return obj instanceof GfoInvkAble ? (GfoInvkAble)obj : null;}
	public static GfoInvkAble cast(Object obj) {try {return (GfoInvkAble)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, GfoInvkAble.class, obj);}}
	public static final String_obj_val Rv_unhandled = String_obj_val.new_("Unhandled"), Rv_handled = String_obj_val.new_("Handled"), Rv_host = String_obj_val.new_("Host")
		, Rv_cancel = String_obj_val.new_("Cancel"), Rv_error = String_obj_val.new_("Error");

	public static Object InvkCmd(GfoInvkAble invk, String k)				{return InvkCmd_msg(invk, k, GfoMsg_.Null);}
	public static Object InvkCmd_val(GfoInvkAble invk, String k, Object v)	{return InvkCmd_msg(invk, k, GfoMsg_.new_cast_(k).Add("v", v));}
	public static Object InvkCmd_msg(GfoInvkAble invk, String k, GfoMsg m)	{
		Object rv = invk.Invk(GfsCtx.Instance, 0, k, m);
		if (rv == GfoInvkAble_.Rv_unhandled) throw Err_.new_wo_type("invkable did not handle message", "key", k);
		return rv;
	}
	public static final GfoInvkAble Null = new GfoInvkAble_null();
}
class GfoInvkAble_null implements GfoInvkAble {
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {return this;}
}
