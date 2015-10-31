package com.example.student.study;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 *非同期処理
 */

public class AsyncActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        final TextView texthello = (TextView)findViewById(R.id.texthello);


        Thread  th = new Thread(new Runnable(){
            public void run(){
                texthello.setText("helloaoao");
            }
        });

        th.start();





    }

    /**
     * AsyncTackクラス *Toastテスト
     */
    class HelloAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            String param = params[0];

            int sum = 0;

            for(int i=0; i<100; i++){
                sum +=i;
            }

            param += sum;
            return param;
        }

       @Override
        protected void onPostExecute(String result){
            Toast.makeText(AsyncActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * スレッドクラス Toastテスト用
     */
    class HelloThread implements Runnable{

        @Override
        public void run() {

            HelloAsyncTask task = new HelloAsyncTask();
            task.execute("Hello Worlds");
            /*
            Handler handler  = new Handler();

            handler.post(new Runnable(){
                public void run(){

                    Toast.makeText(AsyncActivity.this, "async", Toast.LENGTH_LONG).show();

                }
            });
            */
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sync, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
