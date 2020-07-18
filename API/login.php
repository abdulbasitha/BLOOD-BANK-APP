<?php
    session_start();
    if (isset($_SESSION['is_authenticated'])) {
        header('Location:dashboard.php');
        exit();
    }
    include_once('db.php');
    $conn = db();
    if (isset($_POST['email']) && isset($_POST['password'])) {
        $email = $_POST['email'];
        $password = $_POST['password'];
        $query = mysqli_query($conn, "SELECT full_name FROM user WHERE email='$email' AND password='$password'");
        
        if (mysqli_num_rows($query) === 1) {
            $_SESSION['is_authenticated'] = true;
            $row = mysqli_fetch_assoc($query);
            // print_r($row['full_name']);
            $_SESSION['full_name'] = $row['full_name'];
            header("Location:dashboard.php");
            exit();
        } else {
            $_SESSION['error_msg'] = "Invalid Credentials.";
            header("Location:login.php");
            exit();
        }
    }

?>
    <?php
    $title = "Login";
    include_once('./assets/includes/header.php');
    ?>
        <section class="login-body">
    <form class="text-center login-form" method="POST" novalidate>
      <img
        class="mb-4"
        src="./assets/images/blood_bank_logo.webp"
        alt="MEA Blood Bank Logo"
        width="72"
        height="72"
      />
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <?php
            if(isset($_SESSION['error_msg'])) {
                ?>
                <div id="error" class="bg-danger text-white p-3 mb-3">
                <?php
                    echo($_SESSION['error_msg']);
                    unset($_SESSION['error_msg']);
                    ?>
                </div>
                <?php   
            }
        ?>
      
      <label for="inputEmail" class="sr-only">Email address</label>
      <input
        type="email"
        id="inputEmail"
        class="form-control"
        placeholder="Email address"
        name="email"
        required
        autofocus
      />
      <label for="inputPassword" class="sr-only">Password</label>
      <input
        type="password"
        id="inputPassword"
        class="form-control"
        placeholder="Password"
        name="password"
        required
      />
      <button class="btn btn-outline-danger btn-block mt-3" type="submit">
        Sign in
      </button>
      <p class="mt-5 mb-3 text-muted">
        Made with ❤ by <a href="https://techzia.in">Techzia</a>
      </p>
    </form>
        </section>
    <?php include_once('./assets/includes/footer.php') ?>
    
