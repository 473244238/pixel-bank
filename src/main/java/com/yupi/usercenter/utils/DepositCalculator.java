package com.yupi.usercenter.utils;
public class DepositCalculator {

    // 利率成员变量
    private double rate3Months;
    private double rate6Months;
    private double rate1Year;
    private double rate2Years;
    private double rate3Years;
    private double rate5Years;

    // 构造函数，用于初始化利率
    public DepositCalculator(double rate3Months, double rate6Months, double rate1Year,
                             double rate2Years, double rate3Years, double rate5Years) {
        this.rate3Months = rate3Months;
        this.rate6Months = rate6Months;
        this.rate1Year = rate1Year;
        this.rate2Years = rate2Years;
        this.rate3Years = rate3Years;
        this.rate5Years = rate5Years;
    }

    // 计算利息的方法
    public double calculateInterest(int durationMonths, double principalAmount) {
        double interestRate;

        // 根据存款期限设置利率
        switch (durationMonths) {
            case 3:
                interestRate = rate3Months;
                break;
            case 6:
                interestRate = rate6Months;
                break;
            case 12:
                interestRate = rate1Year;
                break;
            case 24:
                interestRate = rate2Years;
                break;
            case 36:
                interestRate = rate3Years;
                break;
            case 60:
                interestRate = rate5Years;
                break;
            default:
                // 默认情况或处理其他期限
                interestRate = 0.0;
        }

        // 计算利息
        double interest = (principalAmount * interestRate * durationMonths) / (12 * 100);
        return interest;
    }
}