<?php
session_start();
if(!isset($_SESSION['user_id'])){
    header("Location: login.php");
}
?>

<h2>Welcome to Online Examination System</h2>
<a href="student/exam.php">Start Exam</a><br>
<a href="logout.php">Logout</a>
