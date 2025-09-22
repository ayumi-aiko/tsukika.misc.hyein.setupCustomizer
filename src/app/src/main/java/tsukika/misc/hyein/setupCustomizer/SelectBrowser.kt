package tsukika.misc.hyein.setupCustomizer
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.core.content.edit

class SelectBrowser : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_select_browser, container, false);
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        val selectedId = view.findViewById<RadioGroup>(R.id.browser_group).checkedRadioButtonId;
        val browserValue = when(selectedId) {
            R.id.chrome_radio -> BROWSER_CHROME;
            R.id.firefox_radio -> BROWSER_FIREFOX;
            R.id.vivaldi_radio -> BROWSER_VIVALDI;
            R.id.ugc_radio -> BROWSER_UGC;
            else -> -1;
        }
        val prefs = requireContext().getSharedPreferences(rhap.prefsName, Context.MODE_PRIVATE);
        prefs.edit {
            putInt(rhap.keyBrowser, browserValue);
            apply();
        };
        view.findViewById<ImageButton>(R.id.nextButton).setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                SelectDNSProvider()).addToBackStack(null).commit()
        }
    }
    companion object {
        val rhap = Rhapsody();
        const val BROWSER_CHROME = 1;
        const val BROWSER_FIREFOX = 2;
        const val BROWSER_VIVALDI = 3;
        const val BROWSER_UGC = 4;
    }
}