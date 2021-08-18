<?php 
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
$data = json_decode(file_get_contents("php://input"));

include_once '../controllers/product_controller.php';
include_once '../models/product.php';

$category_name = $data->category_name;
$id = $data->id;

if ( $category_name && $id ) {
    $product = new Categories($id, $category_name);
    $status = (new ProductController())->updateCategory($product);
    if ($status) {
        http_response_code(200);
        echo json_encode(
            array(
                "message" => "Update success",
                "status" => true,
            )
        ); 
    } else {
        http_response_code(404);
        echo json_encode(
            array(
                
                "message" => "Update failed",
                "status" => false,
            )
        );
    }
}
else {
    http_response_code(404);
    echo json_encode(
        array(
            "message" => "Update failed",
            "status" => false,
        )
    );   
}

?>