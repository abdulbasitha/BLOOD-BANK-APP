<?php
    session_start();
    if (!isset($_SESSION['is_authenticated'])) {
        header('Location:login.php');
        exit();
    }
    include_once('db.php');
    $conn = db();
    if (isset($_POST['id'])) {
        $id = $_POST['id'];
        $expiry = new DateTime('now');
        $expiry->modify('+3 month');
        $expiry = $expiry->format('Y-m-d');
        $query = mysqli_query($conn, "UPDATE blood_bank_app_dept SET expiry='$expiry' WHERE id=$id");
        if (mysqli_affected_rows($conn) === 1) {
            $_SESSION['updated'] = true;
        } else {
            $_SESSION['updated'] = false;
        }
        header('Location:dashboard.php');
        exit();
    }
    if (isset($_GET['search'])) {
      $phno = $_GET['search'];
      $query = mysqli_query($conn, "SELECT id,name,phno FROM blood_bank_app_dept WHERE year > CURRENT_DATE AND expiry < CURRENT_DATE AND phno='$phno' ORDER BY name");
      if (mysqli_num_rows($query) !== 0) {
        $res = mysqli_fetch_assoc($query);
        // print_r($res);
      } else {
        $msg = true;
      }
    }
    // $query = mysqli_query($conn, "SELECT id,name,phno FROM blood_bank_app_dept WHERE year > CURRENT_DATE AND expiry < CURRENT_DATE ORDER BY name");
    // $members = array();
    // while ($row = mysqli_fetch_assoc($query)) {
    //     $members[] = $row;
    // }
    // $json_data = json_encode($members);
?>

<?php
$title = "Dashboard";
$name = $_SESSION['full_name'];
include_once('./assets/includes/header.php');
include_once('./assets/includes/navbar.php');
if(isset($_SESSION['updated'])) {
    if($_SESSION['updated']) { ?>
    <script>alert('Successfully updated.')</script>
    <?php
    } else { ?>
    <script>alert('Failed')</script>
    <?php
    }
    unset($_SESSION['updated']);
}
    ?>
<div
  class="dashboard-head d-sm-flex justify-content-between align-items-center p-3"
>
  <div>
    <h4 class="">Members</h4>
  </div>
  <div>
      <form class="form-inline">
        <input type="number" placeholder="Search" name="search" class="form-control mr-2" id="search" />
        <button type="submit" class="btn btn-danger btn-small mt-2 mt-sm-0">Search</button>
      </form>
  </div>
</div>
<?php if (isset($res)) { ?>
<div class="table-responsive">
  <table class="table table-dark table-striped table-bordered table-hover">
    <thead>
      <tr>
        <th>Name</th>
        <th>Phone Number</th>
        <th>Just Donated?</th>
      </tr>
    </thead>
    <tbody id="t-body">
    <tr>
      <td><?=$res['name']?></td>
      <td><?=$res['phno']?></td>
      <td>
      <form method="POST" onsubmit="return confirm('Do you really want to update?');">
        <input type="hidden" value="<?=$res['id']?>" name="id">
        <button type="submit" class="btn btn-sm btn-outline-danger"><i class="fa fa-check"></i></button>
      </form>
      </td>
    </tr>
    </tbody>
  </table>
</div>
<?php } 
  if (isset($msg)) {
  ?>
  <h3 class="lead text-center">Blood already given or no data available for this number : <?= $phno ?></h3>
<?php } ?>
<script>
  // let data = <?= $json_data ?>;
  // const t_body = document.getElementById('t-body');
  // // console.log(t_body);

  
  // const buildTable = (data) => {
  //   data.forEach(member => {
  //     let row = 
  //     `
  //     <tr>
  //       <td>${member['name']}</td>
  //       <td>${member['phno']}</td>
  //       <td>
  //           <form method="POST" onsubmit="return confirm('Do you really want to update?');">
  //               <input type="hidden" value="${member['id']}" name="id">
  //               <button type="submit" class="btn btn-sm btn-outline-danger"><i class="fa fa-check"></i></button>
  //           </form>
  //       </td>
  //     </tr>
  //     `
  //     // console.log(row);
  //     t_body.innerHTML += row;
  //   });
  // }
  // const searchInput = document.getElementById('search');
  // searchInput.addEventListener('keyup', (e) => {
  //   searchValue = event.target.value;
  //   filteredArray = data.filter((member) => member['name'].toLowerCase().startsWith(searchValue.toLowerCase()) || member['phno'].startsWith(searchValue));
  //   t_body.innerHTML = '';
  //   buildTable(filteredArray);
  // })
  
  // buildTable(data);


</script>
  <?php
include_once('./assets/includes/footer.php');
?>

