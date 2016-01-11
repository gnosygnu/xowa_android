package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoEngine_xrg_deleteFil extends IoEngine_xrg_fil_affects1_base {
	@gplx.New public IoEngine_xrg_deleteFil Url_(Io_url val) {Url_set(val); return this;}
	public IoEngine_xrg_deleteFil ReadOnlyFails_off() {return ReadOnlyFails_(false);} public IoEngine_xrg_deleteFil ReadOnlyFails_(boolean v) {ReadOnlyFails_set(v); return this;}
	public IoEngine_xrg_deleteFil MissingFails_off() {return MissingFails_(false);} public IoEngine_xrg_deleteFil MissingFails_(boolean v) {MissingFails_set(v); return this;}
	@Override public void Exec() {IoEnginePool.Instance.Get_by(this.Url().Info().EngineKey()).DeleteFil_api(this);}
        public static IoEngine_xrg_deleteFil proto_() {return new IoEngine_xrg_deleteFil();}
	public static IoEngine_xrg_deleteFil new_(Io_url url) {
		IoEngine_xrg_deleteFil rv = new IoEngine_xrg_deleteFil();
		rv.Url_set(url);
		return rv;
	}	IoEngine_xrg_deleteFil() {}
}	
