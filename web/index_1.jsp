<%-- 
    Document   : index
    Created on : 28/04/2012, 23:27:12
    Author     : Ramon
--%>

<!--[if IE 7 ]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8 oldie"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html> <!--<![endif]-->

<%@page import="com.inprop.model.*" %>
<%@page import="com.inprop.persistence.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

   <title>INPROP 0.1 BETA</title>

     <link rel="stylesheet" type="text/css" href="sistema_0/subModal/style.css" />
   <link rel="stylesheet" type="text/css" href="sistema_0/subModal/subModal.css" />
   <script type="text/javascript" src="sistema_0/subModal/common.js"></script>
   <script type="text/javascript" src="sistema_0/subModal/subModal.js"></script>

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
 
  


    <script>window.jQuery || document.write('<script src="js/jquery-1.6.1.min.js"><\/script>')</script>

    <script src="sistema_0/js/jquery.min.js"></script>
    <script src="sistema_0/js/jquery.smoothscroll.js"></script>
    <script src="sistema_0/js/jquery.nivo.slider.pack.js"></script>
    <script src="sistema_0/js/jquery.easing-1.3.pack.js"></script>
    <script src="sistema_0/js/jquery.fancybox-1.3.4.pack.js"></script>
    <script src="sistema_0/js/init.js"></script>
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
<div id="container_main">

   <!--menu -->
    <jsp:include page="sistema_0/page/menu/menu.jsp" />
           <!--fim menu -->




<!-- content-wrap -->
<div class="content-wrap">

   


<div id="container1">
    <!-- main -->
    <section id="main">

        <div class="intro-box">
           <h1>Bem Vindo(a).</h1>

           <p class="intro">
               INPROP. : Plataforma  de crowdfunding desenvolvida para
               facilitar a angariação de recursos e fazer aconteser seus
               projetos criativos de determinadas
               categorias(Cinema, Arte, Games, Quadrinhos, Danças, moda, etc...).
           </p>

           <p class="intro">Você pode saber mais <a href="#about-us">AQUI</a> ou tirar suas duvidas <a href="#contact">AQUI</a> sobre como montar seu projeto e por no nosso site.</p>

        </div>

        <div class="slider-wrapper">

            <div id="slider" class="nivoSlider">
                <img src="sistema_0/images/slides/slide1.png" width="383" height="198" alt="" />
                <img src="sistema_0/images/slides/slide2.png" width="383" height="198" alt="" />
                <img src="sistema_0/images/slides/slide3.png" width="383" height="198" alt="" title="#htmlcaption"/>
            </div>
            <div id="htmlcaption" class="nivo-html-caption">
                <strong>O</strong> is an example of a <em>HTML</em> caption with <a href="#">a link</a>.
            </div>
        </div>


    </section>

<p>
    <div class="content-wrap"></div>


  
     <%@include file="sistema_0/midia/lista/lista_projeto.jsp" %>

</div>






<!-- footer -->
<footer>
     <jsp:include page="sistema_0/page/menu/menu_rodape.jsp"/>
</footer>

</div>

</body>
</html>

