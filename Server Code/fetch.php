<?php
$spl1="The messege is ";
$spl="<br>And distance is ";
$mysqli=new mysqli("fdb18.awardspace.net","3841522_message","FinalYear@05","3841522_message");
$result=mysqli_query($mysqli,"select * from messages");
while($row = mysqli_fetch_array($result))
{

echo ($spl1.$row['data'].$spl.$row['dist']);
break;


}

?>