package simpleBlock;

import java.io.Serializable;

public class Player implements Serializable{
    private String playerID;
    private String color;
    private Cell positionCell;

    public Player(String playerID, String color, Cell positionCell) {
        this.playerID = playerID;
        this.color = color;
        this.positionCell = positionCell;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Cell getPositionCell() {
        return positionCell;
    }

    public void setPositionCell(Cell positionCell) {
        this.positionCell = positionCell;
    }

    @Override
    public String toString() {
        int col = positionCell.getCol();
        int row = positionCell.getRow();

        String message = String.format("PlayerID: %s, row,col: %d, %d", playerID, row, col);
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player){
            Player playerObj = (Player) obj;
            String playerObj_ID = playerObj.getPlayerID();

            if(this.getPlayerID().equals(playerObj_ID)){
                // System.out.println("player id equal");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isSamePos(Player otherPlayer){
        Cell thisCell = this.getPositionCell();
        Cell otherCell = otherPlayer.getPositionCell();
        
        System.out.println("This cell: " + thisCell);
        System.out.println("Other cell: " + otherCell);
        
        return thisCell.equals(otherCell);
    }
    
    

}
