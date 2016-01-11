package gplx.xowa.xtns.math.texvcs.tkns; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.math.*; import gplx.xowa.xtns.math.texvcs.*;
public interface Texvc_tkn {
	int					Tid();
	Texvc_root			Root();
	int					Uid();
	int					Src_bgn();
	int					Src_end();
	void				Src_end_(int v);
	Texvc_tkn			Init(Texvc_root root, int tid, int uid, int src_bgn, int src_end);
	int					Subs__len();
	Texvc_tkn			Subs__get_at(int i);
	void				Print_dbg_bry(Bry_bfr bfr, int indent);
	void				Print_tex_bry(Bry_bfr bfr, byte[] src, int indent);
}
