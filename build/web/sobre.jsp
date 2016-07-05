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


    <script>window.jQuery || document.write('<script src="js/jquery-1.6.1.min.js"><\/script>')</script>

  
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
                <h1>Sobre a INPROP .</h1>
                            <div class="primary">

                                <p class="intro">O site www.InProP.com.br surgiu
                                  da oportunidade de se atuar como intermediário
                                  no processo de patrocínio/investimento em
                                  projetos tecnológicos, sociais, ambientais,
                                  educacionais e culturais, através da internet.
                                  O site visa a facilitar a comunicação entre
                                  quem tem uma idéia criativa e não tem dinheiro
                                  para colocá-la em prática, de pessoas que de
                                  alguma forma se, de pessoas que de alguma
                                  forma se identificaram com esses projetos,
                                  para pessoas Jurídicas, serve como um
                                  termômetro, para medir o nível de aceitação de
                                  certo produto no mercado, servindo para a
                                  divulgação da sua marca também e para obter
                                  patrocínio para executá-los dentro de um tempo
                                  e orçamento pré-determinado no momento em que o
                                  projeto é cadastrado no site, oferecendo prêmios
                                  compatíveis com as quantias oferecidas para patrocinar
                                  o projeto. Os clientes do 1NPR0P são
                                  classificados em PROJETISTAS - Pessoas ou
                                  empresas que cadastram seu projeto no site
                                  sem custo adicional-e PATROCINADORES-usuários que apoiarão os projetos.
                                </p>

                                <h2>Equipe de Gestão</h2>

                                <p>A equipe de Gestão do 1NPR0P são jovens
                                    universitários cada um em uma atividade
                                    diferente, buscando inovação e novos
                                    desafios, todos com experiência em suas
                                    respectivas áreas e trabalho e com uma visão
                                    empreendedora, motivação para enfrentar
                                    e superar os desafios de administrar, gerar
                                    resultados positivos e conquistar uma grande
                                    fatia desse mercado totalmente inexplorado e
                                    vasto tanto no Rio de Janeir, como no Brasil.</p>

                                <div class="row no-bottom-margin">

                                     <!--<section class="col first">

                                       <h2>Our Process</h2>

                                        <p>Nascetur augue hac platea enim, egestas pulvinar vut. Pulvinar cum, ac eu, tristie
                                        acus duis in dictumst non integer! Elit, sed scelerisque odio tortor, sed platea dis? Quis
                                        cursus parturient ac amet odio in? Nunc Amet urna scelerisque eu lectus placerat.</p>

                                    </section>
                                   --><!--
                                    <section class="col">

                                        <h2>Our Approach</h2>

                                        <p>Pellentesque magna mi, iaculis pharetra eu, fermentum ullamcorper nisi.
                                        Integer fringilla magna ut quam vulputate erat. Pulvinar cum, ac eu augue ut sit amet
                                        gravida lacinia, eros massa condimentum sem, a fermentum ligula lorem non.
                                        Phasellus vulputate.</p>

                                    </section>
                                  -->
                                </div>

                                <h2>Equipe INPROP</h2>

                                <ul class="the-team">
                                    <li class="odd">
                                        <div class="thumbnail">
                                            <a href="#"><img alt="thumbnail" src="sistema_0/images/thumb-pic.png" width="83" height="78"></a>
                                        </div>
                                        <p class="mname"><a href="#">Marcio do Couto</a></p>
                                        <p>Fundador &amp; Diretor comercial</p>
                                    </li>
                                    <li>
                                        <div class="thumbnail">
                                            <a href="#"><img alt="thumbnail" src="sistema_0/images/thumb-pic.png" width="83" height="78"></a>
                                        </div>
                                        <p class="mname"><a href="#">Thiago Ramon Ferreira Pinto</a></p>
                                        <p>Fundador &amp; Diretor de produção</p>
                                    </li>
                                   <!-- <li class="odd">
                                        <div class="thumbnail">
                                            <a href="#"><img alt="thumbnail" src="sistema_0/images/thumb-pic.png" width="83" height="78"></a>
                                        </div>
                                        <p class="mname"><a href="#">Haruno Sakura</a></p>
                                        <p>Graphic Designer</p>
                                    </li>
                                    <li>
                                        <div class="thumbnail">
                                            <a href="#"><img alt="thumbnail" src="sistema_0/images/thumb-pic.png" width="83" height="78"></a>
                                        </div>
                                        <p class="mname"><a href="#">Uchiha Sasuke</a></p>
                                        <p>Web Developer</p>
                                    </li>-->
                                </ul>

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

                                    <h2>Frases</h2>

                                    <div class="testimonials">
                                        <blockquote>
                                            <p>"E ao final de nossas longas
                                                explorações chegaremos finalmente
                                                ao lugar de onde partimos e o
                                                conheceremos então  pela primeira
                                                vez...." (Eliot, citado por Rubem)
                                            </p>
                                            <cite><!--&mdash; John Doe, XYZ Company--></cite>
                                        </blockquote>
                                        <blockquote>
                                            <p>"A cobra que não consegue
                                                livrar-se de sua casca morre.
                                                O mesmo acontece com os espíritos
                                                que são impedidos de mudar as suas
                                                opiniões; eles deixam de ser
                                                espírito."
                                                (Nietzsche, citado por Rubem)
</p>

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

</div>

</body>
</html>

