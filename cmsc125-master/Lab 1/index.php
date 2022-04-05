<?php include 'Resources.php'?>
<?php include 'Users.php'?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>CMSC 125 | Lab 1</title>
    <link rel="stylesheet" type="text/css" href="https://bootswatch.com/4/darkly/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="style.css">
    <!----<script src="index.js"></script>--->
</head>
<body>

<div class="jumbotron">
  <h1 class="display-3">Lab 1</h1>
  <p class="lead">Multiprogramming with Time-sharing Systems</p>

</div>

<div class="container-fluid">

    <?php

    $resources = new Resources();
    $users = new Users();

    // Randomize number of Resources and Users

    /*
    // TOTAL NUMBER of Resources
    $numberOfResources = $resources->randomize();
    // TOTAL NUMBER of Users
    $numberOfUsers = $users->randomize();

    */

    // TEMP
    $numberOfResources = 10;
    $numberOfUsers = 5;
    // Number should not be equal, if equal, randomize again
    if ($numberOfUsers == $numberOfResources) {
        $numberOfResources = $resources->randomize();
    }

    // Print the TOTAL NUMBER of Resources & Users
    echo "<h5> <p class='text-success'> <strong> Number of Users: </strong> </h5> <p> $numberOfUsers </p>";
    echo "<h5> <p class='text-success'> <strong> Number of Resources: </strong> </h5> <p> $numberOfResources </p> <br>";

    // ARRAY from [0] -> 1 to N Users
    $arrUsers = $users->addToArray($numberOfUsers);

    // ARRAY from [0] -> 1 to N Resources
    $arrResources = $resources->addToArray($numberOfResources);
   

    // Assign each user with a resource

    // MAIN ARRAY -> User -> User Number, Resource, Time
    $arrUserResource = array();


    for($i = 1; $i <= $numberOfUsers; $i++) {
        
        // Randomize from the N Resources
        $assignedResource = rand(1, $numberOfResources);

        // Time given for each resource
        $timeGiven = rand(1, 5);

        // Add values to MAIN array
        $arrUserResource[] = array(
            "User" => $i, 
            "Resource" => $assignedResource, 
            "Time" => $timeGiven
        );

        // ARRAY that contains the random resources being used
        $arrAssignedResource[] = $assignedResource;

        // ARRAY that contains the random time given
        $arrTime[] = $timeGiven;
    }

    ?>

<h4> <p class='text-success'> <strong>[OVERALL VIEW] Users and Their Assigned Resources </h4></strong>
<table class="table table-hover">
  <thead>
    <tr class="table-secondary">
      <th scope="col">User</th>
      <th scope="col">Resource</th>
      <th scope="col">Time</th>
    </tr>
  </thead>
  <tbody>

    <?php
    //for ($i = 0; $i < count($arrUserResource); $i++) {

        foreach ($arrUserResource as $arr) {

            echo "<tr><td>". $arr["User"] . "</td><td>" . $arr["Resource"] . "</td><td>" . $arr["Time"] . " second(s)" . "</td></tr>";
        //}
    }
    ?>

</tbody>
</table>

<div id="all">
<h2 id="countdown"></h2>
    <h4> <p class='text-success'> <strong> Currently Processing </strong> </h4>
    <div class="progress"> <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 100%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"> </div> </div> <br>


    <?php

    $allUsersFinished = 0;
    $timeRunning = 10;

    while($allUsersFinished === 0 && $timeRunning > 0) {

        $tempArr = array_unique(array_column($arrUserResource, 'Resource'));
        $currentlyProcessing = array_intersect_key($arrUserResource, $tempArr);
        $size_currentlyProcessing = sizeof($currentlyProcessing);

        foreach ($currentlyProcessing as $current) {
            echo "<h4> User " . $current["User"] . " </h4><p> Resource " . $current["Resource"] , '</p><p>' . " Time: " . $current["Time"] . ' second(s) </p> <p id="countdown"></p>';


            if ($current["User"] === $size_currentlyProcessing) {
                echo "Last";
                $allUsersFinished = 1;
                break;
            }

            
            $current["Time"]--;

            $timeRunning--;
            echo $current["Time"];

           

        }


    }

    echo "<h4>  <p class='text-info'> <strong> Waiting to be Processed </strong> </h4>";

    echo '<div class="progress"> <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: 100%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div> </div> <br>';
  
    $tempArr2 = array_diff_key(array_column($arrUserResource, 'Resource'), $tempArr);
    $waiting = array_intersect_key($arrUserResource, $tempArr2);

    foreach ($waiting as $wait) {
        echo "<h4> User " . $wait["User"] . " </h4><p>Resource " . $wait["Resource"] , "</p><p> Time: " . $wait["Time"] . ' second(s) </p>';
    }

    // STAND BY RESOURCES
    echo "<h4>  <p class='text-warning'> <strong> Standby Resources </strong>  <small>(Resources that have finished processing)</small> </h4>";

    echo '<div class="progress"> <div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: 100%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div> </div> <br>';

    // *DISPLAY ONLY RESOURCES THAT ARENT ASSIGNED TO ANY USER
  
    $arrDiff = array_diff($arrResources, $arrAssignedResource);
    
    echo "<h4>  <p class='text-danger'> <strong> Unused Resources </strong> <small>(Resources that were never assigned to any user)</small> </h4> ";
    
    echo '<div class="progress"> <div class="progress-bar progress-bar-striped bg-danger" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div> </div> <br>';

    foreach($arrDiff as $value) {
        echo '<p> Resource ' . $value . '</p>';
    }

    echo "</div>";
?>

</div>

<script>
    var timeleft = 10;
    var downloadTimer = setInterval(function(){
    document.getElementById("countdown").innerHTML = timeleft + " seconds remaining";
    timeleft -= 1;
        if(timeleft <= 0){
            clearInterval(downloadTimer);
            document.getElementById("countdown").innerHTML = "Finished"
        }
    }, 1000);


  </script>
</body>
</html>