<?php
if(isset($_POST['name']) && isset($_POST['email']) && isset($_POST['message'])) {
	
require 'PHPMailerAutoload.php';

$name = 	$_POST['name'];
$email = 		$_POST['email'];
$message = 		$_POST['message'];


$mail = new PHPMailer;
$mail->setFrom($email, $name);
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
            