package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService
{
    private User user;
    private List<User> userList;
    private  ObjectMapper objectMapper = new ObjectMapper();  // To map the objects from list of users we use jackson object mapper
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService(User user) throws IOException
    {
        this.user = user;
        loadUser();
    }

    public UserBookingService() throws IOException{
        loadUser();
    }

    public void loadUser() throws IOException{
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch(IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    // json ---> Object (User) -------> Deserialization (reading)
    // Object (User) -----> json ----> Serialization    (writing)

    public void fetchBooking(){
        user.printTickets();
    }

    public Boolean cancelTicket(String ticketId){
        // Todo HW
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination) {
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrain(source, destination);
        }catch(IOException ex){
            return null;
        }
    }

    public List<List<Integer>> fetchSeats(Train trainSelected){
        return trainSelected.getSeats();
    }

    public boolean bookTicket(Train train, int row, int col){
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if(row >= 0 && row < seats.size() && col >= 0 && col < seats.get(row).size()){
                if(seats.get(row).get(col) == 0){
                    seats.get(row).set(col, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch (IOException ex){
            return false;
        }
    }
}
