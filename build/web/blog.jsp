<%--
    Document   : index
    Created on : 28/04/2012, 23:27:12
    Author     : Ramon
--%>

<!--[if IE 7 ]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8 oldie"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->  <!--<![endif]-->


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
   <script>
 
   window.onload = function(){
             var contato = document.getElementById("contato");
             contato.onclick = function(){
                  //alert();
                  showPopWin( formulario , 500, 500, null);
             }
       }
   </script>
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
   
         <%
            out.println(request.getParameter("pagina"));
         %>
    <!-- about us -->
          <section id="about-us" class="clearfix">
                <h1>BLOG INPROP</h1>

                            <aside>
                                    <h2>More about us</h2>
                                    <p>Nascetur augue hac platea enim, egestas pulvinar vut. Pulvinar cum, ac eu, tristie
                                    acus duis in dictumst non integer! Elit, sed scelerisque odio.</p>
                                    <a href="#" class="download-btn">Download PDF</a>
                                    <h2>Links</h2>
                                    <ul class="link-list">
                                        <li><a href="http://themeforest.net?ref=ealigam" title="Site Templates">Themeforest</a></li>
                                        <li><a href="http://www.4templates.com/?go=228858961" title="Website Templates">4Templates</a></li>
                                        <li><a href="http://www.dreamhost.com/r.cgi?287326|sshout" title="Webhosting">Dreamhost</a></li>
                                        <li><a href="http://store.templatemonster.com?aff=ealigam" title="Web Templates">Templatemonster</a></li>
                                    </ul>
                                    <h2>Testimonials</h2>
                                    <div class="testimonials">
                                        <blockquote>
                                            <p>Donec sed odio dui. Nulla vitae elit libero, a pharetra augue.
                                            Nullam id dolor id nibh ultricies vehicula ut id elit. </p>
                                            <cite>&mdash; John Doe, XYZ Company</cite>
                                        </blockquote>
                                        <blockquote>
                                            <p>Aenean lacinia bibendum nulla sed consectetur. Cras mattis
                                            consectetur purus sit amet fermentum.</p>
                                            <cite>&mdash; Jane Roe, ABC Corp</cite>
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

</body>
</html>

