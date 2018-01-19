package app.calibroz.com.dragewithrecyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.calibroz.com.dragewithrecyclerview.databinding.ActivityMainBinding;
import app.calibroz.com.dragewithrecyclerview.databinding.ItemBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ArrayList<String> list;
    private DraggerAdapter draggerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recyclerVIew.setLayoutManager(new GridLayoutManager(this, 5));
        draggerAdapter = new DraggerAdapter(getList());
        binding.recyclerVIew.setAdapter(draggerAdapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                draggerAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(binding.recyclerVIew);


    }

    public ArrayList<String> getList() {
        list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        return list;
    }


    private class DraggerAdapter extends RecyclerView.Adapter<DraggerAdapter.DaraggerHolder> {
        private ArrayList<String> list;


        public DraggerAdapter(ArrayList<String> list) {
            this.list = list;
        }

        @Override
        public DaraggerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ItemBinding itemBinding = ItemBinding.inflate(layoutInflater, parent, false);
            return new DaraggerHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(DaraggerHolder holder, int position) {
            holder.bind(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        protected class DaraggerHolder extends RecyclerView.ViewHolder {

            ItemBinding binding;

            public DaraggerHolder(ItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind(String s) {
                binding.setName(s);
                binding.executePendingBindings();
            }

        }
    }
}
