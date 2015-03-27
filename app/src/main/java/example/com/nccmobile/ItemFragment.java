package example.com.nccmobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mView;
    public static Item selectedItem;
    public Cart mCart = null;

    ImageButton favBtn;
    Button cartBtn;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemFragment newInstance(String param1, String param2) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private void updateDetails() {
        bindMemberTextView(R.id.name, selectedItem.getName());
        bindMemberTextView(R.id.price, String.valueOf(selectedItem.getPrice()));
        bindMemberTextView(R.id.quantityInStock, String.valueOf(selectedItem.getQuantityInStock()));
    }


    private void initDetailedView() {
        favBtn = (ImageButton) mView.findViewById(R.id.favBtn);
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( selectedItem.isFav == 0 ) {
                    selectedItem.isFav = 1;
                    Log.i(selectedItem.getName(), "Is now favorited");
                } else {
                    selectedItem.isFav = 0;
                    Log.i(selectedItem.getName(), "Is now not favorited");
                }

                selectedItem.save();
                updateView();
            }
        });

        cartBtn = (Button) mView.findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItem.quantityInStock > 0) {
                    // check if cart row for item and user
                    if (mCart == null) {
                        mCart = Cart.findItemId(selectedItem.getId());
                    }

                    if (mCart != null) {
                        mCart.quantityInCart += 1;
                        selectedItem.quantityInStock -= 1;

                        mCart.save();
                        selectedItem.save();
                    } else {
                        mCart = new Cart(selectedItem);
                        selectedItem.quantityInStock -= 1;

                        mCart.save();
                        selectedItem.save();
                    }
                    // toast( "Added to cart" );
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Added to cart",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // toast that is none in stock
                    Toast.makeText(getActivity().getApplicationContext(),
                            "That item is not in stock",
                            Toast.LENGTH_SHORT).show();

                }

                updateView();
            }
        });

        updateView();
    }

    private void updateView() {
        updateDetails();
        updateFavIcon();
    }

    private void updateFavIcon() {
        if (selectedItem.isFav == 1) {
            favBtn.setImageResource(R.drawable.ic_action_important);
        } else {
            favBtn.setImageResource(R.drawable.ic_action_not_important);
        }
    }


    private void bindMemberTextView(int layout_id, String value) {
        TextView textView = (TextView) mView.findViewById(layout_id);
        textView.setText(value);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_item_detailed, container, false);

        Log.i("Item Detailed", selectedItem.toString());
        initDetailedView();

        return mView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
