<%--
    Document   : main
    Created on : 17/07/2012, 23:51:08
    Author     : Ramon
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">



<c:if test="${(empty usuario)}" >
    <c:redirect url='${header["host"]} ${pageContext.request.contextPath}'/>
</c:if>
<c:if test="${(usuario == null)}" >
    <c:redirect url="../..${pageContext.request.contextPath}"/>
</c:if>
<c:if test="${usuario.tipo != \"ADM\" }">
    <c:redirect url="${pageContext.request.contextPath}"/>
</c:if>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <title>Painel Administrativo TR.DEV</title>
        <link rel="stylesheet" type="text/css" href="sistema/style/css/style.css" />
        <script type="text/javascript" src="http://${header['host']}${pageContext.servletContext.contextPath}/extranet/sistema/js/jquery.js"></script>
        <script type="text/javascript" src="sistema/js/ddaccordion.js"></script>
        <script src="../site/sistema/script/js/JModal.js"></script>

        <script type="text/javascript">
        ddaccordion.init({
              headerclass: "submenuheader", //Shared CSS class name of headers group
                contentclass: "submenu", //Shared CSS class name of contents group
               // revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
                mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
                collapseprev: true, //Collapse previous content (so only one open at any time)? true/false
                defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
                onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
                animatedefault: false, //Should contents open by default be animated into view?
                persiststate: true, //persist state of opened contents within browser session?
                toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
                togglehtml: ["suffix", "<img src='sistema/midia/images/plus.gif' class='statusicon' />", "<img src='sistema/midia/images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
                animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
                oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
                        //do nothing
                },
                onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
                        //do nothing
                }
        });
        </script>
        <script language="javascript" type="text/javascript" src="sistema/js/default.js"></script>
<script>

    function alerta(mensagem){
                $('#alerta').hide();
                if(mensagem != null || mensagem != ''){
                    $('#alerta').show();
                    $('#alerta').append("<span style=color:red;>"+mensagem+"</span>");
                    setTimeout(function(){
                                       $('#alerta').hide();
                                       },5000);
                }
            }


             funcClick = function(){
                
                  $("div[id=view]").live( 'click',function(){


                  var rel= $(this).attr('rel');
                  var pagina = rel.match(/form_[a-z A-Z]{1,100}/);
                  var pagina = rel.match(/form_[a-z A-Z]{1,100}/);
                       var x=1000;
                       var y=500;

                       if(rel.match(/view_posposta/)){
                           x = 900;
                           y = 500;
                       }
                       JModal.start("sistema/page/find/"+rel, x, y, "cdx");
                       JModal.close('info',0);
                  });

            }

       $(function(){
          funcClick();
          alerta('${mensagem}');
           

       });

</script>
          <% 
              request.getSession().setAttribute("mensagem", null);
              request.getSession().setAttribute("pagina", null);
           %>

</head><body>
<div id="main_container">

	<div class="header">
    <div class="logo">
        <a href="#"><img src="sistema/midia/images/logo.gif" alt="" title="" border="0" /></a>
    </div>
    <div class="right_header">
        Seja bem-vindo! <span class="emailUsuario"></span>, <a href="#">Visit site</a> | <a href="#" class="messages">(<span class="quantMSG"></span>) Messages</a> | <a href="#" class="logout"  rel="logout">Logout</a></div>
    <div id="clock_a"></div>
    </div>

    <div class="main_content">

<!-- menu topo -->

    <div class="center_content">



    <div class="left_content">
     
             <!-- menu left -->
             <jsp:include page="sistema/page/menu/left_menu.jsp"/>

    </div>

    <div class="right_content">
   
        <div id="alerta"></div>
        <div id="corpo"></div>
     </div><!-- end of right content-->
  </div>   <!--end of center content -->



    <div class="clear"></div>
    </div> <!--end of main content-->


    <div class="footer">

    	<div class="left_footer">Painel Administrativo TRDEV| criado por  <a href="http://indeziner.com">TR.DEV</a></div>
    	<div class="right_footer"><a href="http://indeziner.com"><img src="sistema/midia/images/indeziner_logo.gif" alt="" title="" border="0" /></a></div>

    </div>

</div>
</body>
</html>