package gplx.xowa.files.bins; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
import gplx.core.ios.*; import gplx.core.ios.streams.*; import gplx.core.envs.*;
import gplx.xowa.files.fsdb.*; import gplx.xowa.files.repos.*;
public abstract class Xof_bin_wkr__fsys_base implements Xof_bin_wkr, Gfo_invk {
	public Xof_bin_wkr__fsys_base() {}
	public abstract byte Tid();
	public abstract String Key();
	public boolean Resize_allowed() {return resize_allowed;} public void Resize_allowed_(boolean v) {resize_allowed = v;} private boolean resize_allowed = false;
	public Io_stream_rdr Get_as_rdr(Xof_fsdb_itm itm, boolean is_thumb, int w) {
		Io_url src_url = this.Get_src_url(Xof_repo_itm_.Mode_by_bool(is_thumb), String_.new_u8(itm.Orig_repo_name()), itm.Orig_ttl(), itm.Orig_ttl_md5(), itm.Orig_ext(), w, itm.Lnki_time(), itm.Lnki_page());
		return (src_url == Io_url_.Empty) ? gplx.core.ios.streams.Io_stream_rdr_.Noop : gplx.core.ios.streams.Io_stream_rdr_.file_(src_url);
	}
	public boolean Get_to_fsys(Xof_fsdb_itm itm, boolean is_thumb, int w, Io_url bin_url) {
		return Get_to_fsys(itm.Orig_repo_name(), itm.Orig_ttl(), itm.Orig_ttl_md5(), itm.Orig_ext(), is_thumb, w, itm.Lnki_time(), itm.Lnki_page(), bin_url);
	}
	private boolean Get_to_fsys(byte[] orig_repo, byte[] orig_ttl, byte[] orig_md5, Xof_ext orig_ext, boolean lnki_is_thumb, int file_w, double lnki_time, int lnki_page, Io_url file_url) {
		Io_url src_url = this.Get_src_url(Xof_repo_itm_.Mode_by_bool(lnki_is_thumb), String_.new_u8(orig_repo), orig_ttl, orig_md5, orig_ext, file_w, lnki_time, lnki_page);
		if (src_url == Io_url_.Empty) return false;
		byte[] bin = Io_mgr.Instance.LoadFilBry(src_url);
		return bin != Io_mgr.LoadFilBry_fail;
	}
	protected abstract Io_url Get_src_url(byte mode, String wiki, byte[] ttl_wo_ns, byte[] md5, Xof_ext ext, int w, double time, int page);
	public abstract void Url_(Io_url v);
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_url_))		this.Url_(m.ReadIoUrl("v"));
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}	private static final String Invk_url_ = "url_";
}
abstract class Xof_bin_wkr__fsys_wmf_base extends Xof_bin_wkr__fsys_base {
	public Xof_url_bldr Url_bldr() {return url_bldr;} private Xof_url_bldr url_bldr = new Xof_url_bldr();
	public abstract void Init_by_root();
	@Override public void Url_(Io_url v) {url_bldr.Root_(Bry_.new_u8(v.Raw()));}
	@Override protected Io_url Get_src_url(byte mode, String wiki, byte[] ttl_wo_ns, byte[] md5, Xof_ext ext, int w, double time, int page) {
		return this.Url_bldr().Init_by_itm(mode, ttl_wo_ns, md5, ext, w, time, page).Xto_url();
	}
}
class Xof_bin_wkr__fsys_wmf extends Xof_bin_wkr__fsys_wmf_base {
	@Override public byte Tid() {return Xof_bin_wkr_.Tid_fsys_wmf;}
	@Override public String Key() {return Xof_bin_wkr_.Key_fsys_wmf;}
	@Override public void Init_by_root() {
		this.Url_bldr().Init_by_root(Bry_.Empty, Op_sys.Cur().Tid_is_wnt(), Op_sys.Cur().Fsys_dir_spr_byte(), Bool_.Y, Bool_.Y, Xof_repo_itm_.Dir_depth_wmf);
	}
}
class Xof_bin_wkr__fsys_xowa extends Xof_bin_wkr__fsys_wmf_base {
	@Override public byte Tid() {return Xof_bin_wkr_.Tid_fsys_xowa;}
	@Override public String Key() {return Xof_bin_wkr_.Key_fsys_xowa;}
	@Override public void Init_by_root() {
		this.Url_bldr().Init_by_root(Bry_.Empty, Op_sys.Cur().Tid_is_wnt(), Op_sys.Cur().Fsys_dir_spr_byte(), Bool_.N, Bool_.N, Xof_repo_itm_.Dir_depth_xowa);
	}
}
