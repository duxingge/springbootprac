package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public class RegularPrice extends Price {

    @Override
    public double getCharge(int dayRented) {
        return dayRented > 2 ? (dayRented - 2) * 1.5 : 2;
    }
}
