<?php
session_start();
include("../config/db.php");

$score = 0;

if(isset($_POST['submit_exam'])){
    foreach($_POST['answers'] as $question_id => $answer){
        $query = $conn->query("SELECT correct_option, marks FROM questions WHERE id=$question_id");
        $row = $query->fetch_assoc();
        if($row['correct_option'] == $answer){
            $score += $row['marks'];
        }
    }
    echo "<h2>Your Score: $score</h2>";
    exit();
}

$result = $conn->query("SELECT * FROM questions LIMIT 5");
?>

<form method="POST">
<h2>Exam</h2>
<?php while($row = $result->fetch_assoc()) { ?>
<p><?php echo $row['question_text']; ?></p>
<input type="radio" name="answers[<?php echo $row['id']; ?>]" value="A"> <?php echo $row['option_a']; ?><br>
<input type="radio" name="answers[<?php echo $row['id']; ?>]" value="B"> <?php echo $row['option_b']; ?><br>
<input type="radio" name="answers[<?php echo $row['id']; ?>]" value="C"> <?php echo $row['option_c']; ?><br>
<input type="radio" name="answers[<?php echo $row['id']; ?>]" value="D"> <?php echo $row['option_d']; ?><br><br>
<?php } ?>
<button type="submit" name="submit_exam">Submit Exam</button>
</form>
