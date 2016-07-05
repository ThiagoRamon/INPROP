<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include_once"../../../sistema/classes/Banco.php";
$banco = new Banco();
$banco->abre_banco();
$dados;
if(isset($_POST["sessao"])){
    $dados ="'".strtoupper($_POST["sessao"])."','default.png'";
    $table = 'sessao';
    $rs = mysql_query("select nome_sessao from $table where nome_sessao='".$_POST["sessao"]."'");
    var_dump($rs);
    if($row = mysql_fetch_array($rs)){
        echo strtoupper($_POST["sessao"])." Já está cadastrada na base de dados !";
    }else{
        if($banco->insert($table, $dados)){
            echo strtoupper($_POST["sessao"])." ok!";
        }else{
            echo "Sessao nao pode ser cadastrada, tente mais tarde!";
        }
    }

}
?>
