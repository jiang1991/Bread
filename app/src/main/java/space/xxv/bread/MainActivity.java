package space.xxv.bread;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity
        implements BookListFragment.OnBookSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (findViewById(R.id.book_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            BookListFragment firstFragment = new BookListFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.book_container, firstFragment).commit();
        }
    }

    public void onBookSelected(int position){
        BookViewFragment bookViewFragment = (BookViewFragment)
                getSupportFragmentManager().findFragmentById(R.id.viewer);

        if (bookViewFragment != null) {
            // book viewer is available
            bookViewFragment.updateBookView(position);
        } else {
            BookViewFragment newFragment = new BookViewFragment();
            Bundle args = new Bundle();

            args.putInt(BookViewFragment.ARG_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.book_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }

}
