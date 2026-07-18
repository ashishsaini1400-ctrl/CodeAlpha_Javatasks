import java.util.*;
import java.io.*;


class Stock {

    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}



class Transaction {

    String type;
    String stockName;
    int quantity;
    double amount;

    Transaction(String type, String stockName, int quantity, double amount) {

        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.amount = amount;
    }


    public String toString() {

        return type + " | " + stockName +
                " | Quantity: " + quantity +
                " | Amount: " + amount;
    }
}




class User {

    String name;
    double balance;

    ArrayList<Stock> portfolio = new ArrayList<>();
    ArrayList<Transaction> history = new ArrayList<>();


    User(String name, double balance) {

        this.name = name;
        this.balance = balance;
    }



    void buy(Stock stock, int quantity) {

        double amount = stock.price * quantity;


        if(balance >= amount) {

            balance -= amount;


            for(int i=0;i<quantity;i++) {

                portfolio.add(stock);
            }


            history.add(new Transaction(
                    "BUY",
                    stock.name,
                    quantity,
                    amount
            ));


            System.out.println("Stock Purchased Successfully");

        }

        else {

            System.out.println("Insufficient Balance");
        }

    }




    void sell(String stockName, int quantity) {


        int count = 0;
        double amount = 0;


        Iterator<Stock> itr = portfolio.iterator();


        while(itr.hasNext() && count < quantity) {

            Stock s = itr.next();


            if(s.name.equals(stockName)) {

                amount += s.price;
                balance += s.price;

                itr.remove();

                count++;
            }
        }



        if(count > 0) {


            history.add(new Transaction(
                    "SELL",
                    stockName,
                    count,
                    amount
            ));


            System.out.println("Stock Sold Successfully");

        }

        else {

            System.out.println("Stock Not Available");
        }

    }





    void showPortfolio() {


        double value = 0;


        System.out.println("\n----- Portfolio -----");


        for(Stock s:portfolio) {


            System.out.println(
                    s.name+" Price: "+s.price
            );


            value += s.price;
        }


        System.out.println("Portfolio Value : "+value);
        System.out.println("Available Balance : "+balance);


        System.out.println("Profit/Loss Tracking : " 
                + (value + balance - 10000));

    }





    void showHistory() {


        System.out.println("\nTransaction History");


        for(Transaction t:history) {

            System.out.println(t);
        }

    }






    void saveData() {


        try {

            FileWriter fw = new FileWriter("portfolio.txt");


            fw.write("User : "+name+"\n");
            fw.write("Balance : "+balance+"\n\n");


            fw.write("Transactions:\n");


            for(Transaction t:history) {

                fw.write(t.toString()+"\n");
            }


            fw.close();


            System.out.println("Portfolio Saved");

        }

        catch(Exception e) {

            System.out.println(e);
        }

    }

}





public class StockTradingPlatform {


    public static void main(String args[]) {


        Scanner sc = new Scanner(System.in);


        ArrayList<Stock> market = new ArrayList<>();


        market.add(new Stock("TATA",350));
        market.add(new Stock("INFOSYS",1500));
        market.add(new Stock("RELIANCE",2500));



        User user = new User("Ashish",10000);



        while(true) {


            System.out.println("\n1. Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Show Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");


            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();



            switch(choice) {


                case 1:


                    System.out.println("\nMarket Stocks");


                    for(Stock s:market) {

                        System.out.println(
                                s.name+" Price: "+s.price
                        );
                    }

                    break;




                case 2:


                    System.out.print("Enter Stock Name: ");
                    String buyName = sc.next();


                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();



                    for(Stock s:market) {


                        if(s.name.equals(buyName)) {

                            user.buy(s,buyQty);
                        }

                    }

                    break;




                case 3:


                    System.out.print("Enter Stock Name: ");
                    String sellName = sc.next();


                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();


                    user.sell(sellName,sellQty);


                    break;




                case 4:

                    user.showPortfolio();

                    break;




                case 5:

                    user.showHistory();

                    break;




                case 6:

                    user.saveData();

                    break;




                case 7:

                    System.exit(0);


            }

        }

    }

}
