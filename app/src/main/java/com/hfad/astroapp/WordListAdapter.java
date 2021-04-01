//package com.hfad.astroapp;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import android.content.Context;
//import android.widget.TextView;
//import java.util.LinkedList;
//import android.view.View;
//
//public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
//
//    private final LayoutInflater mInflater;
//    private final LinkedList<String> mWordList;
//
//    public WordListAdapter(Context context, LinkedList<String> wordList){
//        mInflater = LayoutInflater.from(context);
//        this.mWordList = wordList;
//    }
//    @NonNull
//    @Override
//    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
//        return new WordViewHolder(mItemView,this);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
//        String mCurr = mWordList.get(position);
//        holder.wordItemView.setText(mCurr);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mWordList.size();
//    }
//
//    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private final WordListAdapter mAdapter;
//        public TextView wordItemView;
//
//
//        public WordViewHolder(View mItemView, WordListAdapter wordListAdapter) {
//            super(mItemView);
//            wordItemView = mItemView.findViewById(R.id.word);
//            this.mAdapter = wordListAdapter;
//            mItemView.setOnClickListener(this);
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            int mPosition = getLayoutPosition();
//            String element = mWordList.get(mPosition);
//            mWordList.set(mPosition, "Clicked!" + element);
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//}
