<?php
    class Product2{
        private $id;
        private $product_name;
        private $price;
        private $image_url;
        private $datee;
        private $category_id;
        

        function __construct($id, $product_name, $price,$image_url,$datee,$category_id)
        {
            $this->id = $id;
            $this->product_name = $product_name;
            $this->price = $price;
            $this->image_url = $image_url;
            $this->datee = $datee;
            $this->category_id = $category_id;
            
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
        public function getDatee()
        {
            return $this->datee;
        }
        public function getCategoryId()
        {
            return $this->category_id;
        }
    }
?>