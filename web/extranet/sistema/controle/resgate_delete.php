<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include_once '../../../sistema/classes/Banco.php';
$cmd="default";
if(isset($_GET['cmd'])){
    $cmd = $_GET['cmd'];
}else{
    $_SESSION['msg']="por favor Escolha algo que queira deletar ";
    header("Location: ../../");
}
$banco = new Banco();
$banco->abre_banco();

switch ($cmd){
     case 'trabalho':
         $id_trablho= $_GET['id_trabalho'];
         $table = $cmd;
         $condicao="id_trabalho=".$id_trablho;
         if($banco->delete($table, $condicao)){
             $_SESSION['msg']="Trabalho deletado com sucesso!" ;
             header("Location: ../../?page=trabalho&acao=findAll");
         }else{
             $_SESSION['msg']="Trabalho não pode ser deletado!
                               Tente de novo mais tarde!" ;
             header("Location: ../../?");
         }
     break;
    case "default":
             header("Location: ../../");
     break;

}

//if(){
//
//}
?>
