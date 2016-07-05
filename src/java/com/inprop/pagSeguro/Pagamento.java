/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.pagSeguro;

  import java.math.BigDecimal;
    import java.net.MalformedURLException;
    import java.net.URL;

    import br.com.uol.pagseguro.domain.AccountCredentials;
    import br.com.uol.pagseguro.domain.Currency;
    import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.Phone;
import br.com.uol.pagseguro.domain.Sender;
    import br.com.uol.pagseguro.domain.ShippingType;
    import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import com.inprop.model.Apoio;
import com.inprop.model.Recompensa;
import com.inprop.model.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ramon
 */
public class Pagamento {
//    private static Pagamento instance;
    public  Pagamento(){}
          private  String referencia;
//        public static Pagamento getInstance(){
//            if(instance == null){
//                instance = new Pagamento();
//             }
//            return instance;
//        }

        public String  setPagamento(Usuario usuario, Recompensa recom)throws NoSuchAlgorithmException{
          //  Usuario usu   = usuario; //usuario;
            //Reconpensa   ap    = apoio;
            //instancia o obj requisicao  de Pagamento
            PaymentRequest requisicaoPagamento = new PaymentRequest();
            // recebe o tipo de moeda
            requisicaoPagamento.setCurrency(Currency.BRL);

            String id        = "cod:";//+recom.getId_recompensa();//id é a string cod+ o id do apoio
            String descricao = recom.getDescricao();
            String a = recom.getValor()+".00";
            Double  val = Double.parseDouble(a);
            BigDecimal valor     = new  BigDecimal(val);
             //adciona o item que vai ser apoiado
                     requisicaoPagamento.addItem(id,descricao ,new Integer(1) , new BigDecimal(a), null, null);
          //         requisicaoPagamento.addItem("0001", "Notebook Prata", new Integer(1), new BigDecimal("0.06"),
            //    new Long(1000), null);

//                    referencia="ref:#"+usuario.getId_usuario()+"#"+recom.getId_recompensa() ;
                    requisicaoPagamento.setReference(referencia);
//                    String nome=usuario.getPerfil().getNome_completo();
//                    String email=usuario.getEmail();
////                    Sender sender = new Sender();
//                    sender.setName(nome);
//                    sender.setEmail(email);
//                    Phone phone = new Phone();
//                    phone.setAreaCode("21");
//                    phone.setNumber("33529351");
//                    sender.setPhone(phone);
//                    requisicaoPagamento.setSender(sender);
//                   // requisicaoPagamento.setSender("thiagoramon", "", "21", "33529351");
                     // requisicaoPagamento.setSender(nome, email, "21", "33529351");
                         //requisicaoPagamento.setSender("Marcio do Couto t", "marciodocouto@yahoo.com.br", "21", "33529351");

                    try {

                    URL paymentURL = requisicaoPagamento.register(new AccountCredentials("contato@thiagoramon.com",
                                "ACB9D758582E4CD39CB19D1A98F09856"));
                               return paymentURL.toString();
                    } catch (PagSeguroServiceException e) {
                               return e.toString();

                    }

            //return null;
        }

}

