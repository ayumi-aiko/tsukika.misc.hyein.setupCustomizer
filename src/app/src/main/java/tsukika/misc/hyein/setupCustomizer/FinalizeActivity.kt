package tsukika.misc.hyein.setupCustomizer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FinalizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, FinalizeFragment())
                .commit()
        }
    }
}