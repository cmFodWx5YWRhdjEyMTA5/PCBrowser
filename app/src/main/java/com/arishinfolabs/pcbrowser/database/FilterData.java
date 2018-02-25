package com.arishinfolabs.pcbrowser.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by EE207823 on 2/25/2018.
 */

@Table(name = "FilterData")
public class FilterData extends Model {

    @Column(name = "Filter")
    public String filterString;

    public static List<FilterData> getFilterList() {
            return new Select().from(FilterData.class).orderBy("Filter DESC").execute();
    }
}
