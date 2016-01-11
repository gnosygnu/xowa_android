package gplx.core.primitives; import gplx.*; import gplx.core.*;
public class Bool_obj_val {
	Bool_obj_val(int v) {val = v;} private final int val;
	public boolean Val() {return val == 1;}
	public static final Bool_obj_val
	  Null	= new Bool_obj_val(-1)
	, False = new Bool_obj_val(0)
	, True	= new Bool_obj_val(1)
	;
	public static Bool_obj_val read_(Object o) {String s = String_.as_(o); return s == null ? (Bool_obj_val)o : parse(s);}
	public static Bool_obj_val parse(String raw) {
		if		(String_.Eq(raw, "y"))	return Bool_obj_val.True;
		else if	(String_.Eq(raw, "n"))	return Bool_obj_val.False;
		else if	(String_.Eq(raw, ""))	return Bool_obj_val.Null;
		else	throw Err_.new_parse_type(Bool_obj_val.class, raw);
	}
}
