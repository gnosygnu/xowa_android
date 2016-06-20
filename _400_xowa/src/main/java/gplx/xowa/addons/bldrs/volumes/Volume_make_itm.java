package gplx.xowa.addons.bldrs.volumes; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*;
import gplx.core.brys.*;
class Volume_make_itm implements Bry_bfr_able {
	public int Uid = 0;
	public int Prep_id = 0;
	public int Item_type = 0;				// 0=page;1=thm
	public int Item_id = 0;					// either page_id or fsdb_id
	public String Item_name = "";			// friendly-name
	public byte[] Item_ttl = Bry_.Empty;	// actual name
	public long Item_size = 0;				// size of page / file
	public void To_bfr(Bry_bfr bfr) {
	}
}
/*
1|p|Earth|123|100
1|f|Earth.png|124|200
1|f|Moon.png|125|300
4|p|Sun|123|100
*/
