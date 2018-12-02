package com.uka.doordashlite;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static org.junit.Assert.*;

public class RecyclerViewItemCountAssertion implements ViewAssertion {

    int expectedCount;

    public RecyclerViewItemCountAssertion(int count) {
        expectedCount = count;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }
        RecyclerView recyclerView = (RecyclerView) view;

        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        assertEquals(adapter.getItemCount(), expectedCount);
    }
}
