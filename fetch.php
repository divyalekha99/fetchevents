<?php 
// Create connection
require_once('C:\wamp64\www\and_con\db_ffunc.php');
$conn= mysqli_connect("localhost","root","","newdb");
$db = new db_ffunc();
   
 /* if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }
*/
$response = array();

$_POST['mobileNo']="";
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if(isset($_POST['mobileNo']))
{
    $mobileNo= $_POST['mobileNo'];
   /* $sql = "INSERT INTO events(mobileNo,e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11) VALUES('0123341234',0,1,1,0,1,0,1,0,0,1,0)";
//,('0987654321', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
//('2147483647', 1, 1, 1, 0, 0, 0, 0, 0, 0, 0);";
        $result = mysqli_query($conn, $sql);
    */
      $response["error"] = FALSE;
		
		$events = $db->fevents($mobileNo);
		
    if($events != false){
                
		        $response["mobileNo"] = $events["mobileNo"];
				$response["events"]["e1"] = $events["e1"];
				$response["events"]["e2"] = $events["e2"];
				$response["events"]["e3"] = $events["e3"];
				$response["events"]["e4"] = $events["e4"];
				$response["events"]["e5"] = $events["e5"];
				$response["events"]["e6"] = $events["e6"];
				$response["events"]["e7"] = $events["e7"];
				$response["events"]["e8"] = $events["e8"];
				$response["events"]["e9"] = $events["e9"];
				$response["events"]["e10"] = $events["e10"];
				$response["events"]["e11"] = $event["e11"];
				
		/*		$response["mobileNo"] = $user["mobileNo"];
				$response["user"]["name"] = $user["name"];
				$response["user"]["email"] = $user["email"];
				$response["user"]["password"] = $user["password"];
				//$response["user"]["phoneNumber"] = $user["phone"];
				$response["user"]["collegeName"] = $user["college"];
		*/  
                
		}
     else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Please enter valid login credentials.";
        echo json_encode($response);
           }

}else{
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
    


/*// prepare and bind
$stmt = $conn->prepare("Select mobileNo from events where mobileNo = ?");
$stmt->bind_param("s", $firstname);

*/

?>