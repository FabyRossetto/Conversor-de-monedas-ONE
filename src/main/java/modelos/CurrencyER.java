///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
// */
package modelos;

import java.util.Map;

/**
 *
 * @author Faby
 */
public record CurrencyER(String base_code, Map<String, Double> conversion_rates) {

}
