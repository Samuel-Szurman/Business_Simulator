package business_simulator;

public class Business {
    private int production;
    private int productPrice;
    private int productionPrice = 10;
    private int money = 3000;
    private int revenues = 0;
    private int expenses = 0;
    private int income = 0;
    private int demand;
    private int maxDemand = 30;
    private int demandSlope = 1;
    private Tab tab;

    public Business(int productPrice, int production, Tab tab){
        this.productPrice = productPrice;
        this.production = production;
        this.tab = tab;
    }

    public void update(){
        int price = tab.getProductionPrice();
        int production = tab.getProduction();
        setProductPrice(price);
        setProduction(production);
        tab.setJl1("Cena: " + price + " ");
        tab.setJl2("Produkcja: " + production + " ");
    }

    public void calculate(){
        this.demand = this.maxDemand - this.demandSlope * this.productPrice;
        this.revenues = this.demand * this.productPrice;
        this.expenses = this.production * this.productionPrice;
        this.income = this.revenues - this.expenses;
        this.money += this.income;
    }

    public void increaseMaxProduction(){
        this.tab.increaseMaxProduction();
    }

    public void decreaseProductionPrice(){
        this.productionPrice -= 1;
    }

    public void increaseMaxDemand(){
        this.maxDemand += 10;
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
