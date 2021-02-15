package m.example.mygridtopageriv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TVFragment extends Fragment {

  static final String[] textArray = {
      "Zero",
      "One",
      "Two",
      "Three",
      "Four",
      "Five",
      "Six",
      "Seven",
      "Eight",
      "Nine",
      "Ten",
      "Eleven",
      "Twelve",
      "Thirteen"
  };
  private static final String KEY_IMAGE_RES = "IMAGE_FRAGMENT";

  public static TVFragment newInstance(@DrawableRes int drawableRes) {
    TVFragment fragment = new TVFragment();
    Bundle argument = new Bundle();
    argument.putInt(KEY_IMAGE_RES, drawableRes);
    fragment.setArguments(argument);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_tv, container, false);

    Bundle arguments = getArguments();
    @DrawableRes int imageRes = arguments.getInt(KEY_IMAGE_RES);

    // Just like we do when binding views at the grid, we set the transition name to be the string
    // value of the image res.
    view.findViewById(R.id.tv_only_file).setTransitionName(String.valueOf(imageRes));

    TextView tvInTVOnlyFile = view.findViewById(R.id.tv_only_file);
    tvInTVOnlyFile.setText(textArray[imageRes]);

    /*// Load the image with Glide to prevent OOM error when the image drawables are very large.
    Glide.with(this)
        .load(imageRes)
        .listener(new RequestListener<Drawable>() {
          @Override
          public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable>
              target, boolean isFirstResource) {
            // The postponeEnterTransition is called on the parent TVPagerFragment, so the
            // startPostponedEnterTransition() should also be called on it to get the transition
            // going in case of a failure.
            getParentFragment().startPostponedEnterTransition();
            return false;
          }

          @Override
          public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
              target, DataSource dataSource, boolean isFirstResource) {
            // The postponeEnterTransition is called on the parent TVPagerFragment, so the
            // startPostponedEnterTransition() should also be called on it to get the transition
            // going when the image is ready.
            getParentFragment().startPostponedEnterTransition();
            return false;
          }
        })
        .into((ImageView) view.findViewById(R.id.image));*/
    return view;
  }
}
