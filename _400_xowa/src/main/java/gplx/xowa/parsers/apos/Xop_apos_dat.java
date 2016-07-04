package gplx.xowa.parsers.apos; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_apos_dat {
	public int State() {return state;} public void State_clear() {state = Xop_apos_tkn_.State_nil;} private int state = Xop_apos_tkn_.State_nil;
	public int Typ() {return typ;} private int typ;
	public int Cmd() {return cmd;} private int cmd;
	public int Lit_apos() {return lit_apos;} private int lit_apos;
	public int Dual_cmd() {return dual_cmd;} private int dual_cmd;
	public void Ident(Xop_ctx ctx, byte[] src, int apos_len, int cur_pos) {
		typ = cmd = lit_apos = dual_cmd = 0;
		switch (apos_len) {
			case Xop_apos_tkn_.Len_ital: case Xop_apos_tkn_.Len_bold: case Xop_apos_tkn_.Len_dual:
				Ident_props(apos_len); break;
			case Xop_apos_tkn_.Len_apos_bold:
				lit_apos = 1;
				Ident_props(Xop_apos_tkn_.Len_bold); break;
			default:
				lit_apos = apos_len - Xop_apos_tkn_.Len_dual;
				Ident_props(Xop_apos_tkn_.Len_dual);
				break;
		}
	}
	private void Ident_props(int apos_len) {
		typ = apos_len;
		switch (apos_len) {
			case Xop_apos_tkn_.Len_ital:	{
				switch (state) {
					case Xop_apos_tkn_.State_i:		cmd = Xop_apos_tkn_.Cmd_i_end;			state = Xop_apos_tkn_.State_nil;	break;
					case Xop_apos_tkn_.State_bi:	cmd = Xop_apos_tkn_.Cmd_i_end;			state = Xop_apos_tkn_.State_b;		break;
					case Xop_apos_tkn_.State_ib:	cmd = Xop_apos_tkn_.Cmd_bi_end__b_bgn;	state = Xop_apos_tkn_.State_b;		break;
					case Xop_apos_tkn_.State_dual:	cmd = Xop_apos_tkn_.Cmd_i_end;			state = Xop_apos_tkn_.State_b;		dual_cmd = Xop_apos_tkn_.Cmd_bi_bgn; break;
					case Xop_apos_tkn_.State_b:		cmd = Xop_apos_tkn_.Cmd_i_bgn;			state = Xop_apos_tkn_.State_bi;		break;
					case Xop_apos_tkn_.State_nil:	cmd = Xop_apos_tkn_.Cmd_i_bgn;			state = Xop_apos_tkn_.State_i;		break;
					default:						throw Err_.new_unhandled(state);
				}
				break;
			}
			case Xop_apos_tkn_.Len_bold:	{
				switch (state) {
					case Xop_apos_tkn_.State_b:		cmd = Xop_apos_tkn_.Cmd_b_end;			state = Xop_apos_tkn_.State_nil;	break;
					case Xop_apos_tkn_.State_bi:	cmd = Xop_apos_tkn_.Cmd_ib_end__i_bgn;	state = Xop_apos_tkn_.State_i;		break;
					case Xop_apos_tkn_.State_ib:	cmd = Xop_apos_tkn_.Cmd_b_end;			state = Xop_apos_tkn_.State_i;		break;
					case Xop_apos_tkn_.State_dual:	cmd = Xop_apos_tkn_.Cmd_b_end;			state = Xop_apos_tkn_.State_i;		break; // NOTE: dual_cmd = Cmd_ib_bgn is implied
					case Xop_apos_tkn_.State_i:		cmd = Xop_apos_tkn_.Cmd_b_bgn;			state = Xop_apos_tkn_.State_ib;		break;
					case Xop_apos_tkn_.State_nil:	cmd = Xop_apos_tkn_.Cmd_b_bgn;			state = Xop_apos_tkn_.State_b;		break;
					default:						throw Err_.new_unhandled(state);
				}
				break;
			}
			case Xop_apos_tkn_.Len_dual:	{
				switch (state) {
					case Xop_apos_tkn_.State_b:		cmd = Xop_apos_tkn_.Cmd_b_end__i_bgn;	state = Xop_apos_tkn_.State_i;		break;
					case Xop_apos_tkn_.State_i:		cmd = Xop_apos_tkn_.Cmd_i_end__b_bgn;	state = Xop_apos_tkn_.State_b;		break;
					case Xop_apos_tkn_.State_bi:	cmd = Xop_apos_tkn_.Cmd_ib_end;			state = Xop_apos_tkn_.State_nil;	break;
					case Xop_apos_tkn_.State_ib:	cmd = Xop_apos_tkn_.Cmd_bi_end;			state = Xop_apos_tkn_.State_nil;	break;
					case Xop_apos_tkn_.State_dual:	cmd = Xop_apos_tkn_.Cmd_bi_end;			state = Xop_apos_tkn_.State_nil;	break; // NOTE: dual_cmd = Cmd_ib_bgn is implied
					case Xop_apos_tkn_.State_nil:	cmd = Xop_apos_tkn_.Cmd_ib_bgn;			state = Xop_apos_tkn_.State_dual;	break;
					default:						throw Err_.new_unhandled(state);
				}
				break;
			}
			default: throw Err_.new_unhandled(apos_len);
		}
	}
}
