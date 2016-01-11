package gplx.dbs.engines.tdbs; import gplx.*; import gplx.dbs.*; import gplx.dbs.engines.*;
public class TdbFile {
	public int Id() {return id;} int id;
	public Io_url Path() {return url;} Io_url url;

	public static TdbFile new_(int id, Io_url url) {
		TdbFile rv = new TdbFile();
		rv.id = id; rv.url = url;
		return rv;
	}
	public static final int MainFileId = 1;
	public static TdbFile as_(Object obj) {return obj instanceof TdbFile ? (TdbFile)obj : null;}
	public static TdbFile cast(Object obj) {try {return (TdbFile)obj;} catch(Exception exc) {throw Err_.new_type_mismatch_w_exc(exc, TdbFile.class, obj);}}
}
