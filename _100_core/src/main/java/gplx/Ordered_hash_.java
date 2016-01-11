package gplx;
import gplx.core.primitives.*;
public class Ordered_hash_ {
	public static Ordered_hash New()			{return new Ordered_hash_base();}
	public static Ordered_hash New_bry()		{return new Ordered_hash_bry();}
}
class Ordered_hash_bry extends Ordered_hash_base {
	private final Bry_obj_ref tmp_ref = Bry_obj_ref.New_empty();
	@Override protected void Add_base(Object key, Object val)	{super.Add_base(Bry_obj_ref.New((byte[])key), val);}
	@Override protected void Del_base(Object key)				{synchronized (tmp_ref) {super.Del_base(tmp_ref.Val_((byte[])key));}}
	@Override protected boolean Has_base(Object key)				{synchronized (tmp_ref) {return super.Has_base(tmp_ref.Val_((byte[])key));}}
	@Override protected Object Fetch_base(Object key)			{synchronized (tmp_ref) {return super.Fetch_base(tmp_ref.Val_((byte[])key));}}
}
