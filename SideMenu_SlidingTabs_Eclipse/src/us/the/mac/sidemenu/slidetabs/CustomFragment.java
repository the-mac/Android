package us.the.mac.sidemenu.slidetabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Fragment used in ViewPager. Just one color on entire page

public class CustomFragment extends Fragment {

    public static final String KEY_NUMBER = "value_of_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.custom_fragment, container, false);

        View background = rootView.findViewById(R.id.view);

        Bundle args = getArguments();
        int number = args.getInt(KEY_NUMBER);

        switch (number){
            case 0: background.setBackgroundColor(getResources().getColor(R.color.holo_blue)); break;
            case 1: background.setBackgroundColor(getResources().getColor(R.color.green)); break;
            case 2: background.setBackgroundColor(getResources().getColor(R.color.red)); break;
            case 3: background.setBackgroundColor(getResources().getColor(R.color.yellow)); break;
            case 4: background.setBackgroundColor(getResources().getColor(R.color.gray)); break;
            default: background.setBackgroundColor(getResources().getColor(R.color.holo_blue)); break;
        }
        return rootView;
    }
}
