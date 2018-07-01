package com.feb.mvvmproject.model;

import android.databinding.ObservableField;

/**
 * Created by lilichun on 18/7/1.
 */

public class UserBean {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Integer> age = new ObservableField<>();
    public ObservableField<String> sex = new ObservableField<>();
}
