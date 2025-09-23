package tsukika.misc.hyein.setupCustomizer
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class FinalizeActivity : AppCompatActivity() {
    external fun installThisBrowser(browser: Int): Boolean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);
        val sharedPref: SharedPreferences = getSharedPreferences(rhap.prefsName, MODE_PRIVATE);
        val textView = findViewById<TextView>(R.id.textView);
        val imageView = findViewById<ImageView>(R.id.imageView);
        val selectedBrowser: Int = sharedPref.getInt(rhap.keyBrowser, -1);
        val selectedDNS: Int = sharedPref.getInt(rhap.keyDNS, -1);
        val resolver = this.contentResolver
        findViewById<ProgressBar>(R.id.progressBar).isIndeterminate = true;
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
            Settings.Global.putString(resolver, "private_dns_specifier", dnsProviders[selectedDNS]);
        }
        /////////////// DNS SETUP! ///////////////////////


        // TODO: Switch to the finalize fragment to close the app and set the sharedPref value (lastActivity) to setupFinalized to prevent idiots from opening the app again.

        //        supportFragmentManager.beginTransaction()
        //            .replace(R.id.fragmentContainer, fragment) // or .add()
        //            .commit()

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