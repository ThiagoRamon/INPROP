<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include_once '../../../sistema/classes/Banco.php' ;
if(isset($_GET["page"]) && isset($_GET["acao"])){
   echo $pagina = $_GET["page"];
   echo $acao   = $_GET["acao"];
   
   $banco  = new Banco();
   $banco->abre_banco();
   $table  = "texto";
   //?page=conteudo&cmd=home;
   $sql="select count(*) from $table where pagina_texto='$pagina'";
   if(mysql_result(mysql_query($sql),0)!=0){
      $_SESSION["msg"]="Por favor já existe texto cadastrado para a pagina($pagina).
                         Você so pode atualizar o texto desta pagina !";
      header("Location: ../../?page=conteudo&cmd=$pagina");
   }

   exit();
   switch($pagina){
        case'home':
            if($acao=='cadastrar'){
            echo $_POST["titulo"] ;
            //echo $_POST["descricao"] ;
            echo $_POST["ordem"] ;
            //echo $_POST["status"] ;
             exit();
            if(isset($_POST["titulo"]) && isset($_POST["descricao"]) &&  isset($_POST["ordem"]) && isset($_POST['status'])){
                $ordem         =$_POST["ordem"] ;
                $titulo        =$_POST["titulo"] ;
                $descricao     =$_POST["descricao"] ;
                $status        =$_POST["status"] ;
                $data_emissao  =date("Y-m-d") ;

                $dados = "'$pagina','$status','$ordem','$titulo','$descricao','$data_emissao'";
                if($banco->insert($table, $dados)){
                    echo "dados cadastrados com sucesso!";
                }else{
                    echo "dados nao cadastrados !";
                }
            
            }
                 
            }else if($acao=='atualizar'){
                
            }else if($acao=='deletar'){
                
            }
            
                        
        break;
        case'portfolio':
        break;
        case'contato':
        break;
   
   }
}

?>
