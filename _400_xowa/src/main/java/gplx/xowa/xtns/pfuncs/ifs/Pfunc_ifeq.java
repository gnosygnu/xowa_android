package gplx.xowa.xtns.pfuncs.ifs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Pfunc_ifeq extends Pf_func_base {//_20120329	// {{#ifeq:lhs|rhs|equal|not_equal}}
	@Override public boolean Func_require_colon_arg() {return true;}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		int self_args_len = self.Args_len(); if (self_args_len < 2) return; // no equal/not_equal clauses defined; return; EX: {{#if:a}} {{#if:a|b}}
		byte[] lhs = Eval_argx(ctx, src, caller, self);
		byte[] rhs = Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self_args_len, 0);
		if (Pf_func_.Eq_(lhs, rhs))
			bfr.Add(Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self_args_len, 1));
		else
			bfr.Add(Pf_func_.Eval_arg_or_empty(ctx, src, caller, self, self_args_len, 2));
	}
	@Override public int Id() {return Xol_kwd_grp_.Id_xtn_ifeq;}
	@Override public Pf_func New(int id, byte[] name) {return new Pfunc_ifeq().Name_(name);}
}	
