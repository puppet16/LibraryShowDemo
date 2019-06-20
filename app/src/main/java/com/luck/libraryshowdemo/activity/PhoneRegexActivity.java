package com.luck.libraryshowdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;
import com.luck.libraryshowdemo.logic.PhoneRegexManager;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

public class PhoneRegexActivity extends BaseActivity {

    @BindView(R.id.phoneEdtNumber)
    AppCompatEditText mEdt;
    @BindView(R.id.phoneSpinnerRegexType)
    Spinner mSpinner;
    private int mSelectedType;

    @BindArray(R.array.phoneRegexType)
    String[] mRegexType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneregex);
        initListener();
    }

    private void initListener() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.phoneBtnRegex})
    public void onClick(View v) {
        if (TextUtils.isEmpty(mEdt.getText().toString())) {
            Toast.makeText(mActivity, "号码不能为空呐！", Toast.LENGTH_SHORT).show();
            return;
        }
        String content = mEdt.getText().toString();
        String toastContent = mRegexType[mSelectedType];
        boolean result;
        PhoneRegexManager manager = PhoneRegexManager.getInstance();
        switch (mSelectedType) {
            case 0:
            default:
                result = manager.checkAll(content);
                break;
            case 1:
                result = manager.checkPhoneOfMessage(content);
                break;
            case 2:
                result = manager.checkPhoneAll(content);
                break;
            case 3:
                result = manager.checkPhoneMobile(content);
                break;
            case 4:
                result = manager.checkPhoneUnicom(content);
                break;
            case 5:
                result = manager.checkPhoneTelecom(content);
                break;
            case 6:
                result = manager.checkVirtualPhone(content);
                break;
            case 7:
                result = manager.checkVirtualPhoneMobile(content);
                break;
            case 8:
                result = manager.checkVirtualPhoneUnicom(content);
                break;
            case 9:
                result = manager.checkVirtualPhoneTelecom(content);
                break;
        }
        String showContent = "号码：" + content + "类型：" + toastContent + "  结果为：" + result;
        Log.d(TAG, showContent);
        Toast.makeText(mActivity, showContent, Toast.LENGTH_SHORT).show();
    }
}
