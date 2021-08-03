<?php
$mess= $_POST["data"];
$spe="split123split";

$mysqli=new mysqli("fdb18.awardspace.net","3841522_message","FinalYear@05","3841522_message");
$result=mysqli_query($mysqli,"select * from messages");
while($row = mysqli_fetch_array($result))
{

echo ($row['data'].$spe.$row['dist']);
break;

}

?>