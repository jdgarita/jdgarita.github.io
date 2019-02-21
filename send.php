<?php
if(isset($_POST['firstname']) && isset($_POST['lastname']) && isset($_POST['email']) && isset($_POST['message'])) {
	
require 'PHPMailerAutoload.php';

$firstname = 	$_POST['firstname'];
$lastname = 	$_POST['lastname'];
$email = 		$_POST['email'];
$message = 		$_POST['message'];


$mail = new PHPMailer;
$mail->setFrom($email, $firstname);
$mail->addAddress('juandiegogarita@gmail.com', 'jd');
$mail->Subject  = 'First PHPMailer Message';
$mail->Body     = $message;
	if(!$mail->send()) {
	echo 'Message was not sent.';
	echo 'Mailer error: ' . $mail->ErrorInfo;
	} else {
	echo 'Message has been sent.';
	}
}
?>   
            