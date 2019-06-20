package com.luck.libraryshowdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.bean.ExcelPanelCell;
import com.luck.libraryshowdemo.bean.ExcelPanelColTitle;
import com.luck.libraryshowdemo.bean.ExcelPanelRowTitle;

import cn.zhouchaoyuan.excelpanel.BaseExcelPanelAdapter;


public class ExcelPanelAdapter extends BaseExcelPanelAdapter<ExcelPanelRowTitle, ExcelPanelColTitle, ExcelPanelCell> {

    private Context context;
    private View.OnClickListener blockListener;

    public ExcelPanelAdapter(Context context, View.OnClickListener blockListener) {
        super(context);
        this.context = context;
        this.blockListener = blockListener;
    }

    //=========================================content's cell===========================================
    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.excelpanel_room_status_normal_cell, parent, false);
        CellHolder cellHolder = new CellHolder(layout);
        return cellHolder;
    }

    @Override
    public void onBindCellViewHolder(RecyclerView.ViewHolder holder, int verticalPosition, int horizontalPosition) {
        ExcelPanelCell excelPanelCell = getMajorItem(verticalPosition, horizontalPosition);
        if (null == holder || !(holder instanceof CellHolder) || excelPanelCell == null) {
            return;
        }
        CellHolder viewHolder = (CellHolder) holder;
        viewHolder.cellContainer.setTag(excelPanelCell);
        viewHolder.cellContainer.setOnClickListener(blockListener);
        if (excelPanelCell.getStatus() == 0) {
            viewHolder.bookingName.setText("");
            viewHolder.channelName.setText("");
            viewHolder.cellContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        } else {
            viewHolder.bookingName.setText(excelPanelCell.getTeacherName());
            viewHolder.channelName.setText(excelPanelCell.getCourseName());
            if (excelPanelCell.getStatus() == 1) {
                viewHolder.cellContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.left));
            } else if (excelPanelCell.getStatus() == 2) {
                viewHolder.cellContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.staying));
            } else {
                viewHolder.cellContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.booking));
            }
        }
    }

    static class CellHolder extends RecyclerView.ViewHolder {

        public final TextView bookingName;
        public final TextView channelName;
        public final LinearLayout cellContainer;

        public CellHolder(View itemView) {
            super(itemView);
            bookingName = (TextView) itemView.findViewById(R.id.booking_name);
            channelName = (TextView) itemView.findViewById(R.id.channel_name);
            cellContainer = (LinearLayout) itemView.findViewById(R.id.pms_cell_container);
        }
    }


    //=========================================top cell===========================================
    @Override
    public RecyclerView.ViewHolder onCreateTopViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.excelpanel_room_status_top_header, parent, false);
        TopHolder topHolder = new TopHolder(layout);
        return topHolder;
    }

    @Override
    public void onBindTopViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExcelPanelRowTitle excelPanelRowTitle = getTopItem(position);
        if (null == holder || !(holder instanceof TopHolder) || excelPanelRowTitle == null) {
            return;
        }
        TopHolder viewHolder = (TopHolder) holder;
        viewHolder.roomWeek.setText(excelPanelRowTitle.getWeekString());
        viewHolder.roomDate.setText(excelPanelRowTitle.getDateString());
    }

    static class TopHolder extends RecyclerView.ViewHolder {

        public final TextView roomDate;
        public final TextView roomWeek;

        public TopHolder(View itemView) {
            super(itemView);
            roomDate = (TextView) itemView.findViewById(R.id.data_label);
            roomWeek = (TextView) itemView.findViewById(R.id.week_label);
        }
    }

    //=========================================left cell===========================================
    @Override
    public RecyclerView.ViewHolder onCreateLeftViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.excelpanel_item_room_status_left_header, parent, false);
        LeftHolder leftHolder = new LeftHolder(layout);
        return leftHolder;
    }

    @Override
    public void onBindLeftViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExcelPanelColTitle excelPanelColTitle = getLeftItem(position);
        if (null == holder || !(holder instanceof LeftHolder) || excelPanelColTitle == null) {
            return;
        }
        LeftHolder viewHolder = (LeftHolder) holder;
        viewHolder.roomNumberLabel.setText(excelPanelColTitle.getCourseNum());
        viewHolder.roomTypeLabel.setText(excelPanelColTitle.getCourseTime());
        ViewGroup.LayoutParams lp = viewHolder.root.getLayoutParams();
        viewHolder.root.setLayoutParams(lp);
    }

    static class LeftHolder extends RecyclerView.ViewHolder {

        public final TextView roomNumberLabel;
        public final TextView roomTypeLabel;
        public final View root;

        public LeftHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            roomNumberLabel = (TextView) itemView.findViewById(R.id.room_number_label);
            roomTypeLabel = (TextView) itemView.findViewById(R.id.room_type_label);
        }
    }

    //=========================================left-top cell===========================================
    @Override
    public View onCreateTopLeftView() {
        return LayoutInflater.from(context).inflate(R.layout.excelpanel_room_status_normal_cell, null);
    }
}
