package gplx.core.envs; import gplx.*; import gplx.core.*;
public class Runtime_ {
	// *** Hardware-related
	public static int  Cpu_count() {return Runtime.getRuntime().availableProcessors();}		//#<>0~Runtime.getRuntime().availableProcessors()
	public static long Memory_max() {return Runtime.getRuntime().maxMemory();}		//#<>0~Runtime.getRuntime().maxMemory()
	public static long Memory_total() {return Runtime.getRuntime().totalMemory();}	//#<>0~Runtime.getRuntime().totalMemory()
	public static long Memory_free() {return Runtime.getRuntime().freeMemory();}	//#<>0~Runtime.getRuntime().freeMemory()
}