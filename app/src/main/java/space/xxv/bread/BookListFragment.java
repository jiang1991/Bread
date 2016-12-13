package space.xxv.bread;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class BookListFragment extends ListFragment {

    private OnBookSelectedListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Ipsum.Lists));
    }


    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.viewer) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnBookSelectedListener) {
            mListener = (OnBookSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBookSelectedListener");
        }
    }


    public interface OnBookSelectedListener {
        // TODO: Update argument type and name
        void onBookSelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.onBookSelected(position);

        getListView().setItemChecked(position, true);
    }
}
