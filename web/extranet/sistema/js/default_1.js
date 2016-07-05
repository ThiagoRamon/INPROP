/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

clickLinks  = function(){
    var rel;
    $('a').unbind().click(function(){
        rel = $(this).attr("rel");
        var url = null;
       if(rel.match(/^form/)){
           url="sistema/page/form/"+rel+".jsp";
       }else if(rel.match(/^find/)){
           url="sistema/page/find/"+rel+".jsp";
       }else if(rel.match(/^editar/)){
           var arr=new Array();
           arr = rel.split(',');
           url="sistema/page/update/"+arr[0]+".jsp";
           rel ="0&id="+arr[1];
       }else if(rel.match(/^Delete/)){
           var arr=new Array();
           arr = rel.split(',');
           url="../"+arr[0];
           rel ="0&id="+arr[1];
       }


       $.ajax({
//                          scriptCharset: "iso-8895-1" ,
//                          contentType: "application/x-www-form-urlencoded; charset=iso-8895-1",
          type:"post",
          data:"page="+rel,
          url:url,
          success:function(retorno){
              $("div[class=center_content]").html(retorno);
          }
       });
                    // alert("0")

    });

}
inicioDefault = function(){
   clickLinks();
}

window.onload = function(){
    inicioDefault();

}

