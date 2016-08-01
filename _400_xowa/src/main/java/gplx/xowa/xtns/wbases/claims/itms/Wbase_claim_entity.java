package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wbase_claim_entity extends Wbase_claim_base {
	public Wbase_claim_entity(int pid, byte snak_tid, byte entity_tid, byte[] entity_id_bry) {super(pid, snak_tid);
		this.entity_tid = entity_tid;
		this.entity_id_bry = entity_id_bry;
		this.entity_id = Bry_.To_int(entity_id_bry);
	}
	@Override public byte	Val_tid()			{return Wbase_claim_type_.Tid__entity;}
	public int				Entity_id()			{return entity_id;} private final    int entity_id;
	public byte[]			Entity_id_bry()		{return entity_id_bry;} private final    byte[] entity_id_bry;
	public byte				Entity_tid()		{return entity_tid;} private final    byte entity_tid;
	public boolean				Entity_tid_is_qid() {return entity_tid == Wbase_claim_entity_type_.Tid__item;}
	public String			Entity_tid_str()	{return Wbase_claim_entity_type_.To_str_or_fail(entity_tid);}
	public byte[]			Entity_tid_bry()	{return Wbase_claim_entity_type_.To_bry_or_fail(entity_tid);}

	public byte[] Page_ttl_db() {
		return entity_tid == Wbase_claim_entity_type_.Tid__item
			? Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_qid_bry_db, entity_id_bry)
			: Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_pid_bry, entity_id_bry)
			;
	}
	public byte[] Page_ttl_gui() {
		return entity_tid == Wbase_claim_entity_type_.Tid__item
			? Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_qid_bry_gui, entity_id_bry)
			: Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_pid_bry, entity_id_bry)
			;
	}
	@Override public void Welcome(Wbase_claim_visitor visitor) {visitor.Visit_entity(this);}
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", Wbase_claim_value_type_.To_str_or_fail(this.Snak_tid()), Wbase_claim_type_.To_key_or_unknown(this.Val_tid()), this.Entity_tid_str(), Int_.To_str(entity_id));
	}
}
