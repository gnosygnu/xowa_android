package gplx.langs.phps; import gplx.*; import gplx.langs.*;
class Php_tkn_factory {
	public Php_tkn_generic 				Generic(int bgn, int end, byte tid) {return new Php_tkn_generic(bgn, end, tid);}
	public Php_tkn_txt					Txt(int bgn, int end) {return new Php_tkn_txt(bgn, end);}
	public Php_tkn 						Declaration(int bgn, int end) {return Php_tkn_declaration.Instance;}
	public Php_tkn_ws 					Ws(int bgn, int end, byte ws_tid) {return new Php_tkn_ws(bgn, end, ws_tid);}
	public Php_tkn_var 					Var(int bgn, int end) {return new Php_tkn_var(bgn, end);}
	public Php_tkn_num 					Num(int bgn, int end) {return new Php_tkn_num(bgn, end);}
	public Php_tkn_comment 				Comment(int bgn, int end, byte comment_tid) {return new Php_tkn_comment(bgn, end, comment_tid);}
	public Php_tkn_quote 				Quote(int bgn, int end, byte quote_tid) {return new Php_tkn_quote(bgn, end, quote_tid);}
}
