package gplx.xowa.parsers.tmpls; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xot_defn_trace_null implements Xot_defn_trace {
	public void Clear() {}
	public void Trace_bgn(Xop_ctx ctx, byte[] src, byte[] name, Xot_invk caller, Xot_invk self, Xot_defn defn) {}
	public void Trace_end(int trg_bgn, Bry_bfr trg) {}
	public void Print(byte[] src, Bry_bfr bb) {}
	public static final Xot_defn_trace_null Instance = new Xot_defn_trace_null(); Xot_defn_trace_null() {}
}
