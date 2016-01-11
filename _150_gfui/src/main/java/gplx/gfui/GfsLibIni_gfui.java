package gplx.gfui; import gplx.*;
import gplx.langs.gfs.*;
public class GfsLibIni_gfui implements GfsLibIni {
	public void Ini(GfsCore core) {
		core.AddCmd(IptCfgRegy.Instance, "IptBndMgr_");
	}
	public static final GfsLibIni_gfui Instance = new GfsLibIni_gfui(); GfsLibIni_gfui() {}
}
