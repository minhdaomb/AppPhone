<?php
// php -S 127.0.0.1:8081
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

$data = json_decode(file_get_contents("php://input"));
    $e = array(
        "id" =>"0355026672",
        "name"=>"đạo "
    );
    echo json_encode($e);

    // $a = array(
    //     "id" =>"0355026672",
    //     "name"=>"anh "
    // );
    // echo json_encode($a);

    
    // $diema = $data -> diema;
    // $diemb = $data -> diemb;
    // $diemc = $data -> diemc;
    // $diemd = $data -> diemd;
    // $trungbinh=0;
    // $trungbinh = ($diema + $diemb + $diemc + $diemd) / 4;
    // $s = array(
    //     "ten"=>$ten,
    //     "diema"=>$diema,
    //     "diemb"=>$diemb,
    //     "diemc"=>$diemc,
    //     "diemd"=>$diemd,
    //     "trungbinh"=>$trungbinh,
    // );
    // echo json_encode($s);
?>