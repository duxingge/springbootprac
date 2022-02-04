package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public String getTitle() {
        return _title;
    }

    public void setPriceCode(int priceCode) {
        _priceCode = priceCode;
    }

    public double getCharge(int dayRented) {
        double thisAmount = 0;
        switch (getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (dayRented > 2)
                    thisAmount += (dayRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += dayRented * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (dayRented > 3)
                    thisAmount += (dayRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public int getFrequentRenterPoints(int dayRented) {
        int frequentRenterPoints = 0;
        // add frequent renter points
        frequentRenterPoints++;
        // add bonus for a two day new release rental
        if ((getPriceCode() == Movie.NEW_RELEASE)
                && dayRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}

