<%-- 
    Document   : index
    Created on : 09/12/2012, 13:11:59
    Author     : Ramon
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="p" type="com.inprop.view.UsuarioView"  class="com.inprop.view.UsuarioView"  />
<jsp:useBean id="projeto" type="com.inprop.view.ProjetoView"  class="com.inprop.view.ProjetoView"  />
<c:set var="path" value="${header['host']}${pageContext.servletContext.contextPath}"/>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <meta HTTP-EQUIV="Cache-control" CONTENT="no-cache" />
        <meta HTTP-EQUIV="Expires" CONTENT="0" />


        <link rel="stylesheet" href="site/sistema/script/css/default.css" type="text/css" media="screen" />
        <script src="site/sistema/script/js/jquery/jquery.js"></script>
        <script src="site/sistema/script/js/jquery/default.js"></script>
        <script src="site/sistema/script/js/jquery/maskInput.js"></script>


        <script>
         function carregaPage(rel){
                var page  =rel;
                       if(page =="cadastro"){
                            page="login";
                            rel+="&isAd=${p.admin}";
                        }
                        var url ='';
                            url = 'site/sistema/page/'+page+'.jsp';

                        var regTxt = /.jsp/;
                        if(regTxt.test(page)){
                                 url = 'site/sistema/page/'+page;
                        }
                        $('#body-corpo').html("Carregar...");
                        $.ajax({
                                type: 'post',
                                 url:url,
                                data:'rel='+rel,
                             success: function(retorno){
                                     $('#body-corpo').html(retorno);
                                     $("input[name=dataNascimento]").mask('99/99/9999');
                                      funcClick();
                                    }
                             });
            }


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
                 $("a").unbind().click(function(){
                    var rel = $(this).attr('rel');
                    carregaPage(rel);
                });
            }

            $(function(){
                if('${pagina}' !=''){
                     carregaPage('${pagina}');
                }else{
                    carregaPage("home");
                }
                    funcClick();
                    alerta('${mensagem}')
            })
            
        </script>
               <style>
                    #bar-configuracoes{
                        width:100%;
                        background:rgba(2, 2, 2, 0.05);
                        height:50px;
                        margin-bottom: 10px;
                        font-size:12px;

                    }
                    #bar-configuracoes #container{
                        width:970px;
                        margin:auto;
                    }
                    #bar-configuracoes #container #logoMarca{
                        width:200px;
                        height: auto;
                        padding: 5px 0px;
                        float:left;
                    }
                    #bar-configuracoes #container #menu{
                        width:480px;
                        height: auto;
                        margin-left:10px;
                        padding:  10px 0 9px 0px;
                        float:right;
                    }
                    #bar-configuracoes #container #menu #usuario{
                        width:400px;
                        height: 31px;
                        margin-left:10px;
                        float:right;
                        margin: 0px 0px;
                        padding-top: 5px;
                    }
                    #bar-configuracoes #container #menu #usuario label{
                        padding: 0px 10px;
                        float:right;

                    }
                    #bar-configuracoes #container #busca{
                        width:270px;
                        height: auto;
                        padding:  10px 0 9px 0px;
                        float:left;
                    }
                    #bar-configuracoes #container #busca input[type=text]{
                        width:200px;
                        height: 25px;
                        float:left;

                    }
                    #bar-configuracoes #container #busca input[type=button]{
                        height: 25px;
                       margin-top:2px;

                        float:left;

                    }
                </style>
                     <% request.getSession().setAttribute("pagina", null); %>
                     <% request.getSession().setAttribute("mensagem", null); %>
        <title>Aqui Sua Idéia é Valorizada </title>
    </head>
    <body>
        <c:if test="${sessionScope.usuario!=null}">
               <div id="bar-configuracoes">
                    <div id="container">
                              <div class="logoMarca" id="logoMarca">
                                  <a rel="home"> <img src="${contextPath}/site/sistema/midia/imagem/logoInprop_01.png"/></a>
                              </div>
                             <div id="busca" style="display:none;">
                                 <input type="text" value="" size=""/><input type="button" value="Buscar" />
                             </div>
                             <div id="menu">
                                 <div id="usuario">
                                      <label><a  href="LogofController">Sair</a></label>
                                      <label><a href="usuario">Página Inicial</a></label>
                                      <label>${sessionScope.usuario.email}</label>
                                      <label>     <img width="30" height="30" src="${contextPath}/usuario/media/imagem/normal/thumb/${sessionScope.usuario.perfil.foto}"/></label>

                                 </div>
                             </div>
                    </div>
                </div>
       </c:if>
        <div id="fundo">

            <div id="conteiner">

                <c:if test="${sessionScope.usuario==null}">
                            <div id="top">

                                <div id="left-top">

                                    <img src="${contextPath}/site/sistema/midia/imagem/logoInprop_02.png"/>

                                </div>

                                <div id="center-top">

                                    <div id="menu">
                                         <ul>
                                             <li class="li-text"><a   rel="home">Inicio</a></li>
                                             <li class="li-text"><a   rel="projeto">Projetos</a></li>
                                            <!-- <li class="li-text"><a   rel="startup">StartUps</a></li>
                                             <li class="li-text"><a  rel="guia">Envie um Projeto</a></li>
                                             <li class="li-text"><a  rel="blog">Blog</a></li>
                                             <li class="li-text"><a  rel="ajuda">Ajuda</a></li>-->
                                             <li class="li-text"><a  rel="cadastro">Cadastre-se</a></li>
                                             <li class="li-text"><a  rel="login">Log-in</a></li>

                                       </ul>

                                    </div>

                                </div>

                      </div>

            </c:if>

                <div id="alerta"></div>
                <div id="body-corpo">
                </div>


                <div id="rodape" style="display:none;">
                    <div class="" id="box">
                        <div id="menu-categoria">
                             <h1>INPROP</h1>
                             <ul>
                                <li>Como funciona? </li>
                                <li>FAQ/Ajuda </li>
                                <li>Termos de uso </li>
                                <li>Política de privacidade </li>
                                <li>Contato </li>

                            </ul>
                        </div>
                    </div>

                <!--    <div class="" id="box">
                             <h1>INPROP</h1>
                    </div>

                   
                    <div class="sobre" id="box">
                        <h1>Redes</h1>
                    </div>
                   -->
                    
                </div>
      
       </div>

 </div>
        <div id="dados">
            <div class="direito" id="box">Todos os direitos reservados a <strong>INPROP desenvolvimento</strong></div>
            <div class="criador" id="box"><img src="site/sistema/midia/imagem/logoInprop_01.png"/></div>
        </div>
             
           
    </body>
</html>
