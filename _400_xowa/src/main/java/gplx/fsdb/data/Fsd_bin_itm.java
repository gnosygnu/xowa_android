package gplx.fsdb.data; import gplx.*; import gplx.fsdb.*;
public class Fsd_bin_itm {
	public Fsd_bin_itm(int bin_owner_id, byte bin_owner_tid, int bin_part_id, String bin_data_url, byte[] bin_data) {
		this.bin_owner_id = bin_owner_id;
		this.bin_owner_tid = bin_owner_tid;
		this.bin_part_id = bin_part_id;
		this.bin_data_url = bin_data_url;
		this.bin_data = bin_data;
	}
	public int Bin_owner_id() {return bin_owner_id;} private final    int bin_owner_id;
	public byte Bin_owner_tid() {return bin_owner_tid;} private final    byte bin_owner_tid;
	public int Bin_part_id() {return bin_part_id;} private final    int bin_part_id;
	public String Bin_data_url() {return bin_data_url;} private final    String bin_data_url;
	public byte[] Bin_data() {return bin_data;} private final    byte[] bin_data;

	public static final int Db_row_size_fixed = (3 * 4);	// bin_owner_id, bin_part_id, bin_owner_tid (assume byte saved as int in SQLITE)
}
