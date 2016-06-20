package gplx.gfui.kits.core; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
import gplx.langs.gfs.*;
import gplx.gfui.ipts.*;
public class GfsLibIni_gfui implements GfsLibIni {
	public void Ini(GfsCore core) {
		core.AddCmd(IptCfgRegy.Instance, "IptBndMgr_");
	}
	public static final    GfsLibIni_gfui Instance = new GfsLibIni_gfui(); GfsLibIni_gfui() {}
}
