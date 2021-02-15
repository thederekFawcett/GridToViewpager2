package m.example.mygridtopageriv;

import static m.example.mygridtopageriv.TextData.TEXT_POSITIONS;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TVPagerAdapter extends FragmentStateAdapter {

  public TVPagerAdapter(FragmentActivity fa) {
    // Note: Initialize with the child fragment manager.
    super(fa);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    return TVFragment.newInstance(TEXT_POSITIONS[position]);
  }

  @Override
  public int getItemCount() {
    return TEXT_POSITIONS.length;
  }
}