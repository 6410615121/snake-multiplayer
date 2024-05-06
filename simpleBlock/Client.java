package simpleBlock;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {
    private Player player;
    private List<Player> players;
    private Board board;

    private Socket socket;
    private ObjectInputStream inObject;
    private ObjectOutputStream outObject;

    public Client(Player player, Socket socket) {
        this.player = player;
        this.socket = socket;

        try {
            inObject = new ObjectInputStream(socket.getInputStream());
            outObject = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Error while trying to create Object...Stream");
            e.printStackTrace();
        }
    }

    public void sentPlayerObjToServer() {
        try {
            outObject.writeUnshared(player);
        } catch (IOException e) {
            System.err.println("Error while trying to sent Player Object");
            e.printStackTrace();
        }
    }

    public void getBoardFromServer(ObjectInputStream in) {
        try {
            this.board = (Board) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error while trying to get Board Object");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void getPlayersFromServer() {
        try {
            this.players = (List<Player>) inObject.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error while trying to get players list");
            e.printStackTrace();
        }
    }

    public void playerMove(String direction){
        if(direction.equalsIgnoreCase("down")){
            Cell current_pos_cell = player.getPositionCell();
            int current_row = current_pos_cell.getRow();
            int current_col = current_pos_cell.getCol();
            
            if(++current_row > board.ROW_SIZE){
                current_row = 0;
            }

            player.setPositionCell(new Cell(current_row, current_col));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

}