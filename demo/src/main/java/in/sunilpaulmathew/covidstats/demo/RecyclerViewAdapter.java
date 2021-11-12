package in.sunilpaulmathew.covidstats.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on August 25, 2021
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static List<RecyclerViewItem> data;
    public RecyclerViewAdapter(List<RecyclerViewItem> data){
        RecyclerViewAdapter.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(rowItem);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.mCountry.setText(data.get(position).getCountry().toUpperCase());
            holder.mTotalC.setText(holder.mTotalC.getContext().getString(R.string.cases_total, data.get(position).getTotalCases()));
            holder.mNewC.setText(holder.mNewC.getContext().getString(R.string.cases_new, data.get(position).getNewCases()));
            holder.mNewC.setTextColor(Color.RED);
            holder.mTotalD.setText(holder.mTotalD.getContext().getString(R.string.deaths_total, data.get(position).getTotalDeaths()));
            holder.mNewD.setText(holder.mNewD.getContext().getString(R.string.deaths_new, data.get(position).getNewDeaths()));
            holder.mNewD.setTextColor(Color.RED);
            if (!isDarkTheme(holder.mCardLeft.getContext())) {
                holder.mCardLeft.setCardBackgroundColor(Color.LTGRAY);
                holder.mCardRight.setCardBackgroundColor(Color.LTGRAY);
            }
        } catch (IndexOutOfBoundsException | NullPointerException ignored) {}
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final MaterialCardView mCardLeft, mCardRight;
        private final MaterialTextView mCountry, mTotalC, mNewC, mTotalD, mNewD;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.mCardLeft = view.findViewById(R.id.left_card);
            this.mCardRight = view.findViewById(R.id.right_card);
            this.mCountry = view.findViewById(R.id.country);
            this.mTotalC = view.findViewById(R.id.total_case);
            this.mNewC = view.findViewById(R.id.new_cases);
            this.mTotalD = view.findViewById(R.id.total_deaths);
            this.mNewD = view.findViewById(R.id.new_deaths);
        }

        @Override
        public void onClick(View view) {
        }
    }

    private static boolean isDarkTheme(Context context) {
        int currentNightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

}