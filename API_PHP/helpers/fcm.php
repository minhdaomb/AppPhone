<?php 
    class FCM{
        public function sendNotify($title = "", $body="")
        {
            $key = "AAAAV3jnsOA:APA91bGFsaXm6XvbDyjpAct1TSMoOVR--wet1mEhO5eFRm-LiJvk8jDAMZSOyzyFr1Fx5cDn2meHtDNBHvo8sM-my1MrzvOCK183MsXh197Ep6Qhve47lr6VHcUpPIy_vy-C-8LmgzM2";
            ini_set("allow_url_open", "On");
            $data = [
                "to" => 'c95P0tijQjqCNIONNObwLP:APA91bHgrs2OOHpX13vT3AnennnEx4Lp3gXVII9y8qId0XbiHexFtGcl29SIH48qScETlEzsU6oIH0OFctusxqoUTCi8bZgb5klTevtppOHmwehQ9NlsdfqiGNKGDI3e2jJe7jMIH6RH',
                "notification" =>[
                    "body" => $body,
                    "title" => $title
                ]
            ];
            $option = array(
                'http' => array(
                    'method' => 'POST',
                    'content' => json_encode($data),
                    'header' => "Content-Type: application/json\r\n".
                        "Accept: application/json\r\n".
                        "Authorization:key=".$key
                )
            );
            $context = stream_context_create($option);
            $result = file_get_contents("https://fcm.googleapis.com/fcm/send",false, $context);
            return json_encode($result);
        }
    }
?>