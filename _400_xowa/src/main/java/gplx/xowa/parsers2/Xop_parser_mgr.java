package gplx.xowa.parsers2; import gplx.*; import gplx.xowa.*;
class Xop_parser_mgr {
//		public Xop_parser_env Envs__get_or_new() {
//			return null;
//		}
	public Xop_parser_wkr Wkrs__get_or_new() {
		return null;
	}
}
interface Xop_parser_env {
}
interface Xop_parser_wkr {
	void Parse_to_bfr(Xop_parser_env env, Bry_bfr bfr, byte[] src);
}
