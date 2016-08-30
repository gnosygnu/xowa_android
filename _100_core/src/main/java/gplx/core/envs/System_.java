package gplx.core.envs; import gplx.*; import gplx.core.*;
public class System_ {
	// *** ticks
	public static long	Ticks() {return Ticks__test_val >= 0 ? Ticks__test_val : System.currentTimeMillis();} //#<>System.Environment.TickCount~System.currentTimeMillis()
	public static int	Ticks__elapsed_in_sec	(long time_bgn) {return (int)(Ticks() - time_bgn) / 1000;}
	public static int	Ticks__elapsed_in_frac	(long time_bgn) {return (int)(Ticks() - time_bgn);}
	public static void	Ticks__test_set(long v) {Ticks__test_val = v;}
	public static void	Ticks__test_add(long v) {Ticks__test_val += v;}
	public static void	Ticks__test_off()		{Ticks__test_val = -1;}
	private static long	Ticks__test_val = -1;	// in milliseconds

	// *** misc methods: Exit / GC
	public static void Exit() {Exit_by_code(0);}
	public static void Exit_by_code(int code) {System.exit(code);}	//#<>System.Environment.Exit~System.exit
	public static void Garbage_collect() {if (Env_.Mode_testing()) return; System.gc();}				//#<>System.GC.Collect~System.gc

	// *** java properties: getProperty; "-D" properties
	public static String Prop__user_language()	{return Prop__get(Prop_key__user_language);}
	public static String Prop__user_name()		{return Prop__get(Prop_key__user_name);}	//#<>System.Environment.UserName~Prop__get(Prop_key__user_name)
	public static String Prop__java_version()	{return Prop__get(Prop_key__java_version);}
	public static String Prop__java_home()		{return Prop__get(Prop_key__java_home);}
	private static String Prop__get(String key) {
		//#{Prop__get
		return System.getProperty(key);
		//#}
	}
	private static final String
	  Prop_key__user_language	= "user.language"
	, Prop_key__user_name		= "user.name"
	, Prop_key__java_version	= "java.version"
	, Prop_key__java_home		= "java.version"
	;

	// *** environment variables: getenv
	public static String Env__machine_name() {
		String rv = "UNKNOWN_MACHINE_NAME";
		rv = Env__get(Env_key__computername);	if (String_.Len_gt_0(rv)) return rv;
		rv = Env__get(Env_key__hostname);		if (String_.Len_gt_0(rv)) return rv;
		return rv;
	}
	private static String Env__get(String key) {
		//#{Env__get
		return System.getenv(key);
		//#}
	}
	private static final String
	  Env_key__computername = "COMPUTERNAME"
	, Env_key__hostname = "HOSTNAME"
	;
}
