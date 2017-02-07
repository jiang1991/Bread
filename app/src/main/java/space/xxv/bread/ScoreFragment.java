package space.xxv.bread;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.realm.Realm;
import space.xxv.bread.model.Score;


public class ScoreFragment extends Fragment {

    private static final String TAG = NoteActivity.class.getName();
    private static final int TEST_OBJECTS = 100;

    private LinearLayout logsView;
    private ProgressBar progressView;
    private ImportAsyncTask asyncTask;

    //    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        logsView = (LinearLayout) rootView.findViewById(R.id.logs);
        progressView = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressView.setVisibility(View.GONE);

        rootView.findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (asyncTask != null) {
                    asyncTask.cancel(true);
                }

                asyncTask = new ImportAsyncTask();
                asyncTask.execute();
            }
        });

        return rootView;
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(getActivity());
        tv.setText(txt);
        tv.setTextColor(Color.WHITE);
        logsView.addView(tv);
    }

    private class ImportAsyncTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                //                @Override
                public void execute(Realm realm) {
                    realm.delete(Score.class);
                    for (int i =0; i < TEST_OBJECTS; i++) {
                        if (isCancelled()) break;

                        Score score = realm.createObject(Score.class);
                        score.setName("Name" + i);
                        score.setScore(i);
                    }
                }
            });

            Number sum = realm.where(Score.class).sum("score");
            realm.close();
            return sum.intValue();
        }

        @Override
        protected void onPreExecute() {
            logsView.removeAllViews();
            progressView.setVisibility(View.VISIBLE);
            showStatus("Starting import");
        }

        @Override
        protected void onPostExecute(Integer sum) {
            progressView.setVisibility(View.GONE);
            showStatus(TEST_OBJECTS + " objects imported.");
            showStatus("The Total score is : " + sum);
        }
    }
}
