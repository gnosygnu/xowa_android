package gplx.xowa.bldrs.servers.jobs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.servers.*;
public class Xob_job_mgr {
	private Ordered_hash jobs = Ordered_hash_.New();
	public int Count() {return jobs.Count();}
	public Xob_job_itm Get_at(int i) {return (Xob_job_itm)jobs.Get_at(i);}
	public Xob_job_itm Get(String k) {return (Xob_job_itm)jobs.Get_by(k);}
	public void Load(String text) {
		jobs.Add(null, null);
	}
}
