package mathieu.org.fileexplorer.fragments.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import mathieu.org.fileexplorer.R;
import static java.lang.String.*;

public class FileExplorerAdapter extends ArrayAdapter<File> {

    static boolean isMenuVisible;
    final int HEIGHT = 200;
    final int WIDTH = 200;

    class ViewHolder {
        TextView t1, t2;
        ImageView img1;
    }

    public FileExplorerAdapter(@NonNull Context context, int resource, @NonNull List<File> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null) {

            convertView = View.inflate(parent.getContext(),R.layout.adapter_content_fragment_file_explorer,null);

            vh = new ViewHolder();
            vh.t1 = convertView.findViewById(R.id.textView1);
            vh.t2 = convertView.findViewById(R.id.textView2);
            vh.img1 = convertView.findViewById(R.id.imageView);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        File f = getItem(position);
        DecimalFormat df = new DecimalFormat("##.##");

        vh.img1.getLayoutParams().width = WIDTH;
        vh.img1.getLayoutParams().height = HEIGHT;
        vh.img1.requestLayout();

        vh.t2.setText(valueOf(df.format((double)f.getFreeSpace()/1_000_000_000)) + " Go / " + valueOf(f.getTotalSpace()/1_000_000_000 + " Go"));

        if(f.getAbsolutePath().equals("/sdcard")) {
            // menu part
            vh.img1.setImageResource(R.mipmap.smartphone);
            vh.t1.setText("Internal storage");
        }
        else if(f.getAbsolutePath().equals("/storage/4079-1EEC")) {
            // menu part
            vh.img1.setImageResource(R.mipmap.sdcard);
            vh.t1.setText("External storage (SD card)");
        }
        else {
            // file explorer part
            vh.t1.setText(f.getName());

            if(f.isDirectory())
                vh.t2.setText(f.listFiles().length + " items");
            else {
                double size = f.length();

                vh.t2.setText(df.format(size) + " B");

                if(size >= 1_000_000_000)
                    vh.t2.setText(df.format(size/1_000_000_000) + " GB");
                else if(size >= 1_000_000)
                    vh.t2.setText(df.format(size/1_000_000) + " MB");
                else if(size >= 1_000)
                    vh.t2.setText(df.format(size/1_000) + " KB");
            }

        }

        return convertView;
    }

}
