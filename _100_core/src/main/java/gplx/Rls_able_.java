package gplx;
public class Rls_able_ {
	public static Rls_able as_(Object obj) {return obj instanceof Rls_able ? (Rls_able)obj : null;}
	public static Rls_able cast(Object obj) {try {return (Rls_able)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, Rls_able.class, obj);}}
	public static final Rls_able Null = new Rls_able__noop();
}
class Rls_able__noop implements Rls_able {
	public void Rls() {}
}
