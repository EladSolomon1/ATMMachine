package sample.model;

public class User {

    private  String firstName;
    private  String lastName;
    private String password;
    private String email;
    private String gender;
    private int customer_number;
    private double balance;

    public User() {
    }

    public User(String firstName,String lastName,String password,String email,String gender){
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.email=email;
        this.gender=gender;
        //this.balance=0;
    }
    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public int getCustomer_number() { return customer_number; }

    public void setCustomer_number(int customer_number) { this.customer_number = customer_number; }

    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

}


