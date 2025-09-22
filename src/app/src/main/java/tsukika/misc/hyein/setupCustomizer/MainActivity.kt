package tsukika.misc.hyein.setupCustomizer
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {
    val rhap = Rhapsody();
    override fun onCreate(savedInstanceState: Bundle?) {
        // switch to the setup if the user just closed the application.
        if(this.getSharedPreferences(rhap.prefsName, MODE_PRIVATE).getString(rhap.lastActivity, "start") == "start") {
            val intent = Intent(this, FinalizeActivity::class.java);
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK;
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowCompat.setDecorFitsSystemWindows(window, false);
        val controller = WindowInsetsControllerCompat(window, window.decorView);
        controller.hide(WindowInsetsCompat.Type.systemBars());
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE;
    }
}