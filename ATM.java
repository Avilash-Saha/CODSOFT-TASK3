import java.util.ArrayList;
import java.util.Scanner;

class user{
    String username=null;
    String password=null;
    int balance=0;
    
    user(String username, String password){
        this.username=username;
        this.password=password;
    }
    void deposit(int money){
        balance=balance+money;
    }
    void withdraw(int money){
        balance=balance-money;
    }
    void checkBalance(){
        System.out.println(username + ": Account Balance : " + balance);
    }
    void transfer(user receiver, int amount){
        receiver.balance = receiver.balance + amount;
        this.balance = this.balance - amount;
    }

}

class ATM{
    public static void main(String[] args){
        ArrayList<user> database = new ArrayList<user>();
        while(true){
            int i = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("1:Register || 2:Login || 3: EXIT :");
        int x = sc.nextInt();
        if(x == 3){
            break;
        }
        if(x == 1){
            int flag = 1;
            System.out.println("Enter new username :");
            sc.nextLine();
            
            do{
                flag = 1;
                String username = sc.nextLine();
                for(int k = 0; k < database.size(); k++){
                    if(database.get(k).username.equals(username)){
                        System.out.println("username already exists");
                        System.out.println("Enter new username :");
                        flag = 0;
                        break;
                    }
                }
                if(flag == 0){
                    continue;
                }
                System.out.println("Enter new password :");
                String password = sc.nextLine();
                user newAccount = new user(username, password);
                database.add(newAccount);
                System.out.println("New Account has been created under the username :" + username);
            }while(flag == 0);
            
        }
        else if(x == 2){
            System.out.println("Enter username :");
            sc.nextLine();
            String username = sc.nextLine();
            for(int j = 0; j < database.size(); j++){
                if(database.get(j).username.equals(username)){
                    i = j;
                    break;
                }
            }
            if( i == -1){
                System.out.println("Account not found");
                continue;
            }
            System.out.println("Enter password :");
            String password = sc.nextLine();
            if(!(password.equals(database.get(i).password))){
                System.out.println("Wrong Password");
                continue;
            }
             System.out.println("Withdraw(press 1) || Deposit(press 2) || Transfer(press 3) || checkBalance(press 4) ? :");
             int choice = sc.nextInt();
             if(choice == 1){
                System.out.println("Enter amount :");
                int amt = sc.nextInt();
                if(database.get(i).balance - amt < 0){
                    System.out.println("Not enough balance");
                    continue;
                }
                database.get(i).withdraw(amt);
             }
             else if(choice == 2){
                System.out.println("Enter amount :");
                int amt = sc.nextInt();
                database.get(i).deposit(amt);
             }
             else if(choice == 3){
                System.out.println("Enter recepient's username :");
                sc.nextLine();
                String receiverUsername = sc.nextLine();
                int receiverIndex = -1;
                if(username.equals(receiverUsername)){
                    System.out.println("Error");
                    continue;
                }
                for(int v = 0; v < database.size(); v++){
                    if(database.get(v).username.equals(receiverUsername)){
                        receiverIndex = v;
                        break;
                    }
                }
                if(receiverIndex == -1){
                    System.out.println("recipient not Found");
                    continue;
                }
                System.out.println("Enter amount :");
                int amt = sc.nextInt();
                if(database.get(i).balance - amt < 0){
                    System.out.println("Not enough balance");
                    continue;
                }
                database.get(i).transfer(database.get(receiverIndex),amt);
                
             }
             else if(choice == 4){
                database.get(i).checkBalance();
                continue;
             }
             System.out.println("checkBalance ? Y/N");
             char dispChoice = sc.next().charAt(0);
             if((dispChoice == 'Y') || (dispChoice == 'y')){
                database.get(i).checkBalance();
             }
             
             

        }

        }
    }
}
