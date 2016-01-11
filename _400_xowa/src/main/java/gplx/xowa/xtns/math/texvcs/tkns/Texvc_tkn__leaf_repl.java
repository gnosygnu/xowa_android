package gplx.xowa.xtns.math.texvcs.tkns; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
public class Texvc_tkn__leaf_repl implements Texvc_tkn {
	private final byte[] repl;
	public Texvc_tkn__leaf_repl(int tid, byte[] repl) {
		this.tid = tid;
		this.repl = repl;
	}
	public Texvc_tkn Init(Texvc_root root, int tid, int uid, int src_bgn, int src_end) {
		this.root = root;
		this.uid = uid;
		this.src_bgn = src_bgn;
		this.src_end = src_end;
		return this;
	}
	public Texvc_root Root() {return root;} private Texvc_root root;
	public int Tid() {return tid;} private final int tid;
	public int Uid() {return uid;} private int uid;
	public int Src_bgn() {return src_bgn;} private int src_bgn;
	public int Src_end() {return src_end;} private int src_end;
	public void Src_end_(int v) {this.src_end = v;}
	public int Subs__len() {return 0;}
	public Texvc_tkn Subs__get_at(int i) {throw Err_.new_unsupported();}
	public void Print_tex_bry(Bry_bfr bfr, byte[] src, int indent) {bfr.Add(repl);}
	public void Print_dbg_bry(Bry_bfr bfr, int indent) {
		Texvc_tkn_.Print_dbg_str__bgn(bfr, indent, this);
		Texvc_tkn_.Print_dbg_str__end_head(bfr);
	}
}
