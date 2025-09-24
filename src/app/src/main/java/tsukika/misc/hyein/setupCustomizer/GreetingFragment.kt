package tsukika.misc.hyein.setupCustomizer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
class GreetingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_greeting, container, false);
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        val nextButton = view.findViewById<MaterialButton>(R.id.nextButton);
        nextButton.setOnClickListener {
            requireActivity().finishAffinity();
        }
    }
}
