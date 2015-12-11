package sanadroid.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Integer[] posterID={
            R.drawable.movie1,R.drawable.movie2,R.drawable.movie3,
            R.drawable.movie4,R.drawable.movie5,R.drawable.movie6,
            R.drawable.movie7,R.drawable.movie8,R.drawable.movie9,
            R.drawable.movie10};
    String [] posterName = {"내부자들", "도리화가", "검은사제들", "007", "열정같은소리하네", "괴물이야기", "선샤인", "크림스피크", "파워레인저", "헹거게임"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter);
        setTitle("그리드뷰 영화포스터");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final GridView gv = (GridView)findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
    }
    private static class MoviewViewHolder{
        public ImageView poster;
        public TextView title;
    }

    public class MyGridAdapter extends BaseAdapter{
        private LayoutInflater layoutInflater;
        Context context;
        public MyGridAdapter(Context c){
            layoutInflater = LayoutInflater.from(c);
        }
        public int getCount(){
           return posterID.length;
        }
        public Object getItem(int position){
            return position;
        }
       public long getItemId(int position){
            return position;
        }

        public View getView(int position,View convertView,ViewGroup parent){
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(100, 150));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);

            MoviewViewHolder moviewViewHolder;
            View view = convertView;

           if(view ==null) {
                view = layoutInflater.inflate(R.layout.get_item, parent, false);

                moviewViewHolder = new MoviewViewHolder();
                moviewViewHolder.poster = (ImageView) view.findViewById(R.id.imageView);
                moviewViewHolder.title = (TextView) view.findViewById(R.id.textView);
                view.setTag(moviewViewHolder);
            }
            else{
                moviewViewHolder = (MoviewViewHolder)view.getTag();
            }

            moviewViewHolder.poster.setImageResource(posterID[position]);
            moviewViewHolder.title.setText(posterName[position]);

            //imageview.setImageResource(posterID[position]);

            final int pos = position;
            imageview.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    View dialogView = (View) View.inflate(MainActivity.this,R.layout.dialog,null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);

                    dlg.setIcon(R.drawable.movie);
                    dlg.setTitle(posterName[pos]);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });
            return imageview;
        }
    }
}
