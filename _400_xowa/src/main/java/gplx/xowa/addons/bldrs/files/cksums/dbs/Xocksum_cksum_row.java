package gplx.xowa.addons.bldrs.files.cksums.dbs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.files.*; import gplx.xowa.addons.bldrs.files.cksums.*;
public class Xocksum_cksum_row {
	public Xocksum_cksum_row(int fil_id, int thm_id, int bin_db_id, long bin_size, byte cksum_tid, int cksum_count, byte[] cksum_val, String cksum_date) {
		this.fil_id = fil_id;
		this.thm_id = thm_id;
		this.bin_db_id = bin_db_id;
		this.bin_size = bin_size;
		this.cksum_tid = cksum_tid;
		this.cksum_count = cksum_count;
		this.cksum_val = cksum_val;
		this.cksum_date = cksum_date;
	}
	public int Fil_id() {return fil_id;} private final    int fil_id;
	public int Thm_id() {return thm_id;} private final    int thm_id;
	public int Bin_db_id() {return bin_db_id;} private final    int bin_db_id;
	public long Bin_size() {return bin_size;} private long bin_size;
	public byte Cksum_tid() {return cksum_tid;} private final    byte cksum_tid;
	public int Cksum_count() {return cksum_count;} private final    int cksum_count;
	public byte[] Cksum_val() {return cksum_val;} private byte[] cksum_val;
	public String Cksum_date() {return cksum_date;} private final    String cksum_date;

	public void Bin_size_(long v) {this.bin_size = v;}
	public void Cksum_val_(byte[] v) {this.cksum_val = v;}
}
