
<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

include_once"../../../sistema/classes/Banco.php";
$banco = new Banco();
$banco->abre_banco();
$dados;

if(isset($_POST["atividade"])&& $_POST["id_sessao"]){
       $atividade =$_POST["atividade"];

    $dados ="'$atividade','default.png',".$_POST["id_sessao"]." ";
    $table ="atividade";
    $rs = mysql_query("select * from $table where nome_atividade='".$_POST["atividade"]."'");
    var_dump($rs);
    if(mysql_fetch_array($rs)==true){
        echo strtoupper($_POST["atividade"])." Já está cadastrada na base de dados !";
    }else{
        if($banco->insert($table, $dados)){
            echo $_POST["atividade"]." ok!";
        }else{
            echo "Sessao nao pode ser cadastrada, tente mais tarde!";
        }
    }

}
?>
