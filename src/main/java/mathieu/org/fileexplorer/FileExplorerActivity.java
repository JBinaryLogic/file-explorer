package mathieu.org.fileexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import mathieu.org.fileexplorer.fragments.FileExplorerFragment;

public class FileExplorerActivity extends FragmentActivity {

    ViewPager mPager;
    TabLayout mTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);

        mPager = findViewById(R.id.viewPager);
        mTab = findViewById(R.id.tabLayout);

        mPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager,true);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        FileExplorerFragment fragment = (FileExplorerFragment) getSupportFragmentManager().getFragments().get(1);
        fragment.returnToParent(fragment.mFile);

        return true;
    }
}
