<?php

require_once 'common.php';

system('clear');
print_r($_POST);
print(Demos_Zend_Service_LiveDocx_Helper::wrapLine(        PHP_EOL . 'huhuhu'.                PHP_EOL .                PHP_EOL));
$mailMerge = new Zend_Service_LiveDocx_MailMerge();
$mailMerge->setUsername(DEMOS_ZEND_SERVICE_LIVEDOCX_USERNAME)->setPassword(DEMOS_ZEND_SERVICE_LIVEDOCX_PASSWORD);$mailMerge->setLocalTemplate('skpi.docx');
$nama = $_POST['nama'];
$nim = $_POST['nim'];
$prodi = $_POST['prodi'];
$kontribusi = $_POST['kontribusi'];
$mailMerge->assign('nama',  $nama);
$mailMerge->assign('nim',  $nim);
$mailMerge->assign('prodi',  $prodi);
$mailMerge->assign('kontribusi',  $kontribusi);
$mailMerge->createDocument();
$document = $mailMerge->retrieveDocument('pdf');
file_put_contents('document.pdf', $document);
print('File Telah Siap Di Download ');
unset($mailMerge);
?>
<br><br>Download di <a href='document.pdf'>sini</a>
