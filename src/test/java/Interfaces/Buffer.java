package Interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Buffer {

    // Hashmap is used to "store" current value of Price(or any another element atribute) by a specific key on test to allow than a comparison in Assertion.
    //Buffer is cleaned up in every test by a default set up in BaseTest
    public static HashMap<String, String > Buffer = new HashMap<String, String>();

    public static void ClearBuffer(){

        Buffer.clear();
    }

    public static void SetValueInBuffer(String key, String value){

        Buffer.put(key,value);
    }

    public static String  GetValueBufferKey(String key){

      return Buffer.get(key);
    }

    public static int GetActualSize(){

        return Buffer.size();
    }

    public static List<String>  GetValue(){

       List<String> valuesFromHashMap= new ArrayList<>();

       for(String value : Buffer.values()){

            valuesFromHashMap.add(value.trim());

       }

       return  valuesFromHashMap;
    }


}
