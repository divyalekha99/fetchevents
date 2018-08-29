<?php 
class db_ffunc {
 
    private $conn;
 
    function __construct() {
        require_once('C:\wamp64\www\and_con\db_conn.php');
        $db = new db_conn();
        $this->conn = $db->connect();
    }
 
    function __destruct() {
         
    }


public function fevents($mobileNo)
{
// prepare and bind
$stmt = $this->conn->prepare("SELECT * FROM events WHERE mobileNo = ?");
$stmt->bind_param("s", $mobileNo);
	
        if ($stmt->execute()) {
            
            $events = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $events;
            }
		else {
            return NULL;
        }
    }
}
    
?>