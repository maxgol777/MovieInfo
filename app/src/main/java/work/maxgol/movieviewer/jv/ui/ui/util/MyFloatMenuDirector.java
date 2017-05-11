package work.maxgol.movieviewer.jv.ui.ui.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.common.constants.ApiConstants;
import work.maxgol.movieviewer.jv.domain.model.Video;

import static android.R.drawable.ic_media_play;

/**
 * Created by maxgol on 24.04.2017.
 */

public class MyFloatMenuDirector {
    FloatingActionMenu fabMenu;
    Activity activity;

    public MyFloatMenuDirector(Activity activity, FloatingActionMenu fabMenu) {
        this.fabMenu = fabMenu;
        this.activity = activity;
    }


    public void setFloatButtonState(List<Video> list) {
        if (list.size() != 0) {
            fabMenu.post(() -> {
                fabMenu.removeAllMenuButtons();
                for (Video v : list)
                    createButton(v);
                fabMenu.refreshDrawableState();
            });

            fabMenu.setVisibility(View.VISIBLE);
        }
    }

    void createButton(Video video) {
        FloatingActionButton fab = new FloatingActionButton(activity);
        fab.setLabelText(video.getName() + " (" + video.getType() + ")");
        fab.setColorNormal(ContextCompat.getColor(activity, R.color.colorAccent));
        fab.setColorPressed(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
        fab.setColorRipple(ContextCompat.getColor(activity, R.color.colorPrimary));
        fab.setImageDrawable(ContextCompat.getDrawable(activity, ic_media_play));
        fab.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ApiConstants.Youtube.videoPath + video.getKey()));
            if (intent.resolveActivity(activity.getPackageManager()) != null)
                activity.startActivity(intent);
            else
                Toast.makeText(activity, R.string.mes1, Toast.LENGTH_SHORT).show();
            fabMenu.close(false);
        });

        fabMenu.addMenuButton(fab);

    }
}
