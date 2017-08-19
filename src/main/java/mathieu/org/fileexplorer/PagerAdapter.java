package mathieu.org.fileexplorer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mathieu.org.fileexplorer.fragments.FileExplorerFragment;
import mathieu.org.fileexplorer.fragments.ShortcutFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGES = 2;
    Fragment[] tab = new Fragment[]{new ShortcutFragment(),new FileExplorerFragment()};
    String[] name = new String[]{"Shortcut","File explorer"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return tab[position];
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }
}
