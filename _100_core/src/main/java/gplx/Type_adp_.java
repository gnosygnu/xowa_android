package gplx;
public class Type_adp_ {
	public static boolean Eq(Class<?> lhs, Class<?> rhs) {
		if		(lhs == null && rhs == null)	return true;
		else if (lhs == null || rhs == null)	return false;
		else									return lhs.equals(rhs);		//#<>.Equals~.equals
	}
	public static boolean Eq_typeSafe(Object o, Class<?> expd) {if (o == null) return false;
		Class<?> actl = o.getClass();
		return Object_.Eq(expd, actl);
	}
	public static boolean IsAssignableFrom(Class<?> lhs, Class<?> rhs) {return lhs.isAssignableFrom(rhs);}	//#<>.IsAssignableFrom~.isAssignableFrom
	public static boolean Implements_intf_obj(Object cur, Class<?> type) {return cur == null ? false : IsAssignableFrom(type, cur.getClass());}
	public static boolean Is_array(Class<?> t) {return t.isArray();}		//#<>.IsArray~.isArray()
	public static Class<?> ClassOf_obj(Object o) {return o.getClass();}
	public static Class<?> ClassOf_primitive(Object o) {
		//#{ClassOf_primitive
		Class<?> rv = o.getClass();
		if 		(rv == Integer.class) rv = int.class;
		else if	(rv == Long.class) rv = long.class;
		else if	(rv == Byte.class) rv = byte.class;
		else if	(rv == Short.class) rv = short.class;
		return rv;
		//#}
	}
	public static String FullNameOf_obj(Object o) {return FullNameOf_type(o.getClass());}	//#<>.FullName~.getCanonicalName()
	public static String FullNameOf_type(Class<?> type) {return type.getCanonicalName();}	//#<>.FullName~.getCanonicalName()
	public static String NameOf_type(Class<?> type) {return type.getName();}	//#<>.Name~.getName()
	public static String NameOf_obj(Object obj) {return obj == null ? String_.Null_mark : obj.getClass().getName();}	//#<>.getClass().Name~.getClass().getName()
	public static int To_tid_obj(Object o) {
		if (o == null) return Tid__null;
		Class<?> type = o.getClass();
		return To_tid_type(type);
	}
	public static int To_tid_type(Class<?> type) {
		if		(Type_adp_.Eq(type, Int_.Cls_ref_type))				return Tid__int;
		else if (Type_adp_.Eq(type, String_.Cls_ref_type))			return Tid__str;
		else if (Type_adp_.Eq(type, byte[].class))				return Tid__bry;
		else if (Type_adp_.Eq(type, Bool_.Cls_ref_type))			return Tid__bool;
		else if (Type_adp_.Eq(type, Byte_.Cls_ref_type))			return Tid__byte;
		else if (Type_adp_.Eq(type, Long_.Cls_ref_type))			return Tid__long;
		else if (Type_adp_.Eq(type, Double_.Cls_ref_type))			return Tid__double;
		else if (Type_adp_.Eq(type, Decimal_adp_.Cls_ref_type))		return Tid__decimal;
		else if (Type_adp_.Eq(type, DateAdp_.Cls_ref_type))			return Tid__date;
		else if (Type_adp_.Eq(type, Float_.Cls_ref_type))			return Tid__float;
		else if (Type_adp_.Eq(type, Short_.Cls_ref_type))			return Tid__short;
		else if (Type_adp_.Eq(type, Char_.Cls_ref_type))			return Tid__char;
		else														return Tid__obj;
	}
	public static final int
		Tid__obj		=  0
	,	Tid__null		=  1
	,	Tid__bool		=  2
	,	Tid__byte		=  3
	,	Tid__short		=  4
	,	Tid__int		=  5
	,	Tid__long		=  6
	,	Tid__float		=  7
	,	Tid__double		=  8
	,	Tid__char		=  9
	,	Tid__str		= 10
	,	Tid__bry		= 11
	,	Tid__date		= 12
	,	Tid__decimal	= 13
	;
}
