package gplx.xowa.addons.bldrs.utils_rankings.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.utils_rankings.*;
class Statistic_calculator {
	private int count;
	private double old_avg, cur_avg, old_sum, cur_sum;
	public int Count()			{return count;}
	public double Avg()			{return (count > 0) ? cur_avg : 0;}
	public double Variance()	{return (count > 1) ? cur_sum / (count - 1) : 0;}
	public double Stddev()		{return Math_.Sqrt(Variance());}
	public void Clear() {count = 0;}
	public void Push(double x) {
		count++;
		if (count == 1) {
			old_avg = cur_avg = x;
			old_sum = 0.0;
		}
		else {
			cur_avg = old_avg + ((x - old_avg) / count);
			cur_sum = old_sum + ((x - old_avg) * (x - cur_avg));

			old_avg = cur_avg; 
			old_sum = cur_sum;
		}
	}
}
