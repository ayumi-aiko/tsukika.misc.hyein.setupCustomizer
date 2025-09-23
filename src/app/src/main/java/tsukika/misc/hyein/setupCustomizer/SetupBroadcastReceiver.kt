package tsukika.misc.hyein.setupCustomizer
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SetupBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Intent.ACTION_USER_INITIALIZE) context.startActivity(
            Intent(
                context,
                MainActivity::class.java
            ).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) });
    }
}