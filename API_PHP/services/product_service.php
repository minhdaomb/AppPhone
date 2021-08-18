<?php
    include_once '../configs/database_config.php';
    include_once '../models/product.php';
    include_once '../models/reset.php';
    include_once '../models/cart.php';
    include_once '../models/categories.php';
    include_once '../models/product2.php';

    class ProductService {
        private $connection;
        private $tblProducts = "tblProducts";
        private $tblCart = "tblCart";
        private $tblCategories = "tblCategories";
        private $tblProducts2 = "tblProducts2";

        public function __construct()
        {
            $this->connection = (new DatabaseConfig())->getConnection();
        }

        public function getAllProducts()
        {
            try {
                $query = "SELECT id, product_name, price, image_url, category_id 
                FROM " . $this->tblProducts ." order by id desc ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "category_id" => $category_id,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }

        public function getAllProducts2()
        {
            try {
                $query = "SELECT id, product_name, price, image_url , category_id, datee
                FROM " . $this->tblProducts2 ." order by id desc ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "datee" => $datee,
                            "category_id" => $category_id,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }

        public function getCategory()
        {
            try {
                $query = "SELECT  category_name
                FROM " . $this->tblCategories ."  ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "category_name" => $category_name,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }
        

        public function getById($id)
        {
            try {
                $query = "SELECT id, product_name, price, image_url, category_id 
                            FROM " . $this->tblProducts ." where id=:id ";
                $stmt = $this->connection->prepare($query);
                $stmt->bindParam(":id", $id);
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $row = $stmt->fetch(PDO::FETCH_ASSOC);
                    extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "category_id" => $category_id,
                        );
                   
                    return $product;
                }
            } catch (Exception $e) {                
            }
            return null;
        }

        public function getAllCategories()
        {
            try {
                $query = "SELECT id, category_name
                FROM " . $this->tblCategories ."  ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $categories = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $cat = array( 
                            "id" => $id,
                            "category_name" => $category_name,
                        );
                        array_push($categories, $cat);
                    }
                   
                    return $categories;
                }
            } catch (Exception $e) {                
            }
            return null;
        }

        public function insert($product){
            try {
                $query = "INSERT INTO " . $this->tblProducts ." 
                    SET product_name = :product_name, price = :price
                    , image_url = :image_url, category_id = :category_id ";
                $stmt = $this->connection->prepare($query);
                
                $product_name = $product->getProductName();
                $price = $product->getPrice();
                $image_url = $product->getImageUrl();
                $category_id = $product->getCategoryId();

                $stmt->bindParam(":product_name", $product_name);
                $stmt->bindParam(":price", $price);
                $stmt->bindParam(":image_url", $image_url);
                $stmt->bindParam(":category_id", $category_id);

                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }
        public function insertCart($product){
            try {
                $query = "INSERT INTO " . $this->tblCart ." 
                    SET product_name = :product_name, price = :price
                    , image_url = :image_url ";
                $stmt = $this->connection->prepare($query);
                
                $product_name = $product->getProductName();
                $price = $product->getPrice();
                $image_url = $product->getImageUrl();

                $stmt->bindParam(":product_name", $product_name);
                $stmt->bindParam(":price", $price);
                $stmt->bindParam(":image_url", $image_url);

                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }
        public function insertCategory($category){
            try {
                $query = "INSERT INTO " . $this->tblCategories ." 
                    SET category_name = :category_name ";
                $stmt = $this->connection->prepare($query);
                
                $category_name = $category->getCategory_name();
                $stmt->bindParam(":category_name", $category_name);
 

                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }

        public function update($product){
            try {
                $query = " UPDATE " . $this->tblProducts ." 
                    SET product_name = :product_name, price = :price
                    , image_url = :image_url, category_id = :category_id
                    WHERE id=:id ";
                $stmt = $this->connection->prepare($query);              
                $product_name = $product->getProductName();
                $price = $product->getPrice();
                $image_url = $product->getImageUrl();
                $category_id = $product->getCategoryId();
                $id = $product->getId();    
                
                $stmt->bindParam(":product_name", $product_name);
                $stmt->bindParam(":price", $price);
                $stmt->bindParam(":image_url", $image_url);
                $stmt->bindParam(":category_id", $category_id);
                $stmt->bindParam(":id", $id);
                
                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }


        public function updateCategory($product){
            try {
                $query = " UPDATE " . $this->tblCategories ." 
                    SET category_name = :category_name
                    WHERE id=:id ";
                $stmt = $this->connection->prepare($query);              
                $category_name = $product->getCategory_name();
                $id = $product->getId();    
                
                $stmt->bindParam(":category_name", $category_name);
                $stmt->bindParam(":id", $id);
                
                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }

        public function delete($id){
            try {
                $query = "DELETE FROM " . $this->tblProducts ." 
                    WHERE id=:id ";
                $stmt = $this->connection->prepare($query);                  
                $stmt->bindParam(":id", $id);
                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }

        public function deleteCategory($id){
            try {
                $query = "DELETE FROM " . $this->tblCategories ." 
                    WHERE id=:id ";
                $stmt = $this->connection->prepare($query);                  
                $stmt->bindParam(":id", $id);
                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }
        public function deleteCart($id){
            try {
                $query = "DELETE FROM " . $this->tblCart ." 
                    WHERE id=:id ";
                $stmt = $this->connection->prepare($query);                  
                $stmt->bindParam(":id", $id);
                $this->connection->beginTransaction();
                if ($stmt->execute()) {
                    $this->connection->commit();
                    return true;
                } else {
                    $this->connection->rollBack();
                    return false;
                }
            } catch (Exception $e) {                
            }
            return false;
        }


        public function get1Products()
        {
            try {
                $query = "SELECT id, product_name, price, image_url, category_id 
                FROM " . $this->tblProducts ." where category_id=1 ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "category_id" => $category_id,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }

        public function get2Products()
        {
            try {
                $query = "SELECT id, product_name, price, image_url, category_id 
                FROM " . $this->tblProducts ." where category_id=2 ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "category_id" => $category_id,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }
        public function get3Products()
        {
            try {
                $query = "SELECT id, product_name, price, image_url, category_id 
                FROM " . $this->tblProducts ." where category_id=3 ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "category_id" => $category_id,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }
        public function get4Products()
        {
            try {
                $query = "SELECT id, product_name, price, image_url, category_id 
                FROM " . $this->tblProducts ." where category_id=4 ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "category_id" => $category_id,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }
        
        public function get5Products()
        {
            try {
                $query = "SELECT id, product_name, price, image_url, category_id 
                FROM " . $this->tblProducts ." where category_id=5 ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                            "category_id" => $category_id,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }

        public function getCart()
        {
            try {
                $query = "SELECT id, product_name, price, image_url
                FROM " . $this->tblCart ." order by id desc ";
                $stmt = $this->connection->prepare($query);
                
                $stmt->execute();
                if ($stmt->rowCount() > 0) {
                    $products = array();
                    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                        extract($row);
                        $product = array( 
                            "id" => $id,
                            "product_name" => $product_name,
                            "price" => $price,
                            "image_url" => $image_url,
                        );
                        array_push($products, $product);
                    }
                   
                    return $products;
                }
            } catch (Exception $e) {                
            }
            return null;
        }

        
        

    }
?>