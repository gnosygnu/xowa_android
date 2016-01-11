package gplx.langs.gfs; import gplx.*; import gplx.langs.*;
import gplx.core.gfo_regys.*;
public class GfsLibIni_core implements GfsLibIni {
	public void Ini(GfsCore core) {
		core.AddCmd(GfsCoreHelp.new_(core), "help");
		core.AddObj(String_.Gfs, "String_");
		core.AddObj(Int_.Gfs, "Int_");
		core.AddObj(DateAdp_.Gfs, "Date_");
		core.AddObj(RandomAdp_.Gfs, "RandomAdp_");
		core.AddObj(GfoTemplateFactory.Instance, "factory");
		core.AddObj(GfoRegy.Instance, "GfoRegy_");
		core.AddObj(GfsCore.Instance, "GfsCore_");
		core.AddObj(gplx.core.ios.IoUrlInfoRegy.Instance, "IoUrlInfoRegy_");
		core.AddObj(gplx.core.ios.IoUrlTypeRegy.Instance, "IoUrlTypeRegy_");

		GfoRegy.Instance.Parsers().Add("Io_url", Io_url_.Parser);
	}
        public static final GfsLibIni_core Instance = new GfsLibIni_core(); GfsLibIni_core() {}
}
