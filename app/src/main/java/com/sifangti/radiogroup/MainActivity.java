package com.sifangti.radiogroup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.tab_home)
    RadioButton tabHome;
    @BindView(R.id.tab_rankings)
    RadioButton tabRankings;
    @BindView(R.id.tab_categorys)
    RadioButton tabCategorys;
    @BindView(R.id.tab_bar)
    RadioGroup tabBar;
    @BindView(R.id.card_view)
    CardView cardView;



    public final static String ACTION_EXIT_SYSTEM = "sys_exit";

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FrHome frHome;
    private FrRankings frRankings;
    private FrCategorys frCategorys;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//该绑定如果不是在一个公共类里初始化就绑定，一般每次onCreater初始化时都要这样绑定一次。
        RadioButton tab_home = (RadioButton)tabBar.getChildAt(0);
        tab_home.setChecked(true);
        tabBar.setOnCheckedChangeListener(this);
        initFragment();

    }

    private void initFragment(){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        frHome = new FrHome();
        transaction.add(R.id.frame_layout,frHome);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup,int checkedId){
        switch (checkedId){

            case R.id.tab_home:
                FragmentTransaction ft1 = manager.beginTransaction();
                hideAll(ft1);
                if(frHome!=null){
                    ft1.show(frHome);
                }else {
                    frHome = new FrHome();
                    ft1.add(R.id.frame_layout,frHome);
                }
                ft1.commit();
                break;
            case R.id.tab_rankings:
                FragmentTransaction ft2 = manager.beginTransaction();
                hideAll(ft2);
                if(frRankings!=null){
                    ft2.show(frRankings);
                }else {
                    frRankings = new FrRankings();
                    ft2.add(R.id.frame_layout,frRankings);
                }
                ft2.commit();
                break;
            case R.id.tab_categorys:
                FragmentTransaction ft3 = manager.beginTransaction();
                hideAll(ft3);
                if(frCategorys!=null){
                    ft3.show(frCategorys);
                }else {
                    frCategorys = new FrCategorys();
                    ft3.add(R.id.frame_layout,frCategorys);
                }
                ft3.commit();
                break;
        }
    }

    private void hideAll(FragmentTransaction ft){
        if (ft==null){
            return;
        }
        if(frHome!=null){
            ft.hide(frHome);
        }
        if(frRankings!=null){
            ft.hide(frRankings);
        }
        if(frCategorys!=null){
            ft.hide(frCategorys);
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this,"再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onExit(MainActivity.this);
                }
            }, 500);
        }
    }

    public static void onExit(final Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction(context.getApplicationContext().getPackageName() + ACTION_EXIT_SYSTEM);
            context.sendBroadcast(intent);
            // MobclickAgent.onKillProcess(context);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
