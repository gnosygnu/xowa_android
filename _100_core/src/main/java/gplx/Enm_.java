package gplx;
public class Enm_ {
	public static int		To_int(Object enm) {return Ordinal_lang(enm);}
	private static int		Ordinal_lang(Object v) {return ((Enum)v).ordinal();}	//#<>(int)v~((Enum)v).ordinal()
}
