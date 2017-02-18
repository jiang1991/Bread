package space.xxv.bread;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import space.xxv.bread.model.Note;

/**
 * Created by wangjiang on 2017/2/13.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private NoteClickListener clickListener;

    @Nullable
    private List<Note> dataset;

    public interface NoteClickListener {
        void onNoteClick(int position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{
        public TextView text;

        public NoteViewHolder(View itemView) {
            super(itemView);
            text =  (TextView) itemView.findViewById(R.id.textViewNote);
            
            // set on click listener
        }
    }

    public NoteAdapter(NoteClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<Note>();
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(v);
    }

    public void setNote(@Nullable List<Note> notes) {
        dataset = notes;
    }

    public Note getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position){
        Note note = dataset.get(position);
        holder.text.setText(note.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
