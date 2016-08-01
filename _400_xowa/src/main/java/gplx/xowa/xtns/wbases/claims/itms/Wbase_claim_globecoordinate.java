package gplx.xowa.xtns.wbases.claims.itms; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*; import gplx.xowa.xtns.wbases.*; import gplx.xowa.xtns.wbases.claims.*;
import gplx.xowa.xtns.wbases.claims.enums.*;
public class Wbase_claim_globecoordinate extends Wbase_claim_base {
	public Wbase_claim_globecoordinate(int pid, byte snak_tid, byte[] lat, byte[] lng, byte[] alt, byte[] prc, byte[] glb) {super(pid, snak_tid);
		this.lat = lat; this.lng = lng; this.alt = alt; this.prc = prc; this.glb = glb;
	}
	@Override public byte	Val_tid() {return Wbase_claim_type_.Tid__globecoordinate;}
	public byte[]			Lat() {return lat;} private final    byte[] lat;
	public byte[]			Lng() {return lng;} private final    byte[] lng;
	public byte[]			Alt() {return alt;} private final    byte[] alt;
	public byte[]			Prc() {return prc;} private final    byte[] prc;
	public byte[]			Glb() {return glb;} private final    byte[] glb;
	public byte[]			Glb_ttl() {return glb_ttl;} private byte[] glb_ttl;

	public void Glb_ttl_(byte[] v) {glb_ttl = v;} 
	public Decimal_adp Prc_as_num() {
		if (prc_as_num == null)
			prc_as_num = Bry_.Eq(prc, Object_.Bry__null) ? Decimal_adp_.One : Decimal_adp_.parse(String_.new_a7(prc));
		return prc_as_num;
	}	private Decimal_adp prc_as_num;

	@Override public void Welcome(Wbase_claim_visitor visitor) {visitor.Visit_globecoordinate(this);}
	@Override public String toString() {// TEST:
		return String_.Concat_with_str("|", Wbase_claim_value_type_.To_str_or_fail(this.Snak_tid()), Wbase_claim_type_.To_key_or_unknown(this.Val_tid()), String_.new_u8(lat), String_.new_u8(lng), String_.new_u8(alt), String_.new_u8(prc), String_.new_u8(glb));
	}
}
