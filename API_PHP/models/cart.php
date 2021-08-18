<?php
    class Cart{
        private $id;
        private $product_name;
        private $price;
        private $image_url;
        

        function __construct($id, $product_name, $price,$image_url)
        {
            $this->id = $id;
            $this->product_name = $product_name;
            $this->price = $price;
            $this->image_url = $image_url;
            
        }

        public function getId()
        {
            return $this->id;
        }
        public function getProductName()
        {
            return $this->product_name;
        }
        public function getPrice()
        {
            return $this->price;
        }
        public function getImageUrl()
        {
            return $this->image_url;
        }
        
    }
?>