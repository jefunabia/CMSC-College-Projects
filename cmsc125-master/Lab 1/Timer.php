<?php
   session_start();

   //to reset the saved countdown
   if (!empty($_REQUEST['resetCountdown']))
   {
       unset($_SESSION['startTime']);
   }

   if (empty($_SESSION['startTime']))
   {
       $_SESSION['startTime'] = time();
   }

   //In seconds
   $startTime = time() - $_SESSION['startTime'];
?>

<script type="text/javascript">
    var countdown = 60; //in seconds
    var startTime = <?php echo $startTime; ?>;

    startCountdown(startTime, countdown);

    function startCountdown(startFrom, duration)
    {
        //countdown implementation
    }
 </script>
 
 <?php echo $startTime; ?>