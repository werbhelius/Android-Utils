/**
 * 一个关于获取屏幕像素相关参数及 View 宽高的工具类
 * 采用单例模式实现，同样也可以自定义 Application 传入 Context 用静态类实现
 *
 * Created by wanbo on 2017/4/6.
 */

public class ScreenPixelUtils {

    private static ScreenPixelUtils mInstance = null;
    private Context context;

    public static ScreenPixelUtils getInstance(Context c) {
        if (mInstance == null) {
            synchronized (ScreenPixelUtils.class) {
                if (mInstance == null) {
                    mInstance = new ScreenPixelUtils(c);
                }
            }
        }
        return mInstance;
    }

    private ScreenPixelUtils(Context c) {
        this.context = c;
    }

    /**
     * dp to px
     * @param dpValue dp
     * @return px
     */
    public int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取屏幕密度
     * @return density
     */
    public int getDensity() {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density + 0.5f);
    }

    /**
     * px to dp
     * @param pxValue px
     * @return dp
     */
    public int px2dp(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 判断是否是低分辨率
     * @return true or false
     */
    public boolean isLowDesity() {
        return context.getResources().getDisplayMetrics().widthPixels < 1080;
    }

    /**
     * 获取当前设备屏幕宽，单位是 px
     * @return px
     */
    public int getWidthPixels(){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Configuration cf = context.getResources().getConfiguration();
        int ori = cf.orientation;
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
            return displayMetrics.heightPixels;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {// 竖屏
            return displayMetrics.widthPixels;
        }
        return 0;
    }

    /**
     * 获取当前设备屏幕高，单位是 px
     * @return px
     */
    public int getHeightPixels(){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Configuration cf = context.getResources().getConfiguration();
        int ori = cf.orientation;
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
            return displayMetrics.widthPixels;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {// 竖屏
            return displayMetrics.heightPixels;
        }
        return 0;
    }

    /**
     * 将资源文件中定义的dp值乘以屏幕密度
     * getDimension() float 返回实际数值
     * getDimensionPixelSize() int 返回的是实际数值的四舍五入
     * getDimensionPixelOffset() int 返回的是实际数值去掉后面的小数点
     * @param resId id
     * @return px
     */
    public int getDimen(int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取控件宽
     * @param view view
     * @return width
     */
    public int getWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredWidth());
    }

    /**
     * 获取控件高
     * @param view view
     * @return height
     */
    public int getHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredHeight());
    }

    /**
     * 获取一个字的宽
     * @param textView textView
     * @return width
     */
    public float getTextWidth(TextView textView) {
        Paint mPaint = textView.getPaint();
        String str = textView.getText().toString();
        return mPaint.measureText(str) / str.length();
    }
}
