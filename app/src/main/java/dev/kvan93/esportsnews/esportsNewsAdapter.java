package dev.kvan93.esportsnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class esportsNewsAdapter extends RecyclerView.Adapter<esportsNewsAdapter.esportsViewHolder>  {

    public static class  esportsViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;



        esportsViewHolder(View view){
            super(view);
            containerView = view.findViewById(R.id.esports_row);
            textView = view.findViewById(R.id.esports_row_text_view);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    esport current = (esport) containerView.getTag();
                    Intent intent = new Intent(v.getContext(), esportsNewsActivity.class);
                    intent.putExtra("appid", current.getAppId());
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

    // TODO Put in a Db? so you can add extra titles
    private List<esport> mainList = Arrays.asList(
            new esport("CS:GO",730,R.drawable.csgo),
            new esport("Dota 2", 570, R.drawable.dota2),
            new esport("Rocket League", 252950,R.drawable.rl),
            new esport("League of Legends", 20590,R.drawable.league),
            new esport("FIFA21", 1313860, R.drawable.fifa21),
            new esport("Call of Duty: MW",7940, R.drawable.callofduty),
            new esport("F12020",1080110, R.drawable.f12020),
            new esport("PUBG", 578080,R.drawable.pubg),
            new esport("Rainbow Six Siege", 359550, R.drawable.rbs6)

            );

    esportsNewsAdapter(Context context){

    }

    @NonNull
    @Override
    public esportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.esports_row, parent,false);

        return new esportsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull esportsViewHolder holder, int position) {
        esport current = mainList.get(position);
        //holder.textView.setText(current.getName());
        holder.containerView.setBackgroundResource(current.getBackgroundurl());
        holder.containerView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

}
