package com.luck.libraryshowdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.luck.libraryshowdemo.adapter.ExcelPanelAdapter;
import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;
import com.luck.libraryshowdemo.bean.ExcelPanelCell;
import com.luck.libraryshowdemo.bean.ExcelPanelColTitle;
import com.luck.libraryshowdemo.bean.ExcelPanelRowTitle;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import cn.zhouchaoyuan.excelpanel.ExcelPanel;
import es.dmoral.toasty.Toasty;

public class ExcelPanelActivity extends BaseActivity implements ExcelPanel.OnLoadMoreListener {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String WEEK_FORMAT_PATTERN = "EEEE";
    public static final String[] COURSE = {"英语", "数学", "思想品德", "国学", "计算机", "化学", "物理"};
    public static final String[] TEACHERNAME = {"扬伟", "屠咏志", "杨宜知", "社子腾", "段珍", "朱逸群", "焦厚根"};

    public static final long ONE_DAY = 24 * 3600 * 1000;
    public static final int PAGE_SIZE = 14;
    public static final int ROW_SIZE = 20;

    @BindView(R.id.excelpanel_content_container)
    ExcelPanel excelPanel;
    @BindView(R.id.excelpanel_progress)
    ProgressBar progress;

    private ExcelPanelAdapter adapter;
    private List<ExcelPanelRowTitle> excelPanelRowTitles;
    private List<ExcelPanelColTitle> excelPanelColTitles;
    private List<List<ExcelPanelCell>> cells;
    private SimpleDateFormat dateFormatPattern;
    private SimpleDateFormat weekFormatPattern;
    private boolean isLoading;
    private long historyStartTime;
    private long moreStartTime;

    private Handler loadDataHandler = new WeakRefenceHandler(this);

    @Override
    protected boolean hasCustomSlide() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excelpanel);
        adapter = new ExcelPanelAdapter(mActivity, blockListener);
        excelPanel.setAdapter(adapter);
        excelPanel.setOnLoadMoreListener(this);
        excelPanel.addOnScrollListener(onScrollListener);
        initData();
    }

    private ExcelPanel.OnScrollListener onScrollListener = new ExcelPanel.OnScrollListener() {
        @Override
        public void onScrolled(ExcelPanel excelPanel, int dx, int dy) {
            super.onScrolled(excelPanel, dx, dy);
            Log.e("acjiji", dx + "     " + dy);
        }
    };

    private View.OnClickListener blockListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ExcelPanelCell excelPanelCell = (ExcelPanelCell) view.getTag();
            if (excelPanelCell != null) {
                if (excelPanelCell.getStatus() == 0) {
                    Toasty.warning(mActivity, "不要点击空白区域").show();
                } else if (excelPanelCell.getStatus() == 1) {
                    Toasty.info(mActivity, excelPanelCell.getTeacherName() + "讲授的" + excelPanelCell.getCourseName() + "人数已满，可以正常开课").show();

                } else if (excelPanelCell.getStatus() == 2) {
                    Toasty.info(mActivity, excelPanelCell.getTeacherName() + "讲授的" + excelPanelCell.getCourseName() + "人数不足，无法开课").show();
                } else if (excelPanelCell.getStatus() == 3) {
                    Toasty.info(mActivity, excelPanelCell.getTeacherName() + "讲授的" + excelPanelCell.getCourseName() + "课程已关闭").show();

                }
            }
        }
    };

    @Override
    public void onLoadMore() {
        if (!isLoading) {
            loadData(moreStartTime, false);
        }
    }

    @Override
    public void onLoadHistory() {
        if (!isLoading) {
            loadData(historyStartTime, true);
        }
    }

    private void initData() {
        moreStartTime = Calendar.getInstance().getTimeInMillis();
        historyStartTime = moreStartTime - ONE_DAY * PAGE_SIZE;
        dateFormatPattern = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        weekFormatPattern = new SimpleDateFormat(WEEK_FORMAT_PATTERN);
        excelPanelRowTitles = new ArrayList<>();
        excelPanelColTitles = new ArrayList<>();
        cells = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            cells.add(new ArrayList<ExcelPanelCell>());
        }
        loadData(moreStartTime, false);
    }

    private void loadData(long startTime, final boolean history) {
        //模拟网络加载
        isLoading = true;
        Message message = new Message();
        message.arg1 = history ? 1 : 2;
        message.obj = new Long(startTime);
        loadDataHandler.sendMessageDelayed(message, 1000);
    }

    private static class WeakRefenceHandler extends Handler {
        private WeakReference<ExcelPanelActivity> mRefrence;

        public WeakRefenceHandler(ExcelPanelActivity refrence) {
            this.mRefrence = new WeakReference<ExcelPanelActivity>(refrence);
        }

        @Override
        public void handleMessage(Message msg) {
            ExcelPanelActivity refrence = mRefrence.get();
            refrence.isLoading = false;
            long startTime = (Long) msg.obj;
            List<ExcelPanelRowTitle> excelPanelRowTitles1 = refrence.genRowData(startTime);
            List<List<ExcelPanelCell>> cells1 = refrence.genCellData();
            if (msg.arg1 == 1) {//history
                refrence.historyStartTime -= ONE_DAY * PAGE_SIZE;
                refrence.excelPanelRowTitles.addAll(0, excelPanelRowTitles1);
                for (int i = 0; i < cells1.size(); i++) {
                    refrence.cells.get(i).addAll(0, cells1.get(i));
                }

                //加载了数据之后偏移到上一个位置去
                if (refrence.excelPanel != null) {
                    refrence.excelPanel.addHistorySize(PAGE_SIZE);
                }
            } else {
                refrence.moreStartTime += ONE_DAY * PAGE_SIZE;
                refrence.excelPanelRowTitles.addAll(excelPanelRowTitles1);
                for (int i = 0; i < cells1.size(); i++) {
                    refrence.cells.get(i).addAll(cells1.get(i));
                }
            }
            if (refrence.excelPanelColTitles.size() == 0) {
                refrence.excelPanelColTitles.addAll(refrence.genColData());
            }
            refrence.progress.setVisibility(View.GONE);
            refrence.adapter.setAllData(refrence.excelPanelColTitles, refrence.excelPanelRowTitles, refrence.cells);
            refrence.adapter.enableFooter();
            refrence.adapter.enableHeader();
        }
    }

    //====================================模拟生成数据==========================================
    private List<ExcelPanelRowTitle> genRowData(long startTime) {
        List<ExcelPanelRowTitle> excelPanelRowTitles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < PAGE_SIZE; i++) {
            ExcelPanelRowTitle excelPanelRowTitle = new ExcelPanelRowTitle();
            excelPanelRowTitle.setTotalPersonalNum(random.nextInt(10) + 10);
            excelPanelRowTitle.setDateString(dateFormatPattern.format(startTime + i * ONE_DAY));
            excelPanelRowTitle.setWeekString(weekFormatPattern.format(startTime + i * ONE_DAY));
            excelPanelRowTitles.add(excelPanelRowTitle);
        }
        return excelPanelRowTitles;
    }

    private List<ExcelPanelColTitle> genColData() {
        List<ExcelPanelColTitle> excelPanelColTitles = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            ExcelPanelColTitle excelPanelColTitle = new ExcelPanelColTitle();
            excelPanelColTitle.setCourseNum("第" + (i + 1) + "节");
            excelPanelColTitle.setCourseTime((4 + i) + ":00");
            excelPanelColTitles.add(excelPanelColTitle);
        }
        return excelPanelColTitles;
    }

    private List<List<ExcelPanelCell>> genCellData() {
        List<List<ExcelPanelCell>> cells = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            List<ExcelPanelCell> excelPanelCellList = new ArrayList<>();
            cells.add(excelPanelCellList);
            for (int j = 0; j < PAGE_SIZE; j++) {
                ExcelPanelCell excelPanelCell = new ExcelPanelCell();
                Random random = new Random();
                int number = random.nextInt(6);
                if (number == 1 || number == 2 || number == 3) {
                    excelPanelCell.setStatus(number);
                    excelPanelCell.setCourseName(COURSE[random.nextInt(COURSE.length)]);
                    excelPanelCell.setTeacherName(TEACHERNAME[random.nextInt(TEACHERNAME.length)]);
                } else {
                    excelPanelCell.setStatus(0);
                }
                excelPanelCellList.add(excelPanelCell);
            }
        }
        return cells;
    }
}
