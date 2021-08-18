<?php

    include_once '../services/product_service.php';


    class ProductController {

        private $product_service;
        public function __construct()
        {
            $this->product_service = new ProductService();        
        }

        public function getAllProducts()
        {
            return $this->product_service->getAllProducts();     
        }
        public function getAllProducts2()
        {
            return $this->product_service->getAllProducts2();     
        }
        public function getAllCategory()
        {
            return $this->product_service->getCategory();     
        }
        public function getById($id)
        {
            return $this->product_service->getById($id);     
        }
        public function getAllCategories()
        {
            return $this->product_service->getAllCategories();     
        }
        public function insert($product)
        {
            return $this->product_service->insert($product);     
        }
        public function insertCart($product)
        {
            return $this->product_service->insertCart($product);     
        }
        public function update($product)
        {
            return $this->product_service->update($product);     
        }
        public function updateCategory($product)
        {
            return $this->product_service->updateCategory($product);     
        }
        public function delete($id)
        {
            return $this->product_service->delete($id);     
        }
        public function deleteCart($id)
        {
            return $this->product_service->deleteCart($id);     
        }
        public function deleteCategory($id)
        {
            return $this->product_service->deleteCategory($id);     
        }
        public function get1Products()
        {
            return $this->product_service->get1Products();     
        }
        public function get2Products()
        {
            return $this->product_service->get2Products();     
        }
        public function get3Products()
        {
            return $this->product_service->get3Products();     
        }
        public function get4Products()
        {
            return $this->product_service->get4Products();     
        }
        public function get5Products()
        {
            return $this->product_service->get5Products();     
        }
        public function getCart()
        {
            return $this->product_service->getCart();     
        }

        public function insertCategory($category)
        {
            return $this->product_service->insertCategory($category);     
        }
        
    }
?>