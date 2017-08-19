package mathieu.org.fileexplorer.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import mathieu.org.fileexplorer.R;
import mathieu.org.fileexplorer.fragments.adapters.FileExplorerAdapter;

public class FileExplorerFragment extends Fragment {

    View root;
    public File mFile;
    ArrayList<File> files = new ArrayList<>(),  availableHardwareStorages = new ArrayList<>();

    ListView mListView;

    FileExplorerAdapter adapter;

    boolean isMenuVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = View.inflate(getContext(), R.layout.fragment_file_explorer,null);
        mListView = root.findViewById(R.id.listView_file_explorer);

        availableHardwareStorages = getAvailableHardwareStorages();
        loadMenu(availableHardwareStorages);
        listener();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return root;
    }

    private void loadMenu(ArrayList<File> storages) {
        adapter = new FileExplorerAdapter(getContext(),R.layout.adapter_content_fragment_file_explorer,storages);
        mListView.setAdapter(adapter);
        isMenuVisible = true;
    }

    private void loadFileExplorer(ArrayList<File> files) {

        adapter = new FileExplorerAdapter(getContext(),R.layout.adapter_content_fragment_file_explorer,files);
        mListView.setAdapter(adapter);
        isMenuVisible = false;
    }

    private ArrayList<File> getAvailableHardwareStorages() {

        ArrayList<File> al = new ArrayList<>();

        al.add(new File("/sdcard"));

        if(isExternalStorageWritable())
            al.add(new File("/storage/4079-1EEC"));

        // return an array of available storage
        return al;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    private ArrayList<File> updateList(File mFile) {
        ArrayList<File> al = new ArrayList<>();

        if(mFile.listFiles() != null)
            al.addAll(Arrays.asList(mFile.listFiles()));

        return al;
    }

    private void listener() {

        mListView.setOnItemClickListener(( adapterView, view,  i,  l) -> {

            if(isMenuVisible) {
                mFile = availableHardwareStorages.get(i);
                files = updateList(mFile);
                loadFileExplorer(files);
            }
            else {
                mFile = files.get(i);
                files = updateList(mFile);
                loadFileExplorer(files);
            }

        });
    }

    public void returnToParent(File f) {

        if(!f.getParentFile().toString().equals("/storage") && !f.getParentFile().toString().equals("/")) {
            mFile = f.getParentFile();
            files = updateList(mFile);
            loadFileExplorer(new ArrayList<>(files));
        }
        else {
            loadMenu(new ArrayList<>(availableHardwareStorages));
        }

    }

}
