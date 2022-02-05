package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public class NewReleasePrice extends Price {
    @Override
    public double getCharge(int dayRented) {
        return dayRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int dayRented) {
        if (dayRented > 1) {
            return 2;
        }else {
            return super.getFrequentRenterPoints(dayRented);
        }
    }
}
