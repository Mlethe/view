package com.mlethe.app.view.day09;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 流式布局的adapter
 * Created by Mlethe on 2018/3/12.
 */

abstract class FlowAdapter<T> {

    protected Context mContext;

    protected List<T> mData;

    private int mLayoutId;

    private DataObservable mObserver = new DataObservable();

    protected FlowAdapter(Context context, List<T> data, @LayoutRes int layoutId){
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    // 1、有多少条目
    public int getCount() {
        return mData.size();
    }

    // 2、getView通过position
    public View getView(final int position, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        if (mTagClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTagClickListener.onItemClick(position);
                }
            });
        }
        if (mLongClickListener != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mLongClickListener.onLongClick(position);
                }
            });
        }
        convert(view, mData.get(position), position);
        return view;
    }

    // 3、观察者模式及时通知更新
    public void unregisterDataSetObserver(DataObserver observer){
        mObserver.unregisterObserver(observer);
    }

    public void registerDataSetObserver(DataObserver observer){
        mObserver.registerObserver(observer);
    }

    public void notifyDataSetChanged(){
        if (mObserver != null){
            mObserver.notifyChanged();
        }
    }

    public abstract void convert(View view, T item, int position);

    private OnTagClickListener mTagClickListener;
    private OnLongClickListener mLongClickListener;

    public void setOnItemClickListener(OnTagClickListener tagClickListener) {
        this.mTagClickListener = tagClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.mLongClickListener = longClickListener;
    }

    public interface OnTagClickListener {
        void onItemClick(int position);
    }

    public interface OnLongClickListener {
        boolean onLongClick(int position);
    }

    private class DataObservable extends Observable<DataObserver> {
        public void notifyChanged() {
            // since onChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
    }
}
