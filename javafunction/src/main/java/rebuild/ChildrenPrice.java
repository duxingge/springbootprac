package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public class ChildrenPrice extends Price {

    @Override
    public double getCharge(int dayRented) {
        return dayRented > 3 ? (dayRented - 2) * 1.5 : 1.5;
    }
}
