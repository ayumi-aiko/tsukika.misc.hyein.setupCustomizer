package tsukika.misc.hyein.setupCustomizer
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
class FinalizeActivity : AppCompatActivity() {
    val rhap = Rhapsody();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);
        val sharedPerf: SharedPreferences = getSharedPreferences(rhap.prefsName, MODE_PRIVATE);
        val editPerf = sharedPerf.edit();
        editPerf.apply {
            putString(rhap.lastActivity, "start");
            apply();
        }
    }
}