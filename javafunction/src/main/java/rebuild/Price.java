package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public abstract class Price {
    public abstract double getCharge(int dayRented);

    public int getFrequentRenterPoints(int dayRented) {
        return 1;
    }
}
