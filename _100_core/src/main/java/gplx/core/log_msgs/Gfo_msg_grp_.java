package gplx.core.log_msgs; import gplx.*; import gplx.core.*;
public class Gfo_msg_grp_ {
	public static final    Gfo_msg_grp Root_gplx = new Gfo_msg_grp(null, Gfo_msg_grp_.Uid_next(), Bry_.new_a7("gplx"));
	public static final    Gfo_msg_grp Root = new Gfo_msg_grp(null, Gfo_msg_grp_.Uid_next(), Bry_.Empty);
	public static Gfo_msg_grp prj_(String key)						{return new Gfo_msg_grp(Root	, Gfo_msg_grp_.Uid_next(), Bry_.new_a7(key));}
	public static Gfo_msg_grp new_(Gfo_msg_grp owner, String key)	{return new Gfo_msg_grp(owner	, Gfo_msg_grp_.Uid_next(), Bry_.new_a7(key));}
	public static int Uid_next() {return uid_next++;} static int uid_next = 0;
	public static byte[] Path(byte[] owner_path, byte[] key) {
		if (owner_path != Bry_.Empty) tmp_bfr.Add(owner_path).Add_byte(Byte_ascii.Dot);	// only add "." if owner_path is available; prevents creating ".gplx"
		return tmp_bfr.Add(key).To_bry_and_clear();
	}
	static Bry_bfr tmp_bfr = Bry_bfr_.Reset(256);
}
