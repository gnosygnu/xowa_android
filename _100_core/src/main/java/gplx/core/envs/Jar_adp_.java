package gplx.core.envs; import gplx.*; import gplx.core.*;
public class Jar_adp_ {
	public static DateAdp ModifiedTime_type(Class<?> type) {if (type == null) throw Err_.new_null();
		Io_url url = Url_type(type);
		return Io_mgr.Instance.QueryFil(url).ModifiedTime();
	}
	public static Io_url Url_type(Class<?> type) {if (type == null) throw Err_.new_null();
		//#{Url_type
		String codeBase = type.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (Op_sys.Cur().Tid_is_wnt())
			codeBase = String_.Mid(codeBase, 1);	// codebase always starts with /; remove for wnt 
		codeBase = String_.Replace(codeBase, "/", Op_sys.Cur().Fsys_dir_spr_str());	// java always returns DirSpr as /; change to Env_.DirSpr to handle windows
		try   {codeBase = java.net.URLDecoder.decode(codeBase, "UTF-8");}
		catch (java.io.UnsupportedEncodingException e) {Err_.Noop(e);}
		//#}
		return Io_url_.new_fil_(codeBase);
	}
}
