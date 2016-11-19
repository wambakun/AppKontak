package androidkejar.appkontak.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidkejar.appkontak.R;
import androidkejar.appkontak.listener.RecyclerItemClickListener;
import androidkejar.appkontak.model.Kontak;
import io.realm.RealmResults;

/**
 * Created by Kirito on 18/11/2016.
 */

public class KontakListAdapter extends RecyclerView.Adapter<KontakListAdapter.KontakViewHolder> {
    public RealmResults<Kontak> kontakList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public KontakListAdapter(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public void setKontakList(RealmResults<Kontak> kontakList) {
        this.kontakList = kontakList;
        notifyDataSetChanged();
    }

    public Kontak getItem(int position) {
        return kontakList.get(position);
    }

    @Override
    public KontakViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kontak, parent, false);
        final KontakViewHolder kontakViewHolder = new KontakViewHolder(view);
        kontakViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = kontakViewHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemClickListener.itemClick(adapterPos, kontakViewHolder.itemView);
                    }
                }
            }
        });

        return kontakViewHolder;
    }

    @Override
    public void onBindViewHolder(KontakViewHolder holder, int position) {
        final Kontak kontak = kontakList.get(position);

        holder.nama.setText(kontak.getNama().length() > 50 ? kontak.getNama().substring(0, 50) : kontak.getNama());
        holder.number.setText(kontak.getNumber().length() > 50 ? kontak.getNumber().substring(0, 50) : kontak.getNumber());

    }

    @Override
    public int getItemCount() {
        return kontakList.size();
    }

    public static class KontakViewHolder extends RecyclerView.ViewHolder {

        TextView nama;
        TextView number;

        public KontakViewHolder(View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.nama);
            number = (TextView) itemView.findViewById(R.id.telp);
        }
    }
}
