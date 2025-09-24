package tsukika.misc.hyein.setupCustomizer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.core.content.edit

class SelectDNSProvider : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        val selectedId = view.findViewById<RadioGroup>(R.id.dns_group)?.checkedRadioButtonId;
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
        view.findViewById<ImageButton>(R.id.finalizeButton)?.setOnClickListener {
            val intent = Intent(requireContext(), FinalizeActivity::class.java)
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
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