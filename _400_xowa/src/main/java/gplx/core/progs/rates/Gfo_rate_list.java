package gplx.core.progs.rates; import gplx.*; import gplx.core.*; import gplx.core.progs.*;
import gplx.core.lists.rings.*;
public class Gfo_rate_list {
	private final    Ring__long ring;
	public Gfo_rate_list(int size) {
		this.ring = new Ring__long(size * 2);	// *2 to store both data and time
	}
	public void Clear() {ring.Clear(); cur_rate = cur_delta = 0;}
	public double Cur_rate() {return cur_rate;} private double cur_rate = 0;
	public double Cur_delta() {return cur_delta;} private double cur_delta;
	public double Add(long data, long time) {
		ring.Add(data);
		ring.Add(time);		
		double new_rate = Cur_calc(data, time);
		cur_delta = cur_rate == 0 ? new_rate : Math_.Abs_double((new_rate - cur_rate) / cur_rate);
		cur_rate = new_rate;
		return cur_rate;
	}
	private double Cur_calc(long cur_data, long cur_time) {
		int len = ring.Len();
		long data_all = 0;
		long time_all = 0;
		for (int i = 0; i < len; i += 2) {
			data_all += ring.Get_at(i);
			time_all += ring.Get_at(i + 1);
		}
		return data_all / (time_all == 0 ? .001 : time_all);
	}
}
