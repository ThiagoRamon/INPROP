<%--
    Document   : index
    Created on : 28/04/2012, 23:27:12
    Author     : Ramon
--%>

<!--[if IE 7 ]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8 oldie"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <!--<![endif]-->


<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
              <script src="sistema_0/js/jquery.min.js"></script>
    <script src="sistema_0/js/jquery.smoothscroll.js"></script>
    <script src="sistema_0/js/jquery.nivo.slider.pack.js"></script>
    <script src="sistema_0/js/jquery.easing-1.3.pack.js"></script>
    <script src="sistema_0/js/jquery.fancybox-1.3.4.pack.js"></script>
    <script src="sistema_0/js/init.js"></script>

     <link rel="stylesheet" type="text/css" href="sistema_0/subModal/style.css" />
   <link rel="stylesheet" type="text/css" href="sistema_0/subModal/subModal.css" />
   <script type="text/javascript" src="sistema_0/subModal/common.js"></script>
   <script type="text/javascript" src="sistema_0/subModal/subModal.js"></script>
   <script>

   window.onload = function(){
             var contato = document.getElementById("contato");
             contato.onclick = function(){
                  //alert();
                  showPopWin( formulario , 500, 500, null);
             }
       }

   </script>

        <title>INPROP 0.1 BETA</title>

    <!------------------------------------------------------------------------>
            <link href="sistema_0/css/kck_css/style.css" media="screen" rel="stylesheet" type="text/css">
            <link href="sistema_0/css/kck_css/compass.css" media="screen" rel="stylesheet" type="text/css">
            <link href="sistema_0/css/kck_css/visao_um.css" media="screen" rel="stylesheet" type="text/css">
            <link href="sistema_0/css/kck_css/views_two.css" media="screen" rel="stylesheet" type="text/css">
            <link href="sistema_0/css/kck_css/views_three.css" media="screen" rel="stylesheet" type="text/css">
            <link href="sistema_0/css/kck_css/views_four.css" media="screen" rel="stylesheet" type="text/css">
            <link href="sistema_0/css/kck_css/MyFontsWebfontsKit.css" media="screen" rel="stylesheet" type="text/css">

    <!------------------------------------------------------------------------>

        <!------------------------------------------------------------------------->
            <link rel="stylesheet" href="sistema_0/css/style.css" type="text/css" media="screen" />
            <link rel="stylesheet" href="sistema_0/css/nivo-slider.css" type="text/css" />
            <link rel="stylesheet" href="sistema_0/css/jquery.fancybox-1.3.4.css" type="text/css" />
     <!------------------------------------------------------------------------>

    <!--[if lt IE 9]>
	    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
    <![endif]-->

<script src="sistema_0/js/jquery-1.6.1.min.js"></script>
<script src="sistema_0/js/base.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery-1.6.1.min.js"><\/script>')</script>

<%
String    msg="";
      if(session.getAttribute("msg")!=null){
          msg=(String)session.getAttribute("msg");
           msg="<script> window.setTimeout(\"showPopWin('"+msg+" ', 500, 500, null)\",1000);" +
                        "window.setTimeout(\"hidePopWin(false)\",3000)  </script>";
           out.println(msg);
          session.removeAttribute("msg");
      }else{
          msg="";
      }

%>
</head>


<style>
    #container_main{
        width: 1000px;
        margin: auto;

    }
</style>
<body>

<!-- header-wrap -->

   <!--menu -->
    <jsp:include page="sistema_0/page/menu/menu.jsp" />
   <!--fim menu -->
<!-- content-wrap -->
<div class="content-wrap">
    <div id="container1">

    <!-- about us -->
          <section id="about-us" class="clearfix">
                <h1>Formulário de Cadastro.</h1>
                            <div class="primary">

                                <jsp:include page="sistema_0/page/cadastro_usuario.jsp"/>

                            </div>

                            <aside>

                                    <!--<h2>SAIBA MAIS</h2>

                                    <p>você também podera esta sabendo tudo sobre
                                       INPROP baixando tudo para seu computador
                                       em PDF.</p>


                                    <a href="#" class="download-btn">Projetos</a>


                                    <h2>Links</h2>


                                    <ul class="link-list">
                                        <li><a href="http://themeforest.net?ref=ealigam" title="Site Templates">Themeforest</a></li>
                                        <li><a href="http://www.4templates.com/?go=228858961" title="Website Templates">4Templates</a></li>
                                        <li><a href="http://www.dreamhost.com/r.cgi?287326|sshout" title="Webhosting">Dreamhost</a></li>
                                        <li><a href="http://store.templatemonster.com?aff=ealigam" title="Web Templates">Templatemonster</a></li>
                                    </ul>
-->

                                    <h2>LOGIN   </h2>

                                    <div class="testimonials">
                                     <form  method='post' action='sistema_0/controle/logar.jsp' name='form_login' >                                                          
                      <table style='margin:auto;'>                                
                         <tr>                                                     
                           <td>E-mail*</td>                                       
                         </tr>                                                    
                         <tr>                                                     
                          <td><input type='text' name='email' size='50' /></td>
                         </tr>                                                    
                         <tr>                                                     
                          <td>Senha*</td>                                        
                         </tr>                                                    
                         <tr>                                                     
                          <td><input type='password' name='senha'size='50' /></td>
                         </tr>                                                    
                         <tr>                                                     
                            <td colspan='5' ><input style='float:right;' name='botao' type='submit' value='Entrar' /></td>    
                         </tr>                                                      
                         <tr>                                                       
                          <td></td>    
                         </tr>                                                   
                    </table></form>

                                            <cite></cite>
                                        </blockquote>
                                    </div>

                            </aside>



                            <a class="back-to-top" href="#main">Back to Top</a>

                      </section>

                      </div>

                <!-- footer -->
                <footer>
                    <jsp:include page="sistema_0/page/menu/menu_rodape.jsp"/>
                </footer>

</div>

</body>
</html>

