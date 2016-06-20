package gplx.langs.regxs; import gplx.*; import gplx.langs.*;
public class Regx_adp_ {
	public static Regx_adp new_(String pattern) {return new Regx_adp(pattern);}
	public static List_adp Find_all(String input, String find) {
		Regx_adp regx = Regx_adp_.new_(find);
		int idx = 0;
		List_adp rv = List_adp_.New();
		while (true)  {
			Regx_match match = regx.Match(input, idx);
			if (match.Rslt_none()) break;
			rv.Add(match);
			int findBgn = match.Find_bgn();
			idx = findBgn + match.Find_len();
			if (idx > String_.Len(input)) break;
		}
		return rv;
	}
	public static String Replace(String raw, String regx_str, String replace) {
		Regx_adp regx = Regx_adp_.new_(regx_str);
		return regx.ReplaceAll(raw, replace);
	}
	public static boolean Match(String input, String pattern) {
		Regx_adp rv = new Regx_adp(pattern);
		return rv.Match(input, 0).Rslt();
	}
}
