package com.liz.myapplication;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liz.myapplication.enity.Lists;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<Lists> mLists;
    private static OnItemClickListener mListener;

    public ListAdapter(List<Lists> mLists) {
        this.mLists = mLists;
    }

    public void setOnItemClick(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_layout, viewGroup, false);
        return new ListViewHolder(view,mLists);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int i) {
        holder.bindData(mLists.get(i));
    }

    @Override
    public int getItemCount() {
        return mLists != null ? mLists.size() : 0;
    }

    interface OnItemClickListener {
        void setOnItemClick(List<Lists> lists, int position);
    }

    static class ListViewHolder extends RecyclerView.ViewHolder{
        private TextView txtMain,txtDt, txtTemp, txtTempMin, txtTempMax, txtPressure;
        private TextView txtSeaLevel, txtGrndLevel, txtHumidity, txtTempKf;
        private TextView txtWeather,txtWeatherId, txtWeatherMain, txtDescription, txtWeatherIcon;
        private TextView txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9;
        private TextView txt10, txt11, txt12, txt13;
        private int countMain = 0;
        private int countWeather = 0;
        public ListViewHolder(@NonNull View itemView, final List<Lists> lists) {
            super(itemView);
            initView(itemView);
            mainViewState(View.GONE);
            weatherViewState(View.GONE);
            txtMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.setOnItemClick(lists,getLayoutPosition());
                    if(countMain % 2==0){
                        mainViewState(View.VISIBLE);
                    }else {
                        mainViewState(View.GONE);
                    }
                    countMain++;
                }
            });
            txtWeather.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.setOnItemClick(lists,getLayoutPosition());
                    if(countWeather % 2==0){
                        weatherViewState(View.VISIBLE);
                    }else {
                        weatherViewState(View.GONE);
                    }
                    countWeather++;
                }
            });
        }

        private void mainViewState(int state) {
            txt2.setVisibility(state);
            txt3.setVisibility(state);
            txt4.setVisibility(state);
            txt5.setVisibility(state);
            txt6.setVisibility(state);
            txt7.setVisibility(state);
            txt8.setVisibility(state);
            txt9.setVisibility(state);

            txtTemp.setVisibility(state);
            txtTempMin.setVisibility(state);
            txtTempMax.setVisibility(state);
            txtPressure.setVisibility(state);
            txtSeaLevel.setVisibility(state);
            txtGrndLevel.setVisibility(state);
            txtHumidity.setVisibility(state);
            txtTempKf.setVisibility(state);
        }

        private void weatherViewState(int state) {
            txtWeatherId.setVisibility(state);
            txtWeatherMain.setVisibility(state);
            txtDescription.setVisibility(state);
            txtWeatherIcon.setVisibility(state);

            txt10.setVisibility(state);
            txt11.setVisibility(state);
            txt12.setVisibility(state);
            txt13.setVisibility(state);
        }

        private void initView(View itemView) {
            txtMain = itemView.findViewById(R.id.txtMain);
            txtDt = itemView.findViewById(R.id.txtDt);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            txtTempMin = itemView.findViewById(R.id.txtTempMin);
            txtTempMax = itemView.findViewById(R.id.txtTempMax);
            txtPressure = itemView.findViewById(R.id.txtPressSure);
            txtSeaLevel = itemView.findViewById(R.id.txtSeaLevel);
            txtGrndLevel = itemView.findViewById(R.id.txtGrndLevel);
            txtHumidity = itemView.findViewById(R.id.txtHumidity);
            txtTempKf = itemView.findViewById(R.id.txtTempKf);
            txt2 = itemView.findViewById(R.id.textView2);
            txt3 = itemView.findViewById(R.id.textView3);
            txt4 = itemView.findViewById(R.id.textView4);
            txt5 = itemView.findViewById(R.id.textView5);
            txt6 = itemView.findViewById(R.id.textView6);
            txt7 = itemView.findViewById(R.id.textView7);
            txt8 = itemView.findViewById(R.id.textView8);
            txt9 = itemView.findViewById(R.id.textView9);

            txtWeather = itemView.findViewById(R.id.txtWeather);
            txtWeatherId = itemView.findViewById(R.id.txtWeatherId);
            txtWeatherMain = itemView.findViewById(R.id.txtWeatherMain);
            txtDescription = itemView.findViewById(R.id.txtWeatherDescription);
            txtWeatherIcon = itemView.findViewById(R.id.txtWeatherIcon);
            txt10 = itemView.findViewById(R.id.textView10);
            txt11 = itemView.findViewById(R.id.textView11);
            txt12 = itemView.findViewById(R.id.textView12);
            txt13 = itemView.findViewById(R.id.textView13);
        }

        @SuppressLint("SetTextI18n")
        private void bindData(Lists lists) {
            txtDt.setText(lists.getDt() + "");
            txtTemp.setText(lists.getMain().getTemp() + "");
            txtTempMin.setText(lists.getMain().getTempMin() + "");
            txtTempMax.setText(lists.getMain().getTempMax() + "");
            txtPressure.setText(lists.getMain().getPressSure() + "");
            txtSeaLevel.setText(lists.getMain().getSeaLevel() + "");
            txtGrndLevel.setText(lists.getMain().getGrndLevel() + "");
            txtHumidity.setText(lists.getMain().getHumidity() + "");
            txtTempKf.setText(lists.getMain().getTempKf() + "");
            txtWeatherId.setText(lists.getWeather().getId() + "");
            txtWeatherMain.setText(lists.getWeather().getMain());
            txtDescription.setText(lists.getWeather().getDescription());
            txtWeatherIcon.setText(lists.getWeather().getIcon());
        }
    }
}
