package gplx.xowa.xtns.math; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.brys.fmtrs.*; import gplx.core.strings.*; import gplx.core.consoles.*; import gplx.core.envs.*;
import gplx.xowa.apps.progs.*;
public class Xof_math_mgr implements GfoInvkAble {
	private Xoae_app app;
	public Process_adp Cmd_convert_tex_to_dvi() {return cmd_convert_tex_to_dvi;} private Process_adp cmd_convert_tex_to_dvi = new Process_adp();
	public Process_adp Cmd_convert_dvi_to_png() {return cmd_convert_dvi_to_png;} private Process_adp cmd_convert_dvi_to_png = new Process_adp();
	public void Init_by_app(Xoae_app app) {
		this.app = app;
		Xoa_prog_mgr app_mgr = app.Prog_mgr();
		cmd_convert_tex_to_dvi = app_mgr.App_convert_tex_to_dvi();
		cmd_convert_dvi_to_png = app_mgr.App_convert_dvi_to_png();
	}
	private Io_url Make_math_dir(String wiki_key) {return app.Fsys_mgr().Root_dir().GenSubDir_nest("file", wiki_key, "math");}
	public Xof_math_html_wtr Html_wtr() {return html_wtr;} private final Xof_math_html_wtr html_wtr = new Xof_math_html_wtr();
	public void Make_itm(Xof_math_itm rv, String wiki_key, byte[] math_bry) {
		Io_url math_dir = Make_math_dir(wiki_key);
		math_bry = app.Math_subst_regy().Subst(math_bry);
		String md5 = gplx.core.security.HashAlgo_.Md5.CalcHash(Console_adp_.Noop, gplx.core.ios.IoStream_.ary_(math_bry));
		Io_url png_fil = Make_png_fil(math_dir, md5);
		rv.Ctor(math_bry, md5, png_fil);
	}
	public boolean Find_itm(Xof_math_itm rv, String wiki_key, byte[] math_bry) {
		Make_itm(rv, wiki_key, math_bry);
		return Io_mgr.Instance.ExistsFil(rv.Png_url());
	}
	public boolean Renderer_is_mathjax() {return renderer_is_mathjax;} public void Renderer_is_mathjax_(boolean v) {renderer_is_mathjax = v;} private boolean renderer_is_mathjax = true;
	private Io_url Make_png_fil(Io_url math_dir, String hash) {
		String Math_dir_spr = math_dir.Info().DirSpr();
		tmp_sb.Clear().Add(math_dir.Raw())
			.Add(String_.CharAt(hash, 0)).Add(Math_dir_spr)
			.Add(String_.CharAt(hash, 1)).Add(Math_dir_spr)
			.Add(String_.CharAt(hash, 2)).Add(Math_dir_spr)
			.Add(hash).Add(".png")
			;
		return Io_url_.new_fil_(tmp_sb.To_str_and_clear());
	}
	public boolean MakePng(byte[] math, String hash, Io_url png_url, String prog_fmt) {
		if (!enabled) return false;
		Io_url tmp_dir = app.Usere().Fsys_mgr().App_temp_dir().GenSubDir("math"); // cmd_convert_tex_to_dvi.Tmp_dir();
		Io_url tex_url = tmp_dir.GenSubFil("xowa_math_temp.tex");
		String latex = Latex_wrap(math);
		prog_fmt = String_.Replace(prog_fmt, "~", "~~");	// double-up ~ or else will break in progress bar
		Io_mgr.Instance.SaveFilStr(tex_url, latex);
		cmd_convert_tex_to_dvi.Prog_fmt_(prog_fmt + " tex_to_dvi: ~{process_seconds} second(s); ~{process_exe_name} ~{process_exe_args}");
		boolean pass = cmd_convert_tex_to_dvi.Run(tex_url.Raw(), tmp_dir.Xto_api()).Exit_code_pass();
		if (!pass) {
			app.Usr_dlg().Warn_many(GRP_KEY, "tex_to_dvi.fail", "fail: tex_to_dvi: error=~{0} latex=~{1}", cmd_convert_tex_to_dvi.Rslt_out(), latex);
		}
		// NOTE: latex sometimes throws errors, but will generate .dvi; for sake of simplicity; always try to run dvipng
		Io_mgr.Instance.CreateDirIfAbsent(png_url.OwnerDir());
		cmd_convert_dvi_to_png.Prog_fmt_(prog_fmt + " dvi_to_png: ~{process_seconds} second(s); ~{process_exe_name} ~{process_exe_args}");
		pass = cmd_convert_dvi_to_png.Run(tex_url.GenNewExt(".dvi"), png_url, tmp_dir.Xto_api()).Exit_code_pass();
		if (!pass) {
			app.Usr_dlg().Warn_many(GRP_KEY, "dvi_to_png.fail", "fail: dvi_to_png: error=~{0} latex=~{1}", cmd_convert_tex_to_dvi.Rslt_out(), latex);
		}
		return pass;
	}
	private String_bldr tmp_sb = String_bldr_.new_();
	private String Latex_wrap(byte[] math) {return Latex_doc_fmtr.Bld_str_many(String_.Replace(String_.new_u8(math), "\n\n", "\n"));}	// NOTE: remove lines that are completely blank; not sure if this is right; PAGE:en.w:Standard Model (mathematical formulation); <math>(\mathbf{1},\mathbf\n\n{1},0)</math>
	private static Bry_fmtr Latex_doc_fmtr = new Bry_fmtr()
	.Fmt_(String_.Concat_lines_nl_skip_last
	(	"\\documentclass{article}"
	,	"\\usepackage{amsmath}"
	,	"\\usepackage{amsfonts}"
	,	"\\usepackage{amssymb}"
	,	"\\usepackage{color}"
	,	"\\usepackage[landscape]{geometry}"
	,	"\\pagestyle{empty}"
	,	"\\begin{document}"
	,	"\\begin{Large}"
	,	"\\nonumber"
	,	"$\\displaystyle"
	,	"~{0}"
	,	"$\\end{Large}"
	,	"\\end{document}"
	));
	public boolean Enabled() {return enabled;} public Xof_math_mgr Enabled_(boolean v) {enabled = v; return this;} private boolean enabled = true;
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_enabled))		return Yn.To_str(enabled);
		else if	(ctx.Match(k, Invk_enabled_))		enabled = m.ReadYn("v");
		else if	(ctx.Match(k, Invk_renderer))		return renderer_is_mathjax ? "mathjax" : "latex";
		else if	(ctx.Match(k, Invk_renderer_))		renderer_is_mathjax = String_.Eq(m.ReadStr("v"), "mathjax");
		else if	(ctx.Match(k, Invk_renderer_list))	return Options_renderer_list;
		else	return GfoInvkAble_.Rv_unhandled;
		return this;
	}	private static final String Invk_enabled = "enabled", Invk_enabled_ = "enabled_", Invk_renderer = "renderer", Invk_renderer_ = "renderer_", Invk_renderer_list = "renderer_list";
	private static KeyVal[] Options_renderer_list = KeyVal_.Ary(KeyVal_.new_("mathjax", "MathJax"), KeyVal_.new_("latex", "LaTeX")); 
	private static final String GRP_KEY = "xowa.math.mgr";
}
