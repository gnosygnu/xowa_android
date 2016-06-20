package gplx.dbs.engines.mems; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class Mem_qry_set {
	private final    List_adp rows = List_adp_.New();
	public int Len() {return rows.Count();}
	public Mem_row Get_at(int i) {return (Mem_row)rows.Get_at(i);}
	public void Add(Mem_row row) {rows.Add(row);}
}
