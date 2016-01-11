package gplx;
import gplx.core.strings.*;
public class TimeSpanAdp implements CompareAble {
	public long Fracs() {return fracs;} long fracs; public int FracsAsInt() {return (int)fracs;}
	public Decimal_adp TotalSecs() {
		return Decimal_adp_.divide_(fracs, TimeSpanAdp_.Divisors[TimeSpanAdp_.Idx_Sec]);
	}
	public Decimal_adp Total_days() {
		return Decimal_adp_.divide_(fracs, TimeSpanAdp_.Divisors[TimeSpanAdp_.Idx_Hour]  * 24);
	}
	public int[] Units() {return TimeSpanAdp_.Split_long(fracs, TimeSpanAdp_.Divisors);}
	public int Units_fracs() {
		int[] ary = TimeSpanAdp_.Split_long(fracs, TimeSpanAdp_.Divisors);
		return ary[TimeSpanAdp_.Idx_Frac];
	}
	public TimeSpanAdp Add(TimeSpanAdp val)			{return new TimeSpanAdp(fracs + val.fracs);}
	public TimeSpanAdp Add_fracs(long val)			{return new TimeSpanAdp(fracs + val);}
	public TimeSpanAdp Add_unit(int idx, int val) {
		int[] units = TimeSpanAdp_.Split_long(fracs, TimeSpanAdp_.Divisors);
		units[idx] += val;
		int sign = fracs >= 0 ? 1 : -1;
		long rv = sign * TimeSpanAdp_.Merge_long(units, TimeSpanAdp_.Divisors);
		return TimeSpanAdp_.fracs_(rv);
	}
	public TimeSpanAdp Subtract(TimeSpanAdp val)	{return new TimeSpanAdp(fracs - val.fracs);}

	public int compareTo(Object obj)				{TimeSpanAdp comp = TimeSpanAdp_.cast(obj); return CompareAble_.Compare_obj(fracs, comp.fracs);}
	public boolean Eq(Object o) {
		TimeSpanAdp comp = TimeSpanAdp_.cast(o); if (comp == null) return false;
		return fracs == comp.fracs;
	}
	@Override public String toString()				{return To_str(TimeSpanAdp_.Fmt_Default);}
	@Override public boolean equals(Object obj)			{TimeSpanAdp comp = TimeSpanAdp_.cast(obj); return Object_.Eq(fracs, comp.fracs);}
	@Override public int hashCode() {return super.hashCode();}

	public String To_str()	{return TimeSpanAdp_.To_str(fracs, TimeSpanAdp_.Fmt_Default);}
	public String To_str(String format)	{
		return TimeSpanAdp_.To_str(fracs, format);
	}
	public String XtoStrUiAbbrv()	{
		if (fracs == 0) return "0" + UnitAbbrv(0);
		int[] units = Units();
		boolean started = false;
		String_bldr sb = String_bldr_.new_();
		for (int i = units.length - 1; i > -1; i--) {
			int unit = units[i];
			if (!started) {
				if (unit == 0)
					continue;
				else
					started = true;
			}
			if (sb.Count() != 0) sb.Add(" ");
			sb.Add_obj(unit).Add(UnitAbbrv(i));
		}
		return sb.To_str();
	}
	String UnitAbbrv(int i) {
		switch (i) {
			case 0: return "f";
			case 1: return "s";
			case 2: return "m";
			case 3: return "h";
			default: return "unknown:<" + Int_.To_str(i) + ">";
		}
	}
	@gplx.Internal protected TimeSpanAdp(long fracs) {this.fracs = fracs;}
}
