package com.android.greenlight.common.utility;

/**
 * This interface is used for attachView and detch function to follow
 *
 * @author Vivek
 */

public interface IBasePresenter<V> {

    void attachView(V view);

    void detch();

}
