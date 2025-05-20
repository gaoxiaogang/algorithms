package org.gxg.app.sort;

public class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public Date(int d, int m, int y) {
        day = d;
        month = m;
        year = y;
    }

    /**
     *
     * @param that the object to be compared.
     * @return +1 说明比 that 大
     *         -1 说明比 that 小
     *         0  说明和 that 一样大
     */
    public int compareTo(Date that) {
        if (this.year > that.year) return +1;
        if (this.year < that.year) return -1;

        if (this.month > that.month) return +1;
        if (this.month < that.month) return -1;

        if (this.day > that.day) return +1;
        if (this.day < that.day) return -1;

        return 0;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static void main(String[] args) {
        Date thisDate = new Date(19, 5, 2025);
        Date thatDate = new Date(21, 5, 2025);

        int result = thisDate.compareTo(thatDate);
        if (result > 0) {
            System.out.println(thisDate + " 大于 " + thatDate);
        } else if (result < 0) {
            System.out.println(thisDate + " 小于 " + thatDate);
        } else {
            System.out.println(thisDate + " 等于 " + thatDate);
        }
    }
}
