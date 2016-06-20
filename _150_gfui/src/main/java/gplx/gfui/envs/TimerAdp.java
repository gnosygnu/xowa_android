package gplx.gfui.envs; import gplx.*; import gplx.gfui.*;
//#{import
import javax.swing.Timer;
import java.awt.event.ActionListener;
//#}
import gplx.core.envs.*;
public class TimerAdp implements Rls_able {
	public TimerAdp Interval_(int interval) {
		//#{Interval_
		underTimer.setInitialDelay(interval);
		underTimer.setDelay(interval);
		//#}
		return this;
	}
	public TimerAdp Enabled_on() {return Enabled_(true);} public TimerAdp Enabled_off() {return Enabled_(false);}
	public TimerAdp Enabled_(boolean val) {
		if (!Env_.Mode_testing()) {
			//#{Enabled_
			if (val) underTimer.start();
			else underTimer.stop();
			//#}
		}
		return this;
	}
	public void Rls() {underTimer.stop();}	//#<>.Dispose~.stop

	Timer underTimer;
	//#{TimerAdp
	public static TimerAdp new_(Gfo_invk invk, String msgKey, int interval, boolean enabled) {
		TimerAdp rv = new TimerAdp();
		rv.underTimer = new Timer(interval, new TimerActionListener(invk, msgKey));
		rv.Interval_(interval).Enabled_(enabled);
		return rv;
	}
	//#}
}
//#{TimerActionListener
class TimerActionListener implements ActionListener {
	public void actionPerformed(java.awt.event.ActionEvent arg0) {
		Gfo_invk_.Invk_by_key(invk, key);
	}
	Gfo_invk invk; String key;
	public TimerActionListener(Gfo_invk invk, String key) {
		this.invk = invk; this.key = key;
	}
}
//#}
