package m.example.mygridtopageriv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class TVPagerFragment extends Fragment {

  ViewPager2 viewPager;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    viewPager = (ViewPager2) inflater.inflate(R.layout.fragment_pager, container, false);
    viewPager.setAdapter(new TVPagerAdapter(this.getActivity()));
    // Set the current position and add a listener that will update the selection coordinator when
    // paging the images.
    viewPager.setCurrentItem(MainActivity.currentPosition, false);

    viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        MainActivity.currentPosition = position;
      }
    });

    /*prepareSharedElementTransition();*/

    // Avoid a postponeEnterTransition on orientation change, and postpone only of first creation.
    /*if (savedInstanceState == null) {
      postponeEnterTransition();
    }*/

    return viewPager;
  }

  /**
   * Prepares the shared element transition from and back to the grid fragment.
   */
/*  private void prepareSharedElementTransition() {
    Transition transition =
        TransitionInflater.from(getContext())
            .inflateTransition(R.transition.image_shared_element_transition);
    setSharedElementEnterTransition(transition);

    // A similar mapping is set at the GridFragment with a setExitSharedElementCallback.
    setEnterSharedElementCallback(
        new SharedElementCallback() {
          @Override
          public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            // Locate the image view at the primary fragment (the TVFragment that is currently
            // visible). To locate the fragment, call instantiateItem with the selection position.
            // At this stage, the method will simply return the fragment at the position and will
            // not create a new one.
            *//*Fragment currentFragment = (Fragment) viewPager.getAdapter()
                .instantiateItem(viewPager, MainActivity.currentPosition);*//*

            Fragment currentFragment = findCurrentFragment(getChildFragmentManager());
            View view = currentFragment.getView();
            if (view == null) {
              return;
            }

            // Map the first shared element name to the child ImageView.
            sharedElements.put(names.get(0), view.findViewById(R.id.image));
          }
        });
  }

  private Fragment findCurrentFragment(FragmentManager fragmentManager) {
    Log.d("TAG", "findCurrentFragment: " + viewPager.getCurrentItem());
    return fragmentManager.findFragmentByTag("f" + viewPager.getCurrentItem());
  }*/
}
