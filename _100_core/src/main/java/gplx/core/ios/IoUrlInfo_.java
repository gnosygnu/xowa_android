package gplx.core.ios; import gplx.*; import gplx.core.*;
public class IoUrlInfo_ {//_20101005
	public static final IoUrlInfo Nil = IoUrlInfo_nil.Instance;
	public static final IoUrlInfo Wnt = IoUrlInfo_wnt.Instance;
	public static final IoUrlInfo Lnx = IoUrlInfo_lnx.Instance;
	public static final IoUrlInfo Mem = IoUrlInfo_mem.new_("mem", IoEngine_.MemKey);

	public static IoUrlInfo mem_(String key, String engineKey) {return IoUrlInfo_mem.new_(key, engineKey);}
	public static IoUrlInfo alias_(String srcRoot, String trgRoot, String engineKey) {return IoUrlInfo_alias.new_(srcRoot, trgRoot, engineKey);}
}
/*
wnt		C:\dir\fil.txt
wce		\dir\fil.txt	
lnx		/dir/fil.txt
mem		mem/dir/fil.txt
alias	app:\dir\fil.txt
*/