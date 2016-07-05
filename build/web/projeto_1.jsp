<%--
    Document   : index
    Created on : 28/04/2012, 23:27:12
    Author     : Ramon
--%>

<!--[if IE 7 ]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8 oldie"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html> <!--<![endif]-->


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
    #canto_direito{
        float:right;
        width:35%;
        height:100%;
        background-color:buttonface;
    }
    #canto_esquerda{
        float:left;
        width:64%;
        height:100%;
        background-color:white;
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
        <!-- main -->
        <section id="main">

            <div class="content-wrap">
                <jsp:include page="sistema_0/midia/find/find_projeto.jsp"/>

            </div>
         </section>

    </div>
    <!-- footer -->
        <footer>
             <jsp:include page="sistema_0/page/menu/menu_rodape.jsp"/>
        </footer>

    </div>

</body>
</html>

