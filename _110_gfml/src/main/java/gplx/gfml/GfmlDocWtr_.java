package gplx.gfml; import gplx.*;
import gplx.core.strings.*;
public class GfmlDocWtr_ {
	public String To_str_and_clear()			{return sb.To_str_and_clear();}
	public void BuildAttrib(GfmlAtr atr)	{Build(atr);}
	public void BuildNode(GfmlNde nde)		{Build(nde);}
	void Build(GfmlItm owner) {
		for (int i = 0; i < owner.SubObjs_Count(); i++) {
			GfmlObj subObj = owner.SubObjs_GetAt(i);
			GfmlItm subItm = GfmlItm_.as_(subObj);
			if (subItm == null)
				sb.Add(GfmlTkn_.as_(subObj).Raw());
			else
				Build(subItm);
		}
	}
	String_bldr sb = String_bldr_.new_();
        public static String xtoStr_(GfmlNde nde) {
		GfmlDocWtr_ wtr = new GfmlDocWtr_();
		wtr.BuildNode(nde);
		return wtr.To_str_and_clear();
	}
}
