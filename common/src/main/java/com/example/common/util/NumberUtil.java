package com.example.common.util;

/**
 * @Author wangjiaxing
 * @Date 2021/9/30
 */
public class NumberUtil {


    public static String intToBinary32(int i, int bitNum){
        String binaryStr = Integer.toBinaryString(i);
        while(binaryStr.length() < bitNum){
            binaryStr = "0"+binaryStr;
        }
        return binaryStr;
    }
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;


    public static void main(String[] args) {
//        System.out.println("RUNNING   :"+NumberUtil.intToBinary32(RUNNING,32));
//        System.out.println("SHUTDOWN  :"+NumberUtil.intToBinary32(SHUTDOWN,32));
//        System.out.println("STOP      :"+NumberUtil.intToBinary32(STOP,32));
//        System.out.println("TIDYING   :"+NumberUtil.intToBinary32(TIDYING,32));
//        System.out.println("TERMINATED:"+NumberUtil.intToBinary32(TERMINATED,32));
//        System.out.println("CAPACITY  :"+NumberUtil.intToBinary32(CAPACITY,32));
        System.out.println("RUNNING   :"+NumberUtil.intToBinary32(-1,32));
        System.out.println("SHUTDOWN  :"+NumberUtil.intToBinary32(-2,32));
        System.out.println("STOP      :"+NumberUtil.intToBinary32(-3,32));
        System.out.println("TIDYING   :"+NumberUtil.intToBinary32(-4,32));
        System.out.println("TERMINATED:"+NumberUtil.intToBinary32(-5,32));
        System.out.println("CAPACITY  :"+NumberUtil.intToBinary32(-6,32));

    }
}
