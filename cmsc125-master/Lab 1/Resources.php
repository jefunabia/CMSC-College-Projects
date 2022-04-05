<?php

class Resources {

    public function randomize() : int {

        $numberOfResources = rand(1,30);

        return $numberOfResources;
    }


    public function addToArray($numberOfResources) : array {

        //$strResource = "Resource ";
        $arrResources = array();

        for ($i = 1; $i <= $numberOfResources; $i++) {
    
            $arrResources[] = $i;
    
        }

        return $arrResources;
    
    }

}

?>