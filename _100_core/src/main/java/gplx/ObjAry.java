package gplx;
public class ObjAry {
	public Object[] Ary() {return ary;} Object[] ary;
	public Object Get(int i) {return ary[i];}
	public Object Get0() {return ary[0];}
	public Object Get1() {return ary[1];}
        public static ObjAry pair_(Object val0, Object val1) {
		ObjAry rv = new ObjAry();
		rv.ary = new Object[2];
		rv.ary[0] = val0;
		rv.ary[1] = val1;
		return rv;
	}	ObjAry() {}
        public static ObjAry many_(Object... ary) {
		ObjAry rv = new ObjAry();
		rv.ary = ary;
		return rv;
	}
}
