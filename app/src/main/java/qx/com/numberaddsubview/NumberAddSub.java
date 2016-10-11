package qx.com.numberaddsubview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/26.
 */
public class NumberAddSub extends LinearLayout implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Button mAddBtn;
    private Button mSubBtn;
    private TextView mNumTv;

    private int value;
    private int minValue;
    private int maxValue;

    private OnButtonClickListener mOnButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.mOnButtonClickListener=onButtonClickListener;
    }



    public interface OnButtonClickListener{
        void OnButtonAddClick(View view,int value);
        void OnButtonSubClick(View view,int value);
    }

    public int getValue() {
        String val=mNumTv.getText().toString();
        if (val !=null && !"".equals(val)){
            this.value=Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        mNumTv.setText(value+"");
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public NumberAddSub(Context context) {
        this(context,null);
    }

    public NumberAddSub(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumberAddSub(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater=LayoutInflater.from(context);
        initView();
        if (attrs != null){
            TintTypedArray tta=TintTypedArray.obtainStyledAttributes(
                    context,attrs,R.styleable.NumberAddSub,defStyleAttr,0);

            int value=tta.getInt(R.styleable.NumberAddSub_value,0);
            setValue(value);

            int minValue=tta.getInt(R.styleable.NumberAddSub_minvalue,0);
            setMinValue(minValue);

            int maxValue=tta.getInt(R.styleable.NumberAddSub_maxvalue,0);
            setMaxValue(maxValue);

            Drawable addBtnDrawable=tta.getDrawable(R.styleable.NumberAddSub_addBtnBg);
            setAddBtnBackground(addBtnDrawable);

            Drawable subBtnDrawable=tta.getDrawable(R.styleable.NumberAddSub_SubBtnBg);
            setSubBtnBackground(subBtnDrawable);

            int numTvDrawable =tta.getResourceId(R.styleable.NumberAddSub_numBtnBg,android.R.color.transparent);
            setmNumTvBackground(numTvDrawable);

            tta.recycle();//回收
        }
    }

    private void setAddBtnBackground(Drawable drawable){
        this.mAddBtn.setBackground(drawable);
    }
    private void setSubBtnBackground(Drawable drawable){
        this.mSubBtn.setBackground(drawable);
    }
    private void setmNumTvBackground(int drawableId){
        this.mNumTv.setBackgroundResource(drawableId);
    }

    private void initView(){
        View view=mInflater.inflate(R.layout.wight_number_add_sub,this,true);
        mAddBtn= (Button) view.findViewById(R.id.add_btn);
        mSubBtn= (Button) view.findViewById(R.id.sub_btn);
        mNumTv= (TextView) view.findViewById(R.id.num_tv);

        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_btn){//点击+
            numAdd();
            if (mOnButtonClickListener !=null){
                mOnButtonClickListener.OnButtonAddClick(v,value);
            }
        }else if(v.getId()==R.id.sub_btn){//点击-
            numSub();
            if (mOnButtonClickListener !=null){
                mOnButtonClickListener.OnButtonSubClick(v,value);
            }
        }
    }

    private void numAdd(){
        if (value < maxValue){
            value++;
        }
        mNumTv.setText(value+"");
    }

    private void numSub(){
        if (value > minValue){
            value--;
       }
        mNumTv.setText(value+"");
    }
}
