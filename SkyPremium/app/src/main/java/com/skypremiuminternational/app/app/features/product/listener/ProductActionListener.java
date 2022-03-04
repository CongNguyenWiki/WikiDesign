package com.skypremiuminternational.app.app.features.product.listener;

public interface ProductActionListener<T> {
    void onItemClicked(T item,int position);
}
