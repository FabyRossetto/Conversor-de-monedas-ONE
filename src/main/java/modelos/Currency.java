///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package modelos;
//
import java.util.Map;
//
///**
// *
// * @author Faby
// */
public class Currency {

    private String base_code;
    private Map<String, Double> conversion_rates;

    public Currency(String base_code, Map<String, Double> conversion_rates) {
        this.base_code = base_code;
        this.conversion_rates = conversion_rates;
       
     }
     public Currency(CurrencyER tituloomdb) {
        this.base_code = tituloomdb.base_code();
        this.conversion_rates = tituloomdb.conversion_rates();
    
    }

    public String getBase_code() {
        return base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
     
   
     @Override
    public String toString() {
        return "tipo de cambio: " + base_code + ", taza de conversion: " + conversion_rates ;
    }

    
    
    

}

