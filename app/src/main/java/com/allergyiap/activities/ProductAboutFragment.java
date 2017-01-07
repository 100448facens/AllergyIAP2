package com.allergyiap.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allergyiap.R;
import com.allergyiap.entities.CatalogEntity;
import com.allergyiap.entities.DownloadImageTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductAboutFragment extends Fragment {

    static final String TAG = "ProductAboutFrag";

    ProductCatalogMapActivity activity;
    Context context;
    ViewHolder viewHolder;

    public ProductAboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (ProductCatalogMapActivity) getActivity();
        context = getActivity().getApplicationContext();

        //activity.updateLocale();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, ".onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        viewHolder = new ViewHolder(view);

        loadData();
    }

    private void loadData() {

        CatalogEntity product = activity.getProduct();

        viewHolder.productName.setText(product.title);
        viewHolder.productDescription.setText(product.description);
        new DownloadImageTask(viewHolder.productImage).execute(product.url_image);
        viewHolder.productImage.setImageResource(R.drawable.allergy_product);

        //TODO: Find the data of product's company
        viewHolder.companyName.setText(product.title);
        viewHolder.companyDescription.setText(product.description);
        new DownloadImageTask(viewHolder.companyImage).execute(product.url_image);
        viewHolder.companyImage.setImageResource(R.drawable.allergy_product);

    }

    /**
     * Class To represent a view with elements
     */
    class ViewHolder {
        TextView productName;
        TextView companyName;
        TextView productDescription;
        TextView companyDescription;
        ImageView productImage;
        ImageView companyImage;

        public ViewHolder(View view) {

            productName = (TextView) view.findViewById(R.id.product_name);
            productDescription = (TextView) view.findViewById(R.id.product_description);
            productImage = (ImageView) view.findViewById(R.id.image_product);

            companyName = (TextView) view.findViewById(R.id.company_name);
            companyDescription = (TextView) view.findViewById(R.id.company_description);
            companyImage = (ImageView) view.findViewById(R.id.image_company);
        }
    }
}
