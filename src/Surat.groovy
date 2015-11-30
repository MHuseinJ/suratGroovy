import groovy.xml.MarkupBuilder

/**
 * Created by emhah on 11/30/2015.
 */

class Surat {
    Boolean HEADER
    Boolean TANGGAL
    Boolean FOOTER
    Boolean ISI
    Boolean PENERIMA
    Boolean PENUTUP
    Boolean PENGIRIM

    /**
     * This method accepts a closure which is essentially the DSL. Delegate the closure methods to
     * the DSL class so the calls can be processed
     */

    def static make(closure) {
        Surat surat = new Surat()
        // any method called in closure will be delegated to the memoDsl class
        closure.delegate = surat
        closure()
    }

    /**
     * Store the parameter as a variable and use it later to output a memo
     */

    def pengirim(Boolean pengirimText){
        this.PENGIRIM = pengirimText;
    }
    def penerima(Boolean penerimaText){
        this.PENERIMA = penerimaText;
    }
    def header(Boolean headerText){
        this.HEADER = headerText;
    }
    def footer(Boolean footerText){
        this.FOOTER = footerText;
    }
    def tanggal(Boolean tanggalText){
        this.TANGGAL = tanggalText;
    }
    def isi(Boolean isiText){
        this.ISI = isiText;
    }
    def penutup(Boolean penutupText){
        this.PENUTUP = penutupText;
    }


    def getSurat() {
        doSurat(this)
    }

    def getText() {
        doText(this)
    }

    private static doSurat(Surat surat){
        StringWriter writer = new StringWriter()
        MarkupBuilder xml = new MarkupBuilder(writer)

        String filename = "C:/xampp/htdocs/test.html"
        def surats = new File(filename)
        PrintWriter printWriter = new PrintWriter(surats)
        xml.html() {
            head {
                title("Simple Mail Maker")
            }
            body {
                form(method: 'post', action: 'surat-maker/enginetest.php') {
                    if(surat.HEADER){
                        br()
                        label("Header")
                        br()
                        input(type: 'text-area', name: 'header')
                    }
                    if(surat.TANGGAL){
                        br()
                        label("Tanggal")
                        br()
                        input(type: 'date', name: 'tanggal')
                    }
                    if(surat.PENGIRIM){
                        br()
                        label("pengirim")
                        br()
                        input(type: 'text-area', name: 'pengirim')
                    }
                    if(surat.PENERIMA){
                        br()
                        label("Penerima")
                        br()
                        input(type: 'text-area', name: 'penerima')
                    }
                    if(surat.ISI){
                        br()
                        label("Isi")
                        br()
                        input(type: 'text-area', name: 'isi')
                    }
                    if(surat.PENUTUP){
                        br()
                        label("Penutup")
                        br()
                        input(type: 'text-area', name: 'penutup')
                    }
                    if(surat.FOOTER){
                        br()
                        label("Footer")
                        br()
                        input(type: 'text-area', name: 'footer')
                    }
                    br()
                    label("Submit")
                    br()
                    input(type: 'submit', value: 'submit')
                }
            }
            printWriter.println(writer.toString())
            printWriter.close()
            println writer
        }
        String template = "<?php\n\n"
        template +="require_once 'common.php';\n\n"
        template += "system('clear');\n"
        template += "print_r(\$_POST);\n"
        template += "print(Demos_Zend_Service_LiveDocx_Helper::wrapLine("
        template += "        PHP_EOL . 'huhuhu'."
        template += "                PHP_EOL ."
        template += "                PHP_EOL)"
        template += ");\n"
        template += "\$mailMerge = new Zend_Service_LiveDocx_MailMerge();\n"
        template += "\$mailMerge->setUsername(DEMOS_ZEND_SERVICE_LIVEDOCX_USERNAME)"
        template += "->setPassword(DEMOS_ZEND_SERVICE_LIVEDOCX_PASSWORD);"
        template += "\$mailMerge->setLocalTemplate('temp-surat.docx');\n"
        if(surat.HEADER){
            template += "\$header = \$_POST['header'];\n"
            template += "\$mailMerge->assign('HEADER',  \$header);\n"
        }
        if(surat.FOOTER){
            template += "\$footer = \$_POST['footer'];\n"
            template += "\$mailMerge->assign('FOOTER',  \$footer);\n"
        }
        if(surat.PENERIMA){
            template += "\$penerima = \$_POST['penerima'];\n"
            template += "\$mailMerge->assign('PENERIMA',  \$penerima);\n"
        }
        if(surat.PENGIRIM){
            template += "\$pengirim = \$_POST['pengirim'];\n"
            template += "\$mailMerge->assign('PENGIRIM',  \$pengirim);\n"
        }
        if(surat.ISI){
            template += "\$isi = \$_POST['isi'];\n"
            template += "\$mailMerge->assign('ISI',  \$isi);\n"
        }
        if(surat.PENUTUP){
            template += "\$penutup = \$_POST['penutup'];\n"
            template += "\$mailMerge->assign('PENUTUP',  \$penutup);\n"
        }
        if(surat.TANGGAL){
            template += "\$tanggal = \$_POST['tanggal'];\n"
            template += "\$mailMerge->assign('TANGGAL',  \$tanggal);\n"
        }

        template += "\$mailMerge->createDocument();\n"
        template += "\$document = \$mailMerge->retrieveDocument('pdf');\n"
        template += "file_put_contents('document.pdf', \$document);\n"
        template += "print('File Telah Siap Di Download ');\n"
        template += "unset(\$mailMerge);\n"
        template += "?>\n"
        template += "<br><br>"
        template += "Download di <a href='document.pdf'>sini</a>"
        String filename2 = "C:/xampp/htdocs/surat-maker/enginetest.php"
        def surats2 = new File(filename2)
        PrintWriter printWriter2 = new PrintWriter(surats2)

        printWriter2.println(template.toString())
        printWriter2.close()
        println template
    }

    private static doText(Surat memoDsl) {

        String template = "<?php\n\n"
        template +="require_once 'common.php';\n\n"
        template += "system('clear');\n"
        template += "print_r(\$_POST);\n"
        template += "print(Demos_Zend_Service_LiveDocx_Helper::wrapLine("
        template += "        PHP_EOL . 'huhuhu'."
        template += "                PHP_EOL ."
        template += "                PHP_EOL)"
        template += ");\n"
        template += "\$mailMerge = new Zend_Service_LiveDocx_MailMerge();\n"
        template += "\$mailMerge->setUsername(DEMOS_ZEND_SERVICE_LIVEDOCX_USERNAME)"
        template += "->setPassword(DEMOS_ZEND_SERVICE_LIVEDOCX_PASSWORD);"
            template += "\$mailMerge->setLocalTemplate('temp-surat.docx');\n"
            template += "\$nama = \$_POST['nama'];\n"
            template += "\$nim = \$_POST['nim'];\n"
            template += "\$prodi = \$_POST['prodi'];\n"
            template += "\$pengirim = \$_POST['pengirim'];\n"
            template += "\$kontribusi = \$_POST['kontribusi'];\n"
            template += "\$mailMerge->assign('HEADER',  \$nama);\n"
            template += "\$mailMerge->assign('PENERIMA',  \$nim);\n"
            template += "\$mailMerge->assign('TANGGAL',  \$prodi);\n"
            template += "\$mailMerge->assign('ISI',  \$pengirim . \"\\n  huhuhuaasas\");\n"
            template += "\$mailMerge->assign('PENGIRIM',  \$kontribusi);\n"
            template += "\$mailMerge->createDocument();\n"
            template += "\$document = \$mailMerge->retrieveDocument('pdf');\n"
            template += "file_put_contents('document.pdf', \$document);\n"
            template += "print('File Telah Siap Di Download ');\n"
            template += "unset(\$mailMerge);\n"
            template += "?>\n"
            template += "<br><br>"
            template += "Download di <a href='document.pdf'>sini</a>"
        String filename = "C:/xampp/htdocs/surat-maker/enginetest.php"
        def surats = new File(filename)
        PrintWriter printWriter = new PrintWriter(surats)

        printWriter.println(template.toString())
        printWriter.close()
        println template
    }

}
