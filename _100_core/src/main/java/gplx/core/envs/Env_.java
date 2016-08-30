package gplx.core.envs; import gplx.*; import gplx.core.*;
public class Env_ {
	public static void Init(String[] args, String appNameAndExt, Class<?> type) {
		mode_testing = false;
		mode_debug = String_.In("GPLX_DEBUG_MODE_ENABLED", args);
		appArgs = args;
		appUrl = Jar_adp_.Url_type(type).OwnerDir().GenSubFil(appNameAndExt);
	}
	public static void Init_swt(String[] args, Class<?> type) {	// DATE:2014-06-23
		mode_testing = false;
		mode_debug = String_.In("GPLX_DEBUG_MODE_ENABLED", args);
		appArgs = args;
		appUrl = Jar_adp_.Url_type(type);
	}
	public static void Init_drd() {
		mode_testing = mode_debug = false;
	}
	public static void Init_testing()		{mode_testing = true;}
	public static void Init_testing_n_()	{mode_testing = false;}
	public static boolean Mode_testing()		{return mode_testing;}	private static boolean mode_testing = true;
	public static boolean Mode_debug()			{return mode_debug;}	private static boolean mode_debug = false;
	public static String[] AppArgs() {return appArgs;} static String[] appArgs;
	public static Io_url AppUrl() {
		if (mode_testing) return Io_url_.mem_fil_("mem/testing.jar");
		if (appUrl == Io_url_.Empty) throw Err_.new_wo_type("Env_.Init was not called");
		return appUrl;
	}	static Io_url appUrl = Io_url_.Empty;

	public static final    String LocalHost = "127.0.0.1";
	public static String NewLine_lang() {return mode_testing ? "\n" : "\n";}	//#<>\r\n~\n
}
