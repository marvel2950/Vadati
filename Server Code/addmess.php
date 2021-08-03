<?php
//$mess=filter_input(INPUT_POST,"data");
$mess= $_POST["data"];
$dis= $_POST["distance"];

$mess=str_replace('"', "", $mess);
$mess=str_replace("'", "", $mess);
$dis=str_replace('"', "", $dis);
$dis=str_replace("'", "", $dis);
$mysqli=new mysqli("fdb18.awardspace.net","3841522_message","FinalYear@05","3841522_message");
$sql="UPDATE messages SET data='".$mess."'";
$result=mysqli_query($mysqli,$sql);
$sql="UPDATE messages SET dist='".$dis."'";
$result=mysqli_query($mysqli,$sql);
$spe="split123split";
echo $mess.$spe.$dis



?>