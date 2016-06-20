package gplx.core.progs; import gplx.*; import gplx.core.*;
public interface Gfo_resume_wkr {
	boolean		Resuming();
	long		Get_long(byte tid);
	void		Set_long(byte tid, long v);
}
class Gfo_resume_wkr__download {
	public boolean		Resuming()					{return resuming;} private boolean resuming = true;
	public long		Get_long(byte tid)			{return val;} private long val;
	public void		Set_long(byte tid, long v)	{val = v;}
}
class Gfo_resume_wkr__unzip {
	public boolean		Resuming()					{return resuming;} private boolean resuming = true;
	public long		Get_long(byte tid)			{return val;} private long val;
	public void		Set_long(byte tid, long v)	{val = v;}
	public String	Get_str(byte tid) {return name;} private String name;
	public void		Set_str(byte tid, String v) {this.name = v;}
}
/*
[
	{ "job_uid":
	, "subs": 
	[
		{ "download"
		, "done": 0
		, "resume_bytes":123
		}
	,	{ "unzip"
		, "done": 0
		, "resume_file":abc
		, "resume_bytes":123
		}
	]
	}
]
*/
