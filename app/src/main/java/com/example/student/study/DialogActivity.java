package com.example.student.study;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class DialogActivity extends BaseActivity {

    TextView label = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    // スーパークラスのonCreateメソッド呼び出し
    super.onCreate(savedInstanceState);
    // レイアウト設定ファイルの指定
    setContentView(R.layout.activity_dialog);

    // 表示ラベル取得
    label = (TextView)findViewById(R.id.label_dialogtext);

    // 通常ダイアログ表示ボタンのクリックリスナー設定
    Button dialogBtn = (Button)findViewById(R.id.dialogButton);
    dialogBtn.setTag("dialog");
    dialogBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showDialog();
        }
    });

    // テキストダイアログ表示ボタンのクリックリスナー設定
    Button txtDialogBtn = (Button)findViewById(R.id.textDialogButton);
    txtDialogBtn.setTag("textDialog");
    txtDialogBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showTextDialog();
        }
    });

    // 単一選択ダイアログ表示ボタンのクリックリスナー設定
    Button SingleSelectDialogBtn = (Button)findViewById(R.id.singleSelectDialogButton);
    SingleSelectDialogBtn.setTag("singleSelectDialog");
    SingleSelectDialogBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showSingleSelectDialog();
        }
    });

    // 日付選択ダイアログ表示ボタンのクリックリスナー設定
    Button DatePickerDialogBtn = (Button)findViewById(R.id.datePickerDialogButton);
    DatePickerDialogBtn.setTag("datePickerDialogBtn");
    DatePickerDialogBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showDatePickerDialog();
        }
    });

    // 時間選択ダイアログ表示ボタンのクリックリスナー設定
    Button TimePickerDialogBtn = (Button)findViewById(R.id.timePickerDialogButton);
    TimePickerDialogBtn.setTag("timePickerDialogBtn");
    TimePickerDialogBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showTimePickerDialog();
        }
    });

    // プログレスバーダイアログ表示ボタンのクリックリスナー設定
    Button ProgressDialogBtn = (Button)findViewById(R.id.progressDialogButton);
    ProgressDialogBtn.setTag("progressDialogBtn");
    ProgressDialogBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showProgressDialog();
        }
    });

}


    // 通常ダイアログの表示
    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DialogActivity.this);
        dialog.setTitle("通常ダイアログ");
        dialog.setMessage("選択してください。");
        dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
                label.setText("通常ダイアログ：OKが選択されました。");
            }
        });
        dialog.setNegativeButton("NG", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
                label.setText("通常ダイアログ：NGが選択されました。");
            }
        });
        dialog.show();
    }

    // テキストダイアログの表示
    public void showTextDialog() {
        final EditText editText=new EditText(DialogActivity.this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(DialogActivity.this);
        dialog.setTitle("テキストダイアログ");
        dialog.setMessage("テキストを入力してください。");
        dialog.setView(editText);
        dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
                label.setText("テキストダイアログ：" + editText.getText().toString() + "が入力されました。");
            }
        });

        dialog.show();
        /*
        Dialog dialog = new Dialog(DialogActivity.this, "テキストダイアログ", "テキストを入力してください");
        dialog.setEditText(editText);
        dialog.setPositiveButtonListener("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
                label.setText("テキストダイアログ：" + editText.getText().toString() + "が入力されました。");
            }
        });
        */


    }

// 単一選択ダイアログの表示
final String[] items = new String[]{"もも","うめ","さくら"};
int which = 0;
    public void showSingleSelectDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DialogActivity.this);
        dialog.setTitle("単一選択ダイアログ");
        dialog.setSingleChoiceItems(items
                ,0
                ,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        which = whichButton;
                    }
                }
        );

        dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
                String selected = items[which];
                label.setText("単一選択ダイアログ：" + selected + "が入力されました。");
            }
        });
        dialog.show();
    }

    // 日付選択ダイアログの表示
    public void showDatePickerDialog() {
        Calendar cal = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(DialogActivity.this
                ,new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker picker, int year, int month, int day) {
                label.setText("日付選択ダイアログ：" + year + "年" + (month+1) + "月" + day + "日");
            }
        }
                ,cal.get(Calendar.YEAR)
                ,cal.get(Calendar.MONTH)
                ,cal.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    // 時間選択ダイアログの表示
    public void showTimePickerDialog() {
        TimePickerDialog dialog = new TimePickerDialog(DialogActivity.this
                ,new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker picker, int hour, int min) {
                label.setText("時間選択ダイアログ：" + hour + "時" + min + "分");
            }
        }
                ,0
                ,0
                ,true
        );
        dialog.show();
    }

// プログレスバーダイアログの表示
ProgressDialog dialog;
    public void showProgressDialog() {
        dialog = new ProgressDialog (DialogActivity.this);
        dialog.setTitle("プログレスバーダイアログ");
        dialog.setMessage("しばらくお待ちください・・");
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMax(100);
        dialog.setCancelable(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            }
        });

        dialog.show();
        new Thread(new Runnable(){
            public void run(){
                try{
                    for(int i=0; i< dialog.getMax(); i++){
                        dialog.setProgress(i);
                        Thread.sleep(100);
                    }
                }catch(Exception e){

                }
                dialog.dismiss();
            }
        }).start();
    }
}
