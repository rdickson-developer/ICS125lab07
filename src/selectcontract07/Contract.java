/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract07;

/**
 *
 * @author ryandickson
 */
public class Contract {
    
    private String contractID;
    private String originCity;
    private String destCity;
    private String orderItem;
    
    public static final int NUMBER_OF_CONTRACT_ATTRIBUTES = 4;
    public static final int INDEX_OF_CONTRACT_ID = 0;
    public static final int INDEX_OF_ORIGIN_CITY = 1;
    public static final int INDEX_OF_DEST_CITY = 2;
    public static final int INDEX_OF_ORDER_ITEM = 3;
    
    // Constructor
    Contract(String contId, String orgCity, String dstCity, String ordItem){
        contractID = contId;
        originCity = orgCity;
        destCity = dstCity;
        orderItem = ordItem;

    }
    
    // Getters
    public String getContractID(){
        return contractID;
    }
    
    public String getOriginCity(){
        return originCity;
    }
    
    public String getDestCity(){
        return destCity;
    }
    public String getOrderItem(){
        return orderItem;
    }

    // Class methods
    // return true if city matches originCity
    boolean contains(String city) {
        return city.equals(originCity);
    }
}
