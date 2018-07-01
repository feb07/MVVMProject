package com.feb.mvvmproject;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.feb.mvvmproject.databinding.MainMvvmBinding;
import com.feb.mvvmproject.model.UserBean;

public class MainActivity extends AppCompatActivity {

    private MainMvvmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_mvvm);
        UserBean userBean = new UserBean();
        userBean.name.set("张三");
        userBean.age.set(28);
        userBean.sex.set("男");
        binding.setUser(userBean);
    }
}
