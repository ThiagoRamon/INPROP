<?php
session_start();
  $_SESSION['msg']="você efetuo o logout!";
session_destroy();

header("Location: ../../../login.php");
/*
 *
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of destroy
 *
 * @author Ramon
 */

?>
