package com.cypressworks.viewpagerbug;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    private ViewPager pager;
    private TabLayout tabs;

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        pager = (ViewPager) view.findViewById(R.id.pager1);
        tabs = (TabLayout) view.findViewById(R.id.tabs);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pager.setPageMargin(2);
        pager.setPageMarginDrawable(new ColorDrawable(Color.GRAY));
        pager.setAdapter(new DatePagerAdapter());
        tabs.setupWithViewPager(pager);
        pager.setCurrentItem(1);
    }


    private class DatePagerAdapter extends FragmentStatePagerAdapter {

        private static final int DAYS_TO_SHOW = 365;

        public DatePagerAdapter() {
            super(getChildFragmentManager());
        }

        @Override
        public Fragment getItem(final int pos) {
            Fragment f = new PageFragment();
            Bundle args = new Bundle();
            args.putString("page", String.valueOf(pos));
            f.setArguments(args);
            return f;
        }

        @Override
        public int getCount() {
            return DAYS_TO_SHOW;
        }

        @Override
        public CharSequence getPageTitle(final int position) {
            return String.valueOf(position);
        }

    }

    public static class PageFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(
                final LayoutInflater inflater, @Nullable final ViewGroup container,
                @Nullable final Bundle savedInstanceState) {
            return inflater.inflate(R.layout.page, container, false);
        }

        @Override
        public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
            TextView t = (TextView) view.findViewById(R.id.textView);
            t.setText(getArguments().getString("page"));
        }
    }
}
