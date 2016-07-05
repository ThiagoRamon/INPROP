/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


clickLinks  = function(){
    var rel;
//    $('.ops a').unbind().click(function(){
//        rel = $(this).attr("rel");
//        alert(rel);
//        var url = null;
//       if(rel.match(/^form/)){
//           url="sistema/page/form/"+rel+".jsp";
//       }else if(rel.match(/^lista/)){
//           url="sistema/page/find/"+rel+".jsp";
//       }else if(rel.match(/^editar/)){
//           var arr=new Array();
//           arr = rel.split(',');
//           url="sistema/page/update/"+arr[0]+".jsp";
//           rel ="0&id="+arr[1];
//       }else if(rel.match(/^Delete/)){
////           var arr=new Array();
//           arr = rel.split(',');
//           url="../"+arr[0];
//           rel ="0&id="+arr[1];
//       }
//
//
//       $.ajax({
////                          scriptCharset: "iso-8895-1" ,
////                          contentType: "application/x-www-form-urlencoded; charset=iso-8895-1",
//          type:"post",
//          data:"page="+rel,
//          url:url,
//          success:function(retorno){
//              $("div[id=corpo]").html(retorno);
//          }
//       });
//                    // alert("0")
//
//    });


 $('a[rel]').unbind().click(function(){
         if($(this).attr("class")!= undefined){
            carregaPagina($(this).attr("rel"),$(this).attr("class"));
         }else{
             carregaPagina($(this).attr("rel"));
         }

 });
                 
}

carregaPagina = function(rel,classe){
        
         if(rel != null){
                   var url = null;
                   if(rel.match(/^form/)){
                       url="sistema/page/form/"+rel+".jsp";
                        $(".search_input").val(rel.replace(/^form_/, ""));
                   }else if(rel.match(/^lista/)){
                       url="sistema/page/find/"+rel+".jsp";
                       $(".search_input").val(rel.replace(/^lista_/, ""));

                   }else if(rel.match(/^editar/)){
                       url="sistema/page/update/"+rel+".jsp";
                  $(".search_input").val(rel.replace(/^editar_/, ""));

                   }else if(rel.match(/^excluir/)){
                       url="sistema/page/delete/"+rel+".jsp";
                       $(".search_input").val(rel.replace(/^Delete/, ""));

                   }else if(rel.match(/^logout/)){
                       url = "../Logout" ;
                   }
                   $("#corpo").html("Carregando...");
                   if(classe != undefined){
                       rel += classe;
                   }
                   $.ajax({
//                          scriptCharset: "iso-8895-1" ,
//                          contentType: "application/x-www-form-urlencoded; charset=iso-8895-1",
                      type:"post",
                      data:"page="+rel,
                      url:url,
                      success:function(retorno){
                          $("#corpo").html(retorno);
                      }
                   });

                }else{

                           }

}
inicioDefault = function(){
   clickLinks();
}

window.onload = function(){
    inicioDefault();

}

