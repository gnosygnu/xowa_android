package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoEngine_ {
	public static final String SysKey = "sys";
	public static final String MemKey = "mem";

	public static final IoEngine Sys = IoEngine_system.new_();
	public static final IoEngine_memory Mem = IoEngine_memory.new_(MemKey); 
	public static IoEngine Mem_init_() {
		Mem.Clear();
		return Mem;
	}
	public static IoEngine mem_new_(String key) {return IoEngine_memory.new_(key);}
}
