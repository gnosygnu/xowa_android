package gplx.xowa.langs.genders; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
import gplx.core.primitives.*; import gplx.core.btries.*;
public class Xol_gender_ {
	public static Xol_gender new_by_lang_id(int lang_id) {return Xol_gender__basic.Instance;}
	public static final int Tid_male = 0, Tid_female = 1, Tid_unknown = 2;
}
class Xol_gender__basic implements Xol_gender {
	public byte[] Gender_eval(int gender, byte[] when_m, byte[] when_f, byte[] when_u) {
		switch (gender) {
			case Xol_gender_.Tid_male:		return when_m;
			case Xol_gender_.Tid_female:	return when_f;
			case Xol_gender_.Tid_unknown:	return when_u;
			default:						throw Err_.new_unimplemented();
		}
	}
	public static final Xol_gender__basic Instance = new Xol_gender__basic(); Xol_gender__basic() {}
}
