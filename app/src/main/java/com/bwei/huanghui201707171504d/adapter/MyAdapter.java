package com.bwei.huanghui201707171504d.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.huanghui201707171504d.R;
import com.bwei.huanghui201707171504d.bean.Goods;

import java.util.HashMap;
import java.util.List;

/**
 * 类注释 :适配器类
 * 创建人：黄慧
 * 创建时间： 2017/7/17.9:45
 */

public class MyAdapter extends BaseAdapter {
    private List<Goods> goods;
    private Context context;
    private int num = 0; //数量

    private static HashMap<Integer, Boolean> isSelected;
    private onCheckListener listener;
    private CheckBox check;


    public MyAdapter(List<Goods> goods, Context context) {
        super();
        this.goods = goods;
        this.context = context;


        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < goods.size(); i++) {
            getIsSelected().put(i, false);
        }
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return goods.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return goods.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Goods goodss = goods.get(position);
        if (convertView == null) {
            //加载布局
            convertView = View.inflate(context, R.layout.list_itme, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.money = (TextView) convertView.findViewById(R.id.tv_money);
            holder.flag = (CheckBox) convertView.findViewById(R.id.cb_checkbox);
            holder.btAdd = (Button) convertView.findViewById(R.id.addbt);
            holder.btReduce = (Button) convertView.findViewById(R.id.subbt);
            holder.edtNumber = (EditText) convertView.findViewById(R.id.edt);
            convertView.setTag(holder);
        }
        //进行赋值
        holder = (ViewHolder) convertView.getTag();
        holder.title.setText(goodss.getTitle());
        holder.money.setText(goodss.getMoney() + "");
        holder.flag.setChecked(goodss.isFlag());
        holder.btAdd.setTag("+");
        holder.btReduce.setTag("-");
        //设置输入类型为数字
        holder.edtNumber.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        holder.edtNumber.setText(String.valueOf(num));
        final ViewHolder finalHolder = holder;
        /**
         * 加减按钮事件监听器
         */
        holder.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numString = finalHolder.edtNumber.getText().toString();
                if (numString == null || numString.equals("")) {
                    num = 0;
                    finalHolder.edtNumber.setText("0");
                } else {
                    if (view.getTag().equals("-")) {
                        if (++num < 0) //先加，再判断
                        {
                            num--;
                            Toast.makeText(context, "请输入一个大于0的数字",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            finalHolder.edtNumber.setText(String.valueOf(num));
                        }
                    } else if (view.getTag().equals("+")) {
                        if (--num < 0) //先减，再判断
                        {
                            num++;
                            Toast.makeText(context, "请输入一个大于0的数字",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            finalHolder.edtNumber.setText(String.valueOf(num));
                        }
                    }
                }
            }
        });
        /**
         * 加减按钮事件监听器
         */
        holder.btReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numString = finalHolder.edtNumber.getText().toString();
                if (numString == null || numString.equals("")) {
                    num = 0;
                    finalHolder.edtNumber.setText("0");
                } else {
                    if (view.getTag().equals("-")) {
                        if (++num < 0) //先加，再判断
                        {
                            num--;
                            Toast.makeText(context, "请输入一个大于0的数字",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            finalHolder.edtNumber.setText(String.valueOf(num));
                        }
                    } else if (view.getTag().equals("+")) {
                        if (--num < 0) //先减，再判断
                        {
                            num++;
                            Toast.makeText(context, "请输入一个大于0的数字",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            finalHolder.edtNumber.setText(String.valueOf(num));
                        }
                    }
                }
            }
        });
        /**
         * EditText输入变化事件监听器
         */
      holder.edtNumber.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void afterTextChanged(Editable s) {
              String numString = s.toString();
              if (numString == null || numString.equals("")) {
                  num = 0;
              } else {
                  int numInt = Integer.parseInt(numString);
                  if (numInt < 0) {
                      Toast.makeText(context, "请输入一个大于0的数字",
                              Toast.LENGTH_SHORT).show();
                  } else {
                      //设置EditText光标位置 为文本末端
                      finalHolder.edtNumber.setSelection(finalHolder.edtNumber.getText().toString().length());
                      num = numInt;
                  }
              }
          }
      });

        // 根据isSelected来设置checkbox的选中状况
        holder.flag.setChecked(getIsSelected().get(position));
        holder.flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                }
                listener.onCheck(isSelected);
            }


        });

        return convertView;
    }


    static class ViewHolder {
        public TextView title;
        public TextView money;
        public CheckBox flag;
        public Button btAdd, btReduce;
        public EditText edtNumber;
    }


    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MyAdapter.isSelected = isSelected;
    }

    public void setListener(onCheckListener listener) {
        this.listener = listener;
    }

    public interface onCheckListener {
        void onCheck(HashMap<Integer, Boolean> map);
    }

}
