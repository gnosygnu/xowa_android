package gplx.core;

import android.content.Context;
import android.os.PowerManager;

import gplx.Gfo_log_;
import gplx.Ordered_hash;
import gplx.Ordered_hash_;
import gplx.Tfds;
import gplx.xowa.drds.powers.Xod_power_mgr;

public class Xod_power_mgr__drd implements Xod_power_mgr {
    private final Context context;
    private final Ordered_hash hash = Ordered_hash_.New();
    public Xod_power_mgr__drd(Context context) {this.context = context;}
    @Override public void Wake_lock__get(String name) {
        if (hash.Has(name)) {
            Gfo_log_.Instance.Warn("power_mgr.get:lock exists", "name", name);
            return;
        }
        PowerManager powerManager = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock lock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, name);
        try {
            lock.acquire();
        } catch (Exception e) {
            Tfds.Dbg(e);
        }
        hash.Add(name, lock);
    }
    @Override public void Wake_lock__rls(String name) {
        if (!hash.Has(name)) {
            Gfo_log_.Instance.Warn("power_mgr.rls:lock missing", "name", name);
            return;
        }
        PowerManager.WakeLock lock = (PowerManager.WakeLock)hash.Get_by(name);
        try {
            lock.release();
        }
        catch (Exception e) {
            Tfds.Dbg(e);
        }
    }
}
