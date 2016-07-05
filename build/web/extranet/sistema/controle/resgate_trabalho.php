<?php
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


?>

<?php
session_start();
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
include_once  '../../../sistema/classes/Banco.php';
  //pagina que faz o cadastro de todos os formularios
$banco = new Banco();
$banco->abre_banco();

if(!isset ($_POST['titulo'])&&!isset ($_POST['descricao'])){
    header("Location: ../");
}
if(!isset($_SESSION['nome'])&& !isset($_SESSION['senha'])){
     header("Location: ../login.php");
}

    //caso cmd seja perfil ele ira fzr o cadastro do mesmo
$titulo     =   $_POST['titulo'];
$descricao    =   $_POST["descricao"];
$data_emissao   =   date("Y-m-d");
$status           =  $_POST['status'];//"off";


//-----------------------inicio do novo uploac------------------

    function getFileExtensao($str)
    {
        $i=strrpos($str,".");
        if(!$i){return "";}
        $l = strlen($str)-$i;
        $ext=substr($str,$i,$l);
        return $ext;
    }

    $arquivo= isset($_FILES["imagem"]) ? $_FILES['imagem']: FALSE;
//      echo "".$arquivo['name']."<br>";
    //exit();

    if($arquivo['name']!='')
    {

        $imagem_nome= $_FILES['imagem']['name'];
        $tamanho    = $_FILES['imagem']['size'];


        if($tamanho>1000000)
        {
             echo '<script language = javascript>

		                  alert ("Arquivo de imagem1 muito grande. Tamanho máximo permitido: 300kb");

		                  history.go(-1);

                      </script>';
        }

        $ext    = getFileExtensao($imagem_nome);
        $ext    = strtolower($ext);
        $source = $_FILES['imagem']['tmp_name'];
        $target = "../../../midia/trabalho/".$imagem_nome;
        move_uploaded_file($source,$target);
        $imagepath =$imagem_nome;












        //------------RESIZER----------------------

        $save = "../../../midia/trabalho/cop_".$imagepath;
        $file = "../../../midia/trabalho/".$imagepath;



        list($largura,$altura)=getimagesize($file);

        if($largura >$altura)
        {
            $modLargura = 500;
            $diff       = $largura/$modLargura;
            $modAltura  = $altura/$diff;

        }else{
            $modAltura = 500;
            $diff      = $altura / $modAltura;
            $modLargura= $largura/ $diff;
        }

        $tn = imagecreatetruecolor(500,500);
//        $tn = imagecreatetruecolor($modLargura,$modAltura);
        if($ext =='.jpg' || $ext == '.jpeg' || $ext=='JPG' || $ext=='.JPEG')
        {
            $image  =  imagecreatefromjpeg($file);

        }else if($ext=='.gif' || $ext =='.GIF')
        {
         $image = imagecreatefromgif($file);
        }
        else if($ext=='.PNG' || $ext =='.png')
        {
         $image = imagecreatefrompng($file);
        }
//        echo $modLargura." + ".$modAltura."<br>".$altura."<br>".$largura;
           imagecopyresampled($tn, $image, 0, 0, 0, 0, 500, 500, $largura, $altura) ;
        imagejpeg($tn, $save, 100);


    //----------------------FIM RESIZE--------------------











        //------------RESIZER----------------------

        $save = "../../../midia/trabalho/thumb_crop/crop_".md5($imagepath).$ext;
        $file = "../../../midia/trabalho/cop_".$imagepath;



        list($largura,$altura)=getimagesize($file);

        if($largura >$altura)
        {
            $modLargura = $_POST['w'];
            $diff       = $largura/$modLargura;
            $modAltura  = $altura/$diff;

        }else{
            $modAltura = $_POST['h'];
            $diff      = $altura / $modAltura;
            $modLargura= $largura/ $diff;
        }

//        $tn = imagecreatetruecolor($modLargura,$modAltura);
        $tn = imagecreatetruecolor(70,40);
        if($ext =='.jpg' || $ext == '.jpeg' || $ext=='JPG' || $ext=='.JPEG')
        {
            $image  =  imagecreatefromjpeg($file);

        }else if($ext=='.gif' || $ext =='.GIF')
        {
         $image = imagecreatefromgif($file);
        }
        else if($ext=='.PNG' || $ext =='.png')
        {
         $image = imagecreatefrompng($file);
        }
        
//           imagecopyresampled($tn, $image, 0, 0, $_POST["x1"]/$_POST["y2"],  $_POST["x2"]/$_POST["y2"],$modLargura*(142/100), $modAltura*(100/100),  $_POST['w'], $_POST['h']) ;
           imagecopyresampled($tn, $image, 0, 0, $_POST["x1"],  $_POST["y1"],70, 40,  $_POST['w'], $_POST['h']) ;
        imagejpeg($tn, $save, 95);
       // imagejpng($tn, $save, 100);
        
        unlink($file);
       //
    //----------------------FIM RESIZE--------------------
//echo"<img src='../../../midia/trabalho/thumb_crop/crop_".md5($imagepath).$ext."'/>";
//
//


//exit();




        //------------RESIZER----------------------

        $save = "../../../midia/trabalho/big/big_".md5($imagepath).$ext;
        $file = "../../../midia/trabalho/".$imagepath;



        list($largura,$altura)=getimagesize($file);

        if($largura >$altura)
        {
            $modLargura = 800;
            $diff       = $largura/$modLargura;
            $modAltura  = $altura/$diff;

        }else{
            $modAltura = 800;
            $diff      = $altura / $modAltura;
            $modLargura= $largura/ $diff;
        }

        $tn = imagecreatetruecolor($modLargura,$modAltura);
        if($ext =='.jpg' || $ext == '.jpeg' || $ext=='JPG' || $ext=='.JPEG')
        {
            $image  =  imagecreatefromjpeg($file);

        }else if($ext=='.gif' || $ext =='.GIF')
        {
         $image = imagecreatefromgif($file);
        }
        else if($ext=='.PNG' || $ext =='.png')
        {
         $image = imagecreatefrompng($file);
        }
           imagecopyresampled($tn, $image, 0, 0, 0, 0, $modLargura, $modAltura, $largura, $altura) ;
        imagejpeg($tn, $save, 100);


    //----------------------FIM RESIZE--------------------






        //------------RESIZER----------------------
        $save = "../../../midia/trabalho/small/small_".md5($imagepath).$ext;
        $file = "../../../midia/trabalho/".$imagepath;
        list($largura,$altura)=getimagesize($file);

        if($largura >$altura)
        {
            $modLargura = 200;
            $diff       = $largura/$modLargura;
            $modAltura  = $altura/$diff;

        }else{
            $modAltura = 200;
            $diff      = $altura / $modAltura;
            $modLargura= $largura/ $diff;
        }


        $tn = imagecreatetruecolor($modLargura,$modAltura);
        if($ext =='.jpg' || $ext == '.jpeg' || $ext=='JPG' || $ext=='.JPEG')
        {
            $image  =  imagecreatefromjpeg($file);

        }else if($ext=='.gif' || $ext =='.GIF')
        {
         $image = imagecreatefromgif($file);
        }
        else if($ext=='.PNG' || $ext =='.png')
        {
         $image = imagecreatefrompng($file);
        }
           imagecopyresampled($tn, $image, 0, 0, 0, 0, $modLargura, $modAltura, $largura, $altura) ;
        imagejpeg($tn, $save, 100);



    //----------------------FIM RESIZE--------------------


    //------------RESIZER----------------------

        $save = "../../../midia/trabalho/thumb/thumb_".md5($imagepath).$ext;
        $file = "../../../midia/trabalho/".$imagepath;
        list($largura,$altura)=getimagesize($file);



        if($largura >$altura)
        {
            $modLargura = 70;
            $diff       = $largura/$modLargura;
            $modAltura  = $altura/$diff;

        }else{
            $modAltura = 40;
            $diff      = $altura / $modAltura;
            $modLargura= $largura/ $diff;
        }

        $tn = imagecreatetruecolor($modLargura,$modAltura);
        if($ext =='.jpg' || $ext == '.jpeg' || $ext=='JPG' || $ext=='.JPEG')
        {
            $image  =  imagecreatefromjpeg($file);

        }else if($ext=='.gif' || $ext =='.GIF')
        {
            $image = imagecreatefromgif($file);
        }
        else if($ext=='.PNG' || $ext =='.png')
        {
         $image = imagecreatefrompng($file);
        }
         imagecopyresampled($tn, $image, 0, 0, 0, 0, $modLargura, $modAltura, $largura, $altura) ;
         imagejpeg($tn, $save, 100);

        $imageDB  =md5($imagepath);
        $imageDB .=$ext;
        unlink($file);



    }



    //----------------------FIM RESIZE--------------------



















    //---------------fim upload novo-----------------





















$query_insert="INSERT INTO trabalho VALUES(null,'$imageDB','$titulo','$descricao','$status','$data_emissao')";
$rs = mysql_query($query_insert);
if($rs!= null){
//   / $id_perfil  = mysql_insert_id();
//    $texto=$_POST['sobremin'];
//    $query_insert="INSERT INTO mensagem VALUES(null,'$texto',$id_perfil)";
//    $rs = mysql_query($query_insert);
//    if($rs!=null){
         $_SESSION["msg"]= "Seu trabalho foi cadastrado com sucesso!";
         header("Location: ../../?page=trabalho&acao=add");
//    }else{
//        echo "texto sobre você nao foi cadastrado";
//    }
}  else{
             $_SESSION["msg"]= "Os dados não foram cadastrados!";
         header("Location: ../../?page=trabalho&acao=add");

    echo "";
}




















?>
