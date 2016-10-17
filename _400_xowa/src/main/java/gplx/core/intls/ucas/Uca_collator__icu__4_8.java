package gplx.core.intls.ucas; import gplx.*; import gplx.core.*; import gplx.core.intls.*;
//#{Uca_collator__icu__4_8
import java.util.Locale;
import com.ibm.icu.text.CollationKey;
import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;
class Uca_collator__icu__4_8 implements Uca_collator {
	private Collator collator;
	public void	Init(String locale, boolean numeric_ordering) {
		try {
			this.collator = Collator.getInstance(Locale.forLanguageTag(locale));
			if (numeric_ordering) {
				RuleBasedCollator rbc = (RuleBasedCollator)collator;
				rbc.setNumericCollation(true);
			}
		} catch (Exception e) {throw Err_.new_wo_type("collator init failed", "err", Err_.Message_lang(e));}		
	}
	public byte[]	Get_sortkey(String s) {
		CollationKey key = collator.getCollationKey(s);		
		byte[] src = key.toByteArray();
		int src_len = src.length;
		byte[] rv = src;
		
		// remove last byte if it is 0 (which it often is) 
		if (src_len > 0 && src[src_len - 1] == 0) {
			int rv_len = src_len - 1;
			rv = new byte[rv_len];
			for (int i = 0; i < rv_len; ++i)
				rv[i] = src[i];
		} 
		return rv;
	}
}
//#}
