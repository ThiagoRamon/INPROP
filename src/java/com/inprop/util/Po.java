/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.util;

/**
 *
 * @author Ramon
 */

import java.math.BigDecimal;
import java.net.URL;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Currency;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;




public class Po {
     public String pppp;
     public Po(){}
      public void pg() {

        // Instantiate a new payment request
        PaymentRequest paymentRequest = new PaymentRequest();

        // Sets the currency
        paymentRequest.setCurrency(Currency.BRL);

        // Add an item for this payment request
        paymentRequest.addItem("0001", "Notebook Prata", new Integer(1), new BigDecimal("0.06"),
                new Long(1000), null);

        // Add another item for this payment request
       // paymentRequest.addItem("0002", "Notebook Rosa", new Integer(2), new BigDecimal("2560.00"),
         //       new Long(750), null);

        // Sets a reference code for this payment request, it's useful to identify this payment in future notifications.
        paymentRequest.setReference("REF1234");

        // Sets shipping information for this payment request
        paymentRequest.setShippingType(ShippingType.SEDEX);
        paymentRequest.setShippingAddress("BRA", "SP", "São Paulo", "Jardim Paulistano", "01452002",
                "Av. Brig. Faria Lima", "1384", "5o andar");

        // Sets your customer information.
        paymentRequest.setSender("João Comprador", "comprador@uol.com.br", "11", "56273440");

        try {

            URL paymentURL = paymentRequest.register(new AccountCredentials("contato@thiagoramon.com",
                    "ACB9D758582E4CD39CB19D1A98F09856"));

         this.pppp =    paymentURL.toString();
        } catch (PagSeguroServiceException e) {
         
        }


    }


}
