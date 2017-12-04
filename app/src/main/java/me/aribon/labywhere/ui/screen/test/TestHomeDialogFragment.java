package me.aribon.labywhere.ui.screen.test;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseDialogFragment;

/**
 * Created by anthony.ribon
 * On 25/09/2017
 */

public class TestHomeDialogFragment extends BaseDialogFragment implements
        TestHomeDialogFragmentContract.View {

    @Bind(R.id.test_home_data_result_text)
    TextView tvDataResult;

    TestHomeDialogFragmentContract.Presenter presenter;

    public static TestHomeDialogFragment newInstance() {

        Bundle args = new Bundle();

        TestHomeDialogFragment fragment = new TestHomeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TestHomeDialogFragment() {
    }

    @Override
    public int getLayoutResource() {
        return R.layout.dialog_fragment_home_test;
    }

    @Override
    protected int getStyleResource() {
        return 0;
    }

    @Override
    protected void addLayoutParams(Dialog dialog) {
        super.addLayoutParams(dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void findView(View view) {
        super.findView(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter();
        presenter = new TestHomeDialogFragmentPresenter(this);
    }

    @Override
    public void initializeData() {
        super.initializeData();
        presenter.loadData();
    }

    @Override
    public void showDataResult(String s) {
        tvDataResult.setText(s);
    }
}
