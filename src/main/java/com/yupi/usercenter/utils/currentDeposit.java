package com.yupi.usercenter.utils;

/**
 * 活期存款
 */
public class currentDeposit {
    private double RateMonth;

    public currentDeposit(double rateMonth) {
        RateMonth = rateMonth;
    }

    public double getRateMonth() {
        return RateMonth;
    }

    public void setRateMonth(double rateMonth) {
        RateMonth = rateMonth;
    }

    public double calculateInterest(int durationMonths, double principalAmount) {
        double interestRate=this.RateMonth;
        // 计算利息
        double interest = (principalAmount * interestRate * durationMonths) / (12 * 100);
        return interest;
    }
}
