package tsukika.misc.hyein.setupCustomizer
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

class SetupBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == Intent.ACTION_USER_INITIALIZE) {
            val launchIntent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(launchIntent);
        }
    }
}