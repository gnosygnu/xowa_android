package gplx.core.net.emails;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Gfo_email_mgr__drd implements Gfo_email_mgr {
    private final Context context;
    public Gfo_email_mgr__drd(Context context) {this.context = context;}
    public void Send(String to, String subject, String body) {
        Intent send_intent = new Intent(Intent.ACTION_SEND);
        send_intent.setType("message/rfc822");
        send_intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{to});
        send_intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        send_intent.putExtra(Intent.EXTRA_TEXT   , body);
        try {
            Intent choose_intent = Intent.createChooser(send_intent, "Send mail...");
            choose_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            context.getApplicationContext().startActivity(choose_intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
