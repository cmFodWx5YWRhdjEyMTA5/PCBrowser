package com.arishinfolabs.pcbrowser.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.arishinfolabs.pcbrowser.R;
import com.arishinfolabs.pcbrowser.activities.PCBrowserActivity;
import com.arishinfolabs.pcbrowser.database.FilterData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish Chaudhary on 2/24/2018.
 */

public class AddFiltersFragment extends Fragment implements View.OnClickListener{

    private PCBrowserActivity mActivity;
    private ListView filterView;
    private ArrayAdapter<String> filtersAdapter;
    private ImageButton addFilters;
    private EditText filtersText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (PCBrowserActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_filters, container, false);

        filtersText = v.findViewById(R.id.filters_text);
        filterView = v.findViewById(R.id.filters_view);
        addFilters = v.findViewById(R.id.add_filters);
        addFilters.setOnClickListener(this);
        loadFilterListData();
        return v;
    }

    private void loadFilterListData() {
        filtersAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1);
        List<FilterData> filterDataList =  FilterData.getFilterList();
        ArrayList<String> dataList = new ArrayList<>();
        for (FilterData filterData : filterDataList) {
            dataList.add(filterData.filterString);
        }

        if(filterDataList == null) {
            Toast.makeText(mActivity, "No Filters List to show", Toast.LENGTH_LONG).show();
        }   else {
            filtersAdapter.addAll(dataList);
            filterView.setAdapter(filtersAdapter);
            filtersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.add_filters :
                addFiltersToList();
                break;
        }
    }

    private void addFiltersToList() {
        String filterString = filtersText.getText().toString();

        if(filterString.isEmpty()) {
            Toast.makeText(mActivity, "Enter filters keyword", Toast.LENGTH_LONG).show();
        } else {

            FilterData filterData = new FilterData();
            filterData.filterString = filterString;
            filterData.save();
            filtersAdapter.add(filterString);
            filterView.setAdapter(filtersAdapter);
            filtersAdapter.notifyDataSetChanged();
        }
    }


}
