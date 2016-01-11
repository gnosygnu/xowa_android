package gplx.xowa.xtns.wdatas.core; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wdatas.*;
import gplx.xowa.xtns.wdatas.hwtrs.*;
public class Wdata_claim_itm_entity extends Wdata_claim_itm_core { //#*inherit
	public Wdata_claim_itm_entity(int pid, byte snak_tid, byte entity_tid, byte[] entity_id_bry) {
		this.Ctor(pid, snak_tid);
		this.entity_tid = entity_tid;
		this.entity_id_bry = entity_id_bry;
		this.entity_id = Bry_.To_int(entity_id_bry);
	}
	@Override public byte Val_tid() {return Wdata_dict_val_tid.Tid_entity;}
	public int Entity_id() {return entity_id;} private final int entity_id;
	public byte[] Entity_id_bry() {return entity_id_bry;} private final byte[] entity_id_bry;
	public byte Entity_tid() {return entity_tid;} private final byte entity_tid;
	public boolean Entity_tid_is_qid() {return entity_tid == Wdata_dict_value_entity_tid.Tid_item;}
	public String Entity_tid_str() {return Wdata_dict_value_entity_tid.Xto_str(entity_tid);}
	public byte[] Entity_tid_bry() {return Wdata_dict_value_entity_tid.Xto_bry(entity_tid);}
	public byte[] Page_ttl_db() {
		return entity_tid == Wdata_dict_value_entity_tid.Tid_item
			? Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_qid_bry_db, entity_id_bry)
			: Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_pid_bry, entity_id_bry)
			;
	}
	public byte[] Page_ttl_gui() {
		return entity_tid == Wdata_dict_value_entity_tid.Tid_item
			? Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_qid_bry_gui, entity_id_bry)
			: Bry_.Add(Wdata_wiki_mgr.Ttl_prefix_pid_bry, entity_id_bry)
			;
	}
	@Override public void Welcome(Wdata_claim_visitor visitor) {visitor.Visit_entity(this);}
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", Wdata_dict_snak_tid.Xto_str(this.Snak_tid()), Wdata_dict_val_tid.Xto_str(this.Val_tid()), this.Entity_tid_str(), Int_.To_str(entity_id));
	}
}
