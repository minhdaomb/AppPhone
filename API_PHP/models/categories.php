<?php
    class Categories{
        private $id;
        private $category_name;
       
        

        function __construct($id, $category_name)
        {
            $this->id = $id;
            $this->category_name = $category_name;
            
        }

        public function getId()
        {
            return $this->id;
        }
        public function getCategory_name()
        {
            return $this->category_name;
        }
    
        
    }
?>