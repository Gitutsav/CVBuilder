package com.utsavgupta.cvbuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.utsavgupta.cvbuilder.R;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {
MainActivity myactivity;Fragment fragment11;
    private Context mContext;
    private List<Album> albumList;String holder_name;
    View view;ImageView imgv;
    private SharedPreferences loginData;
    private SharedPreferences.Editor editor;
    private InterstitialAd mInterstitialAd;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
           // count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        myactivity=(MainActivity)mContext;
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        loginData = myactivity.getSharedPreferences("fragment_name", Context.MODE_PRIVATE);
        editor = loginData.edit();
        //imgv=view.findViewById(R.id.img_cv);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Album album = albumList.get(position);
        holder.title.setText(album.getName());
     //  holder_name= String.valueOf(holder.title.getText());
       // holder.count.setText(album.getNumOfSongs() + " songs");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
holder.thumbnail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fragment11 = new ProfileFragment();
        showInterstitial();
        loadFragment(fragment11);
        editor.putString("name_main", album.getName());
        editor.putString("view_status",album.getName());
        editor.putString("viewas","same");
        editor.putString("for_profile_fragment_transaction","store");
        editor.putString("create","yes");
        editor.apply();
    }
});
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
                holder_name= String.valueOf(holder.title.getText());
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
               //    Toast.makeText(mContext, holder_name, Toast.LENGTH_SHORT).show();
                    fragment = new CartFragment();
                    showInterstitial();
                    loadFragment(fragment);
                    editor.putString("name_main", holder_name);
                    editor.putString("view_status",holder_name);
                    editor.putString("viewas","same");
                    editor.apply();


                    return true;
                case R.id.action_play_next:
                    fragment = new ProfileFragment();
                    showInterstitial();
                    loadFragment(fragment);
                    editor.putString("name_main", holder_name);
                    editor.putString("view_status",holder_name);
                    editor.putString("viewas","same");
                    editor.putString("for_profile_fragment_transaction","store");
                    editor.putString("create","yes");
                    editor.apply();
                   /* Drawable d = myactivity.getResources().getDrawable(R.drawable.ic_launcher_background);
                    imgv.setImageDrawable(d);*/
                  //  Toast.makeText(mContext, "View Resume", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
      /*   if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 4) {
           getActivity().getSupportFragmentManager().popBackStackImmediate();
       }*/

        int count = myactivity.getSupportFragmentManager().getBackStackEntryCount();
       // Toast.makeText(myactivity.getApplicationContext(),count+"Added",Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = myactivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
      /*  if (myactivity.getSupportFragmentManager().getBackStackEntryCount() > 4) {
            myactivity.getSupportFragmentManager().popBackStackImmediate();
        }*/
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(myactivity);
        interstitialAd.setAdUnitId(myactivity.getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                ///mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //  mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            // Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        //   mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        // mLevelTextView.setText("Level " + (++mLevel));
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }
}
