package tsukika.misc.hyein.setupCustomizer
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class FinalizeFragment : Fragment() {
    external fun installThisBrowser(browser: Int): Boolean;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finalize, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(rhap.prefsName, MODE_PRIVATE);
        val textView = view.findViewById<TextView>(R.id.textView);
        val imageView = view.findViewById<ImageView>(R.id.imageView);
        val selectedBrowser: Int = sharedPref.getInt(rhap.keyBrowser, -1);
        val selectedDNS: Int = sharedPref.getInt(rhap.keyDNS, -1);
        val resolver = context?.contentResolver
        viewLifecycleOwner.lifecycleScope.launch {
            view.findViewById<ProgressBar>(R.id.progressBar).isIndeterminate = true;
            sharedPref.edit { putString(rhap.lastActivity, "running") };
            // shows skipping browser installation!
            if(selectedBrowser == -1 || selectedBrowser == 0 || selectedBrowser >= 5) textView.text = getString(R.string.skippingbrowser);
            // installs the browser app.
            else {
                imageView.setImageResource(browserDrawables[selectedBrowser])
                installThisBrowser(selectedBrowser)
            }
            /////////////// DNS SETUP! ///////////////////////
            if(selectedDNS == -1 || selectedDNS == 0 || selectedDNS >= 5) textView.text = getString(R.string.skippingdns);
            else {
                imageView.setImageResource(R.drawable.misc);
                Settings.Global.putString(resolver, "private_dns_mode", "hostname");
                Settings.Global.putString(
                    resolver,
                    "private_dns_specifier",
                    dnsProviders[selectedDNS]
                );
            }
            /////////////// DNS SETUP! ///////////////////////
        }
        view.post {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, GreetingFragment())
                .addToBackStack(null)
                .commit()
        }
    }
    companion object {
        val rhap = Rhapsody();
        val browserDrawables = arrayOf(
            0,
            R.drawable.chrome,
            R.drawable.firefox_focus,
            R.drawable.vivaldi,
            R.drawable.ungoogled_chrome
        );
        val dnsProviders = arrayOf(
            "placeholder",
            "dns.google",
            "one.one.one.one",
            "dns.umbrella.com",
            "dns.quad9.net"
        );
    }
}