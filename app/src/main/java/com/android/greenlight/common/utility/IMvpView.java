package com.android.greenlight.common.utility;

import android.content.Context;


public interface IMvpView {

    Context getContext();

    interface OnFilterResultListener {
        void onZeroResult(String emptyMsg);

        void onMoreThanZeroResult(int resultSize);
    }

}
