package tsukika.misc.hyein.setupCustomizer
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.core.content.edit
import android.graphics.Rect
import android.os.Build
import androidx.core.content.ContextCompat

class SelectDNSProvider : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        val selectedId = view?.findViewById<RadioGroup>(R.id.dns_group)?.checkedRadioButtonId;
        val dnsValue = when(selectedId) {
            R.id.google_radio -> DNS_GOOGLE;
            R.id.cloudflare_radio -> DNS_CLOUDFLARE;
            R.id.opendns_radio -> DNS_OPENDNS;
            R.id.quad9_radio -> DNS_QUAD;
            else -> -1;
        }
        val prefs = requireContext().getSharedPreferences(rhap.prefsName, Context.MODE_PRIVATE);
        prefs.edit {
            putInt(rhap.keyDNS, dnsValue);
            apply();
        };
        val windowManager = ContextCompat.getSystemService(requireContext(), WindowManager::class.java)!!
        val bounds = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            windowManager.currentWindowMetrics.bounds
        }
        else {
            val size = Point()
            windowManager.defaultDisplay.getRealSize(size)
            Rect(0, 0, size.x, size.y)
        }
        val width: Int = bounds.width();
        val height: Int = bounds.height();
        view?.findViewById<ImageButton>(R.id.finalizeButton)?.setOnClickListener {
            startActivity(Intent(requireContext(), FinalizeActivity::class.java));
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_select_dns_provider, container, false);
    }
    companion object {
        val rhap = Rhapsody();
        const val DNS_GOOGLE = 1;
        const val DNS_CLOUDFLARE = 2;
        const val DNS_OPENDNS = 3;
        const val DNS_QUAD = 4;
    }
}