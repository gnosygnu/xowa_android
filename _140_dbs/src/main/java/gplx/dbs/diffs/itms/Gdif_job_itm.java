package gplx.dbs.diffs.itms; import gplx.*; import gplx.dbs.*; import gplx.dbs.diffs.*;
public class Gdif_job_itm {
	public Gdif_job_itm(int id, String name, String made_by, DateAdp made_on) {
		this.Id = id; this.Name = name; this.Made_by = made_by; this.Made_on = made_on;
	}
	public final int Id;
	public final String Name;
	public final String Made_by;
	public final DateAdp Made_on;
	public String Data;
}
