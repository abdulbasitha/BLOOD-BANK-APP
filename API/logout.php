<?php
session_start();
if(!isset($_SESSION['is_authenticated'])) {
    header("Location:login.php");
    exit();
}
session_destroy();
header("Location:login.php");
exit();