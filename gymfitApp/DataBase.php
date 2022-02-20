<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $email, $geslo)
    {
        $email = $this->prepareData($email);
        $geslo = $this->prepareData($geslo);
        $this->sql = "SELECT * FROM " . $table . " where email = '" . $email . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbemail = $row['Email'];
            $dbgeslo = $row['Geslo'];
            if ($dbemail == $email && $dbgeslo == $geslo) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $ime, $priimek, $geslo, $Datum_rojstva, $Spol, $Tel_stevilka, $email)
    {
        $ime = $this->prepareData($ime);
        $priimek = $this->prepareData($priimek);
        $geslo = $this->prepareData($geslo);
        $Datum_rojstva = $this->prepareData($Datum_rojstva);
		$Spol = $this->prepareData($Spol);
		$Tel_stevilka = $this->prepareData($Tel_stevilka);
		$email = $this->prepareData($email);
        $this->sql =
            "INSERT INTO " . $table . " (ime, priimek, geslo, Datum_rojstva, Spol, Tel_stevilka, email) VALUES ('" . $ime . "','" . $priimek . "','" . $geslo . "','" . $Datum_rojstva . "','" . $Spol . "','" . $Tel_stevilka . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
	
	function reservation($table, $ID_uporabnika, $ID_fitnesa, $datum)
    {
        $ID_uporabnika = $this->prepareData($ID_uporabnika);
        $ID_fitnesa = $this->prepareData($ID_fitnesa);
        $datum = $this->prepareData($datum);
        $this->sql =
            "INSERT INTO " . $table . " (ID_uporabnika, ID_fitnesa, datum) VALUES ('" . $ID_uporabnika . "','" . $ID_fitnesa . "','" . $datum . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>
