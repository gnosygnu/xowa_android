package gplx.xowa.addons.bldrs.centrals.dbs.datas; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*; import gplx.xowa.addons.bldrs.centrals.dbs.*;
public class Xobc_task_regy_itm {
	public Xobc_task_regy_itm(int id, int seqn, byte[] key, byte[] name, int step_count) {
		this.id = id;
		this.seqn = seqn;
		this.key = key;
		this.name = name;
		this.step_count = step_count;
	}
	public int Id() {return id;} private final    int id;
	public int Seqn() {return seqn;} private final    int seqn;
	public byte[] Key() {return key;} private final    byte[] key;
	public byte[] Name() {return name;} private final    byte[] name;
	public int Step_count() {return step_count;} private final    int step_count;
}
