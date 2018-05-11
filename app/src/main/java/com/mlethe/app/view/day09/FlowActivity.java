package com.mlethe.app.view.day09;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mlethe.app.view.R;

import java.util.ArrayList;
import java.util.List;

public class FlowActivity extends AppCompatActivity {

    private FlowLayout mTagLayout;

    private List<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        mTagLayout = findViewById(R.id.tag_layout);
        // 标签 后台获取
        mItems = new ArrayList<>();
        TagAdapter adapter = new TagAdapter(this, mItems);
        adapter.setOnItemClickListener(new FlowAdapter.OnTagClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(FlowActivity.this, mItems.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        mTagLayout.setAdapter(adapter);

        mItems.add("运动套装五件套男");
        mItems.add("零食");
        mItems.add("运动套装男");
        mItems.add("二手车");
        mItems.add("天猫超市");
        mItems.add("苹果笔记本电脑");
        mItems.add("课外书");
        mItems.add("运动套装男 运动套装 运动服");
        mItems.add("彩票");
        mItems.add("电刷");
        mItems.add("运动套装五件套男");
        mItems.add("零食");
        mItems.add("运动套装男");
        mItems.add("二手车");
        mItems.add("天猫超市");
        mItems.add("苹果笔记本电脑");
        mItems.add("课外书");
        mItems.add("运动套装男 运动套装 运动服");
        mItems.add("彩票");
        mItems.add("电刷");
        mItems.add("运动套装五件套男");
        mItems.add("零食");
        mItems.add("运动套装男");
        mItems.add("二手车");
        mItems.add("天猫超市");
        mItems.add("苹果笔记本电脑");
        mItems.add("课外书");
        mItems.add("运动套装男 运动套装 运动服");
        mItems.add("彩票");
        mItems.add("电刷");
        adapter.notifyDataSetChanged();
    }

    private class TagAdapter extends FlowAdapter<String> {
        protected TagAdapter(Context context, List<String> data) {
            super(context, data, R.layout.item_tag);
        }

        @Override
        public void convert(View view, String item, int position) {
            TextView tagTv = view.findViewById(R.id.item_tag_tv);
            tagTv.setText(mItems.get(position));
        }
    }
}
