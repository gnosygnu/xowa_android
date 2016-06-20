package gplx;
import java.util.*;	//#<>System~java.util
public class RandomAdp_ implements Gfo_invk {//_20110317
	public static RandomAdp new_() {
		Random random = new Random(System.currentTimeMillis()); //#<>DateTime.Now.Millisecond~System.currentTimeMillis()
		return new RandomAdp(random);
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_Next))	return RandomAdp_.new_().Next(m.ReadInt("max"));
		else								return Gfo_invk_.Rv_unhandled;
	}	static final    String Invk_Next = "Next";
        public static final    RandomAdp_ Gfs = new RandomAdp_();
}
