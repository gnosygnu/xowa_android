package gplx.xowa.xtns.pfuncs.exprs; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.parsers.*;
class Func_tkn_stack {
	public void Clear() {stack_len = 0;}
	public int Len() {return stack_len;}
	public Func_tkn GetLast() {return stack_len == 0 ? null : stack[stack_len - 1];}
	public Func_tkn Pop() {
		int stack_len_new = stack_len - 1;
		Func_tkn rv = stack[stack_len_new];
		stack_len = stack_len_new;
		return rv;
	}
	public void Push(Func_tkn v) {
		int stack_len_new = stack_len + 1;
		if (stack_len_new > stack_max) {
			stack_max = stack_len_new * 2;
			stack = (Func_tkn[])Array_.Resize(stack, stack_max);
		}
		stack[stack_len] = v;
		stack_len = stack_len_new;
	}
	Func_tkn[] stack = new Func_tkn[0]; int stack_len = 0, stack_max = 0;
}
