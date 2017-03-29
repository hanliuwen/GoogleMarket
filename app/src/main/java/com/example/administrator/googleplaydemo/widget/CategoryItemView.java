package com.example.administrator.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.bean.CategoryItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/28.
 */

public class CategoryItemView extends RelativeLayout {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.table_layout)
    TableLayout mTableLayout;

    public CategoryItemView(Context context) {
        this(context, null);
    }

    public CategoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_category_item, this);
        ButterKnife.bind(this, this);
    }

    /**
     * 用数据刷新CategoryItemView
     */
    public void bindView(CategoryItemBean categoryItemBean) {
        mTitle.setText(categoryItemBean.getTitle());
        //由于ListView回收机制，绑定时使用回收的view, 清空掉所有行
        mTableLayout.removeAllViews();

        //刷新网格
        //遍历数组，动态的往TableLayout里面添加行
        List<CategoryItemBean.InfosBean> infos = categoryItemBean.getInfos();
        for (int i = 0; i < infos.size(); i++) {
            TableRow row = new TableRow(getContext());
            //调整每个小模块的宽度
            //拿到屏幕宽度,减掉TableLayout左右两边的padding,平均分为三份,分给每一个小模块(CategoryItemInfoView)
            int result = getResources().getDisplayMetrics().widthPixels - mTableLayout.getPaddingLeft() - mTableLayout.getPaddingRight();
            //设置小模块的布局参数
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(getContext(), null);
            layoutParams.width = result / 3;

            //往row添加三个 小模块（图片 和 标题）
            //加入第一个小模块
            //获取一行数据
            CategoryItemBean.InfosBean infosBean = infos.get(i);

            CategoryItemInfoView categoryItemInfoView1 = new CategoryItemInfoView(getContext());
            categoryItemInfoView1.setLayoutParams(layoutParams);
            categoryItemInfoView1.bindView(infosBean.getName1(),infosBean.getUrl1());
            row.addView(categoryItemInfoView1);

            if (infosBean.getName2().length() > 0) {
                CategoryItemInfoView categoryItemInfoView2 = new CategoryItemInfoView(getContext());
                categoryItemInfoView2.setLayoutParams(layoutParams);
                categoryItemInfoView2.bindView(infosBean.getName2(), infosBean.getUrl2());
                row.addView(categoryItemInfoView2);
            }

            //当有数据时，才添加小模块
            if (infosBean.getName3().length() > 0) {
                CategoryItemInfoView categoryItemInfoView3 = new CategoryItemInfoView(getContext());
                categoryItemInfoView3.setLayoutParams(layoutParams);
                categoryItemInfoView3.bindView(infosBean.getName3(), infosBean.getUrl3());
                row.addView(categoryItemInfoView3);
            }

            //将一行加入TableLayout
            mTableLayout.addView(row);
        }
    }
}
