package business_simulator;

public class Business {
    private int production;
    private int productPrice;
    private int productionPrice = 10;
    private int money = 3000;
    private int revenues;
    private int expenses;
    private int income;
    private int demand;
    private int maxDemand = 30;
    private int demandSlope = 1;

    public Business(int productPrice, int production){
        this.productPrice = productPrice;
        this.production = production;
    }

    public void calculate(){
        this.demand = this.maxDemand - this.demandSlope * this.productPrice;
        this.revenues = this.demand * this.productPrice;
        this.expenses = this.production * this.productionPrice;
        this.income = this.revenues - this.expenses;
        this.money += this.income;
    }

    public int getProduction(){
        return this.production;
    }
    public void setProduction(int production){
        this.production = production;
    }
    public int getProductPrice(){
        return this.productPrice;
    }
    public void setProductPrice(int productPrice){
        this.productPrice = productPrice;
    }
}
