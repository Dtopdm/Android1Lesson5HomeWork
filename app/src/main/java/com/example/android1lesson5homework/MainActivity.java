package com.example.android1lesson5homework;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter adapter;
    List<Title> list;
    int position;
    public static final String KEY = "key";
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
        list = new ArrayList<>();
        for (int i = 0; i < 40; i++){
            list.add(new Title("Item " + i +" Список заметок"));
        }

        adapter.setList(list);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        adapter.setClickListener(new OnItemClick() {
            @Override
            public void onItemClick(int position, Title title) {
                MainActivity.this.position = position;

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(KEY,title);

                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE && resultCode == RESULT_OK){

            Title title = (Title) data.getSerializableExtra(SecondActivity.KEY2);
            adapter.updateList(position, title);
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback
            (ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int positionDrag = viewHolder.getAdapterPosition();
            int positionTarget = target.getAdapterPosition();
            Collections.swap(adapter.list, positionDrag, positionTarget);
            adapter.notifyItemMoved(positionDrag, positionTarget);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.list.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

}