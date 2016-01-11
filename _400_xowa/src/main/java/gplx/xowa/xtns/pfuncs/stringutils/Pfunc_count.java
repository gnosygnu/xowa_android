package gplx.xowa.xtns.pfuncs.stringutils; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_count extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_strx_count;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_count().Name_(name);}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] str = Eval_argx(ctx, src, caller, self);
		int self_args_len = self.Args_len();
		byte[] find = Pf_func_.Eval_arg_or(ctx, src, caller, self, self_args_len, 0, null); if (find == null) find = Byte_ascii.Space_bry;
		bfr.Add_int_variable(Count(str, find));
	}
	public static int Count(byte[] src, byte[] find) {
		int src_len = src.length; int find_len = find.length;
		int pos = 0;
		int rv = 0;
		while (true) {
			int find_pos = Bry_find_.Find_fwd(src, find, pos, src_len);
			if (find_pos == Bry_find_.Not_found) break;
			pos = find_pos + find_len;
			++rv;
		}
		return rv;
	}
}	
