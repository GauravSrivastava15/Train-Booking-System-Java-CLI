package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class  User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId ){
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    public User(){}

    public String getName(){
        return name;
    }

    public String getPassword(){
        return  password;
    }

    public String getHashedPassword(){
        return  hashedPassword;
    };

    public List<Ticket> getTicketsBooked(){
        return this.ticketsBooked;
    }

    public void printTickets(){
        for(int i = 0; i < ticketsBooked.size(); i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    public String getUserId(){
        return this.userId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked =  ticketsBooked;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
}
