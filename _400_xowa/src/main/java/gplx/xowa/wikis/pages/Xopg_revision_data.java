package gplx.xowa.wikis.pages; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*;
public class Xopg_revision_data {
	public int		Id() {return id;} public Xopg_revision_data Id_(int v) {id = v; return this;} private int id;
	public DateAdp	Modified_on() {return modified_on;} public Xopg_revision_data Modified_on_(DateAdp v) {modified_on = v; return this;} private DateAdp modified_on = DateAdp_.MinValue;
	public byte[]	User() {return user;} public Xopg_revision_data User_(byte[] v) {user = v; return this;} private byte[] user = Bry_.Empty;
	public byte[]	Protection_level() {return protection_level;} public Xopg_revision_data Protection_level_(byte[] v) {protection_level = v; return this;} private byte[] protection_level = Bry_.Empty;
	public byte[]	Protection_expiry() {return protection_expiry;} private byte[] protection_expiry = Bry__protection_expiry__infinite;
	public int		Html_db_id() {return html_db_id;} public void Html_db_id_(int v) {html_db_id = v;} private int html_db_id = -1;
	public void		Clear()	{}// NOTE: do not clear data b/c saving in Edit will call clear and id will be reset to 0
	public static final byte[] Bry__protection_expiry__infinite = Bry_.new_a7("infinite");
}
