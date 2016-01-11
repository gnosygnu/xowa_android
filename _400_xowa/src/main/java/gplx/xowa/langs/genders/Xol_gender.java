package gplx.xowa.langs.genders; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
public interface Xol_gender {
	byte[] Gender_eval(int gender, byte[] when_m, byte[] when_f, byte[] when_u);
}
