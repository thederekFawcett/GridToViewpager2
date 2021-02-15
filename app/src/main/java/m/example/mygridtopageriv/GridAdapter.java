package m.example.mygridtopageriv;

import static m.example.mygridtopageriv.TextData.TEXT_POSITIONS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import m.example.mygridtopageriv.GridAdapter.ViewHolder;

public class GridAdapter extends RecyclerView.Adapter<ViewHolder> {

  private final ViewHolderListener viewHolderListener;

  /**
   * Constructs a new grid adapter for the given {@link Fragment}.
   */
  public GridAdapter(Fragment fragment) {
    this.viewHolderListener = new ViewHolderListenerImpl(fragment);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.image_card, parent, false);
    return new ViewHolder(view, viewHolderListener);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.onBind();
  }

  @Override
  public int getItemCount() {
    return TEXT_POSITIONS.length;
  }

  /**
   * A listener that is attached to all ViewHolders to handle image loading events and clicks.
   */
  private interface ViewHolderListener {

    /*void onLoadCompleted(ImageView view, int adapterPosition);*/

    void onItemClicked(View view, int adapterPosition);
  }

  /**
   * Default {@link ViewHolderListener} implementation.
   */
  private static class ViewHolderListenerImpl implements ViewHolderListener {

    private final Fragment fragment;
    /*private AtomicBoolean enterTransitionStarted;*/

    ViewHolderListenerImpl(Fragment fragment) {
      this.fragment = fragment;
      /*this.enterTransitionStarted = new AtomicBoolean();*/
    }

/*    @Override
    public void onLoadCompleted(ImageView view, int position) {
      // Call startPostponedEnterTransition only when the 'selected' image loading is completed.
      if (MainActivity.currentPosition != position) {
        return;
      }
      if (enterTransitionStarted.getAndSet(true)) {
        return;
      }
      fragment.startPostponedEnterTransition();
    }*/

    /**
     * Handles a view click by setting the current position to the given {@code position} and
     * starting a {@link  TVPagerFragment} which displays the image at the position.
     *
     * @param view     the clicked {@link ImageView} (the shared element view will be re-mapped at
     *                 the GridFragment's SharedElementCallback)
     * @param position the selected view position
     */
    @Override
    public void onItemClicked(View view, int position) {
      // Update the position.
      MainActivity.currentPosition = position;

      // Exclude the clicked card from the exit transition (e.g. the card will disappear immediately
      // instead of fading out with the rest to prevent an overlapping animation of fade and move).
      /*((TransitionSet) fragment.getExitTransition()).excludeTarget(view, true);*/

      /*ImageView transitioningView = view.findViewById(R.id.card_tv_text);*/
      /*TextView transitioningView = view.findViewById(R.id.card_tv_text);*/
      fragment.getFragmentManager()
          .beginTransaction()
          .setReorderingAllowed(true) // Optimize for shared element transition
          /*.addSharedElement(transitioningView, transitioningView.getTransitionName())*/
          .replace(R.id.fragment_container, new TVPagerFragment(), TVPagerFragment.class
              .getSimpleName())
          .addToBackStack(null)
          .commit();
    }
  }

  /**
   * ViewHolder for the grid's images.
   */
  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ViewHolderListener viewHolderListener;
    private final TextView tvBoxInCardView;

    ViewHolder(View itemView, ViewHolderListener viewHolderListener) {
      super(itemView);
      this.viewHolderListener = viewHolderListener;
      itemView.findViewById(R.id.card_view).setOnClickListener(this);

      tvBoxInCardView = itemView.findViewById(R.id.card_tv_text);
    }

    /**
     * Binds this view holder to the given adapter position.
     * <p>
     * The binding will load the image into the image view, as well as set its transition name for
     * later.
     */
    void onBind() {
      int adapterPosition = getAdapterPosition();
      tvBoxInCardView.setText(TVFragment.textArray[adapterPosition]);

      // Set the string value of the image resource as the unique transition name for the view.
      /*tvBoxInCardView.setTransitionName(String.valueOf(TEXT_POSITIONS[adapterPosition]));*/
    }

/*    void setTextTV(final int adapterPosition) {
      tvBox.setText(TVFragment.textArray[adapterPosition]);

      // Load the image with Glide to prevent OOM error when the image drawables are very large.
      requestManager
          .load(IMAGE_DRAWABLES[adapterPosition])
          .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                Target<Drawable> target, boolean isFirstResource) {
              viewHolderListener.onLoadCompleted(image, adapterPosition);
              return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
                target, DataSource dataSource, boolean isFirstResource) {
              viewHolderListener.onLoadCompleted(image, adapterPosition);
              return false;
            }
          })
          .into(image);
    }*/

    @Override
    public void onClick(View view) {
      // Let the listener start the TVPagerFragment.
      viewHolderListener.onItemClicked(view, getAdapterPosition());
    }
  }
}