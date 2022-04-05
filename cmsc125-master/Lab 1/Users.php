<?php

class Users {

    public function randomize() : int {

        $numberOfUsers = rand(1,30);

        return $numberOfUsers;
    }

    public function addToArray($numberOfUsers) : array {

        $arrUsers = array();

        for ($i = 1; $i <= $numberOfUsers; $i++) {
    
            $arrUsers[] = $i;
    
        }

        return $arrUsers;
    
    }

}


?>