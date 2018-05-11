package com.mlethe.app.view.day04;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlethe.app.view.R;

public class ViewPagerActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private String[] mTitles = new String[]{"关注", "直播", "推荐", "视频", "图片", "段子", "精华", "游戏", "同城", "段友秀"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        tabLayout = findViewById(R.id.font_tab);
        viewPager = findViewById(R.id.font_view_pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // position  代表当前的位置
                // positionOffset 代表滚动的 0 - 1百分比
                ColorTrackTextView left = tabLayout.getTabAt(position).getCustomView().findViewById(R.id.item_font_tab_tv);
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                left.setProcess(1 - positionOffset);
                if ((position + 1) < mTitles.length ) {
                    ColorTrackTextView right = tabLayout.getTabAt(position + 1).getCustomView().findViewById(R.id.item_font_tab_tv);
                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                    right.setProcess(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        FontAdapter adapter = new FontAdapter(this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        initTab(adapter);
        //viewPager.setCurrentItem(1);
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    private void initTab(FontAdapter adapter) {
        for (int i = 0; i < mTitles.length; i++) {
            //tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                    tabView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        }
    }

    private class FontAdapter extends PagerAdapter{

        private Context mContext;

        public FontAdapter(Context context){
            this.mContext = context;
        }

        public View getTabView(int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_font_tab, null);
            ColorTrackTextView tv = view.findViewById(R.id.item_font_tab_tv);
            tv.setText(mTitles[position]);
            return view;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_font_view_pager, container, false);
            TextView textView = view.findViewById(R.id.item_font_view_pager_tv);
            textView.setText(mTitles[position]);
            //添加到容器
            container.addView(view);
            return view;
        }

        /**
         * 销毁view
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从容器中移除view
            container.removeView((View) object);
            object = null;
        }
    }
}
